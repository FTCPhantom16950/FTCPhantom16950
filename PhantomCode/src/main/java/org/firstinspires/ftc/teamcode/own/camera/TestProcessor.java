package org.firstinspires.ftc.teamcode.own.camera;

import static org.opencv.imgproc.Imgproc.RETR_EXTERNAL;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestProcessor implements VisionProcessor {
    private static double[] cameraPos = new double[]{0, 0, 0};
    private static double focalLength, length = 127;
    private static Scalar lowGreen, highGreen, lowPurple, highPurple;
    double rot = 0, x = 0, y = 0;
    Paint rectPaint = new Paint(), textPaint = new Paint();
    Telemetry telemetry;
    Mat hsvImage, purple, both, hierarchy, blur;
    volatile List<MatOfPoint> contours, proccessContours;
    Size blurSize;
    Map<Integer, double[]> objectCoordinatesRobotCentric = new HashMap();
    Map<Integer, double[]> objectCoordinatesFieldCentric = new HashMap();
    double[][]k = new double[3][3];
    public TestProcessor(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
//        focalLength = (calibration.focalLengthX + calibration.focalLengthY) / 2;
//        k[0][0] = calibration.focalLengthX;
//        k[1][0] = 0;
//        k[2][0] = calibration.principalPointX;
//        k[0][1] = 0;
//        k[1][1] = calibration.focalLengthY;
//        k[2][1] = calibration.principalPointY;
//        k[0][2] = 0;
//        k[1][2] = 0;
//        k[2][2] = 1;
        focalLength = 4.0;
        highGreen = new Scalar(63.8, 116, 164.3);
        lowGreen = new Scalar(41.1, 59.5, 66);
        lowPurple = new Scalar(92, 15, 48.23);
        highPurple = new Scalar(167, 59, 124);
        contours = new ArrayList<MatOfPoint>();
        proccessContours = new ArrayList<>();
        contours.clear();
        blur = new Mat(width, height, 24);
        hsvImage = new Mat(width, height, 24);
        purple = new Mat(width, height, 24);
        both = new Mat(width, height, 24);
        blurSize = new Size(31, 31);
        hierarchy = new Mat();
        rectPaint.setColor(Color.CYAN);
        rectPaint.setStyle(Paint.Style.STROKE);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setColor(Color.RED);
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.blur(frame, blur, blurSize);
        Imgproc.cvtColor(blur, hsvImage, Imgproc.COLOR_BGR2HSV);
        Core.inRange(hsvImage, lowGreen, highGreen, both);
        Core.inRange(hsvImage, lowPurple, highPurple, purple);
        Core.bitwise_or(purple, both, both);
        proccessContours.clear();
        if (Core.countNonZero(both) < 200) return null;
        Imgproc.findContours(both, proccessContours, hierarchy, RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

//        for (int i = 0; i < proccessContours.size(); i++) {
//            Imgproc.drawContours(frame, proccessContours, i, lowGreen, 2);
//        }
//        both.copyTo(frame);
        List<Rect> rects = new ArrayList<>();
        int i = 0;
        for (MatOfPoint countor : proccessContours) {
            if (contours.size() > 5) break;
            if (rects.size() > 5) break;
            Rect rect = Imgproc.boundingRect(countor);
            double distance = (length * focalLength) / (rect.width);
            double area = rect.height * rect.width ;
//                    * Math.pow((length/2) / ((double) rect.width / 2 ),2);
            double otn = (double) rect.height / rect.width;
            if (area <= 35000 &&
                    area >= 10000
                    && (otn) >= 0.6 &&
                    (otn) <= 1.5 && distance <= 1500
            ) {
                rects.add(Imgproc.boundingRect(countor));

                double xc = distance * Math.cos(cameraPos[2]), yc = distance * Math.sin(cameraPos[2]);
                double xr = xc + cameraPos[0], yr = yc + cameraPos[1];
                double degreeBetweenRobotAndObject = Math.atan2(yr, xr);
                double xrot = xr * Math.cos(rot) - yr * Math.sin(rot);
                double yrot = xr * Math.sin(rot) + yr * Math.cos(rot);
                objectCoordinatesRobotCentric.put(i, new double[]{xc, yc, degreeBetweenRobotAndObject});
                objectCoordinatesFieldCentric.put(i, new double[]{x + xrot, y + yrot});
                i++;
                Imgproc.putText(frame,Double.toString(area),new Point(rect.x, rect.y),1,1,new Scalar(255,255,0));
            }

        }

        return rects;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        rectPaint.setStrokeWidth(scaleCanvasDensity * 4);
        if (userContext == null) return;
        List<Rect> rectsList = (List<Rect>) userContext;
        textPaint.setTextSize(30 * scaleCanvasDensity);
        textPaint.setStrokeWidth(scaleCanvasDensity * 0.5f);
        for (int i = 0; i < rectsList.size(); i++) {
            Rect rect = rectsList.get(i);
            double distance = (length * focalLength) / (rect.width);
            canvas.drawRect(makeGraphicsRect(rect, scaleBmpPxToCanvasPx), rectPaint);
            canvas.drawText(Double.toString(distance), (float) ((rect.tl().x + 20) * scaleBmpPxToCanvasPx), (float) ((rect.br().y + 1) * scaleBmpPxToCanvasPx), textPaint);
            canvas.drawText(Integer.toString(i), (float) (rect.tl().x * scaleBmpPxToCanvasPx), (float) ((rect.br().y) * scaleBmpPxToCanvasPx), textPaint);
        }

    }

    private android.graphics.Rect makeGraphicsRect(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }
}
