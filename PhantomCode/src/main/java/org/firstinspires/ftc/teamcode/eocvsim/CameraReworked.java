package org.firstinspires.ftc.teamcode.eocvsim;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import org.firstinspires.ftc.robotcore.internal.camera.calibration.CameraCalibration;
import org.firstinspires.ftc.vision.VisionProcessor;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class CameraReworked implements VisionProcessor{
    public int valLeft, valRight;
    Mat frame;

    public Rect leftRect = new Rect(30 , 40, 20,10 );
    public Rect rightRect = new Rect(300, 300, 20, 10);
    Mat hsvImage = new Mat();
    Mat yCbCrChan2Mat = new Mat();
    Mat thresholdMat = new Mat();
    Mat rgbImage = new Mat();
    Mat all = new Mat();
    Mat redMask = new Mat();
    Mat blueMask = new Mat();
    Mat yellowResultRGB = new Mat();
    Mat yellowResult = new Mat();

    // Создание маски для желтого цвета
    Mat yellowMask = new Mat();

    // Определение диапазона красного цвета в HSV
    Scalar lowerRed = new Scalar(0,20,20);
    Scalar upperRed = new Scalar(160,255,255);

    // Определение диапазона синего цвета в HSV
    Scalar lowerBlue = new Scalar(160, 40, 40);
    Scalar upperBlue = new Scalar(255, 255, 255);

    List<MatOfPoint> contoursList = new ArrayList<>();

        @Override
        public void init(int width, int height, CameraCalibration calibration) {
        }

        @Override
        public Mat processFrame(Mat input, long captureTimeNanos) {
            frame = input;
            contoursList.clear();
            /*
             * This pipeline finds the contours of yellow blobs such as the Gold Mineral
             * from the Rover Ruckus game.
             */
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
            //color diff cb.
            //lower cb = more blue = skystone = white
            //higher cb = less blue = oyellow stne = grey
            Imgproc.cvtColor(yellowResultRGB, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);//converts rgb to ycrcb
            Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, 2);//takes cb difference and stores
            //b&w
            Imgproc.threshold(yCbCrChan2Mat, thresholdMat, 120, 255, Imgproc.THRESH_BINARY_INV);
            //outline/contour
            Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            yCbCrChan2Mat.copyTo(all);//copies mat object
            // Imgproc.drawContours(all, contoursList, -1, new Scalar(255, 0, 0), 3, 8);//draws blue contours
            //get values from frame
            double[] pixLeft = thresholdMat.get((int) (input.rows() * leftPos[1]), (int) (input.cols() * leftPos[0]));//gets value at circle
            valLeft = (int) pixLeft[0];

            double[] pixRight = thresholdMat.get((int) (input.rows() * rightPos[1]), (int) (input.cols() * rightPos[0]));//gets value at circle
            valRight = (int) pixRight[0];

            //create three points

            return thresholdMat;

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

            canvas.drawRect(makeRectangle(leftRect,scaleBmpPxToCanvasPx), rectPaint);
            canvas.drawRect(makeRectangle(rightRect, scaleBmpPxToCanvasPx), rectPaint);
        }
    }

