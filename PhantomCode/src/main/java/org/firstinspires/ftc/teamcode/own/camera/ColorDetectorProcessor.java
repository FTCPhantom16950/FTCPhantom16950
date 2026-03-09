package org.firstinspires.ftc.teamcode.own.camera;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Mat;
import org.opencv.core.Rect;

public class ColorDetectorProcessor implements VisionProcessor {
    int x = 10,y = 10,width = 10, height = 10;

    public Rect rect = new Rect(20, 20, 50, 50);
    public ColorDetectorProcessor(Builder builder) {
    }

    @Override
    public void init(int width, int height, CameraCalibration calibration) {

    }

    @Override
    public Object processFrame(Mat frame, long captureTimeNanos) {

        return null;
    }

    @Override
    public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(scaleCanvasDensity * 4);
        canvas.drawRect(opencvToAndroid(rect, scaleBmpPxToCanvasPx), paint);
    }
    private android.graphics.Rect opencvToAndroid(Rect rect, float scaleBmpPxToCanvasPx){
        int left = Math.round(rect.x * scaleBmpPxToCanvasPx);
        int top = Math.round(rect.y * scaleBmpPxToCanvasPx);
        int right = left + Math.round(rect.width * scaleBmpPxToCanvasPx);
        int bottom = top + Math.round(rect.height * scaleBmpPxToCanvasPx);

        return new android.graphics.Rect(left,top,right,bottom);
    }
    public static class Builder {
        public ColorDetectorProcessor build() {
            return new ColorDetectorProcessor(this);
        }
    }
}
