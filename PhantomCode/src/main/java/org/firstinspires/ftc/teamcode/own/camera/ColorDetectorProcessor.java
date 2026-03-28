package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.states.ArtifactColor;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ColorDetectorProcessor implements VisionProcessor {
    int x = 150, y = 75, width = 30, height = 30;
    public Rect rect = new Rect(x, y, width, height);
    Mat blur, hsv, dilate, submat;
    double satur = 0, hue = 0, value = 0;
    ArtifactColor currentColor = ArtifactColor.UNKNOWN;

    public ColorDetectorProcessor(Builder builder) {
    }

    public ArtifactColor getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ArtifactColor currentColor) {
        this.currentColor = currentColor;
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {
        blur = new Mat();
        hsv = new Mat();
        dilate = new Mat();
        submat = new Mat();
    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {
        Imgproc.blur(frame, frame, new Size(30, 30));
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2HSV);
//        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_HSV2RGB);
        satur = getAvgSaturation(frame, rect);
        hue = getAvgHue(frame, rect);
        if ((hue >= 75 && hue <= 120) && (satur >= 180 && satur <= 255)) {
            currentColor = ArtifactColor.GREEN;
        } else if ((hue >= 130 && hue <= 170) && (satur >= 90 && satur <= 150)) {
            currentColor = ArtifactColor.PURPLE;
        } else {
            currentColor = ArtifactColor.UNKNOWN;
        }
//        try {
//            Robot.INSTANCE.addTelemetryData(currentColor.getClass().getSimpleName(), currentColor);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

//        try {
//            Robot.INSTANCE.addTelemetryData("hue", hue);
//            Robot.INSTANCE.addTelemetryData("value", getAvgValue(frame, rect));
//            Robot.INSTANCE.addTelemetryData("satur", satur);
//            Robot.INSTANCE.addTelemetryData("color", currentColor);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }


        return null;
    }

    protected double getAvgSaturation(Mat input, Rect rect) {
        submat = input.submat(rect);
        Scalar color = Core.mean(submat);
        return color.val[1];
    }

    protected double getAvgHue(Mat input, Rect rect) {
        submat = input.submat(rect);
        Scalar color = Core.mean(submat);
        return color.val[0];
    }

    protected double getAvgValue(Mat input, Rect rect) {
        submat = input.submat(rect);
        Scalar color = Core.mean(submat);
        return color.val[2];
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(scaleCanvasDensity * 4);
        canvas.drawRect(opencvToAndroid(rect, scaleBmpPxToCanvasPx), paint);

    }

    private android.graphics.Rect opencvToAndroid(Rect rect, float scaleBmpPxToCanvasPx) {
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left, top, right, bottom);
    }

    public static class Builder {
        public ColorDetectorProcessor build() {
            return new ColorDetectorProcessor(this);
        }
    }
}
