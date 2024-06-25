package org.firstinspires.ftc.teamcode.eocvsim;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class CameraReworked implements VisionProcessor{
    public int valLeft, valRight;
    public Rect leftRect = new Rect(30 , 40, 20,10 );
    public Rect rightRect = new Rect(300, 300, 20, 10);
    Selcted selcted = Selcted.None;
    Mat hsv = new Mat();
    Mat hsvImage = new Mat();
    Mat yCbCrChan2Mat = new Mat();
    Mat thresholdMat = new Mat();
    Mat rgbImage = new Mat();
    Mat all = new Mat();
    Mat redMask = new Mat();
    Mat blueMask = new Mat();
    Mat yellowResultRGB = new Mat();
    Mat yellowResult = new Mat();
    Scalar lowerRed = new Scalar(0,20,20);
    Scalar upperRed = new Scalar(160,255,255);
    Mat submat = new Mat();
    // Определение диапазона синего цвета в HSV
    public Scalar lowerBlue = new Scalar(160, 40, 40);
    public Scalar upperBlue = new Scalar(255, 255, 255);
    Mat yellowMask = new Mat();

        @Override
        public void init(int width, int height, CameraCalibration calibration) {
        }



    @Override
        public Object processFrame(Mat input, long captureTimeNanos) {
            Imgproc.cvtColor(input, rgbImage, Imgproc.COLOR_BGR2RGB);
            // Преобразование RGB в HSV
            Imgproc.cvtColor(rgbImage, hsvImage, Imgproc.COLOR_RGB2HSV);
            // Создание масок для красного и синего цветов
            Core.inRange(hsvImage, lowerRed, upperRed, redMask);
            Core.inRange(hsvImage, lowerBlue, upperBlue, blueMask);
            Core.add(hsvImage, new Scalar(60, 100, 100), rgbImage, blueMask);
            Core.add(hsvImage, new Scalar(60, 100, 100), rgbImage, redMask);
            Core.bitwise_and(rgbImage, rgbImage, yellowResult, yellowMask);
            Imgproc.cvtColor(yellowResult, yellowResultRGB, Imgproc.COLOR_HSV2RGB);

            Imgproc.cvtColor(yellowResultRGB, yCbCrChan2Mat, Imgproc.COLOR_RGB2GRAY);
            Imgproc.threshold(yCbCrChan2Mat, input, 120, 255, Imgproc.THRESH_BINARY_INV);
            Imgproc.cvtColor(input, input, Imgproc.COLOR_GRAY2RGB);


            valLeft = getAverageValue(input, leftRect);
            valRight = getAverageValue(input, rightRect);

            if (valRight > valLeft){
                return selcted = Selcted.Right;
            } else if (valLeft > valRight){
                return selcted = Selcted.Left;
            } else {
                return selcted = Selcted.None;
            }
        }
        protected int getAverageValue(Mat input, Rect rect){
            submat = input.submat(rect);
            Scalar color = Core.mean(submat);
            return (int) Math.round(color.val[2]);
        }

        private android.graphics.Rect makeRectangle(Rect rect, float scaleBmpPXToCanvasPX) {
            int left = Math.round(rect.x * scaleBmpPXToCanvasPX);
            int top = Math.round(rect.y * scaleBmpPXToCanvasPX);
            int right = left + Math.round(rect.width * scaleBmpPXToCanvasPX);
            int bottom = top + Math.round(rect.width* scaleBmpPXToCanvasPX);
            return new android.graphics.Rect(left,top,right,bottom);
        }
        @Override
        public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
            Paint rectPaint = new Paint();
            rectPaint.setColor(Color.YELLOW);
            rectPaint.setStyle(Paint.Style.STROKE);
            rectPaint.setStrokeWidth(scaleCanvasDensity * 4);

            Paint nobselectedpaint = new Paint();
            nobselectedpaint.setColor(Color.GRAY);
            nobselectedpaint.setStyle(Paint.Style.STROKE);
            nobselectedpaint.setStrokeWidth(scaleCanvasDensity * 4);

            android.graphics.Rect drawRectRight = makeRectangle(rightRect, scaleBmpPxToCanvasPx);
            android.graphics.Rect drawRectLeft = makeRectangle(leftRect, scaleBmpPxToCanvasPx);

            switch (selcted){
                case None:
                    canvas.drawRect(drawRectLeft, nobselectedpaint);
                    canvas.drawRect(drawRectRight, nobselectedpaint);
                    break;
                case Left:
                    canvas.drawRect(drawRectLeft, rectPaint);
                    canvas.drawRect(drawRectRight,nobselectedpaint);
                    break;
                case Right:
                    canvas.drawRect(drawRectLeft, nobselectedpaint);
                    canvas.drawRect(drawRectRight, rectPaint);
                    break;
            }

        }

        public enum Selcted{
            None,
            Left,
            Right
        }

    }

