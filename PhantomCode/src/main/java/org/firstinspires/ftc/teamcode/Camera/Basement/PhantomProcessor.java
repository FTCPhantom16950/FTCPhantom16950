package org.firstinspires.ftc.teamcode.Camera.Basement;


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

public class PhantomProcessor implements VisionProcessor {
    // значения правого и левого прямоугольника
    public int valLeft, valRight;
    // размеры и координаты прямоугольников на экране
    public Rect leftRect = new Rect(30 , 40, 20,10 );
    public Rect rightRect = new Rect(300, 300, 20, 10);
    // значения выбранного прямоугольника
    Selcted selcted = Selcted.None;
    /*
    создаём матрицы для будущего использования
    если создавать матрицы внутри processFrame, будет утечка памяти
     */
    Mat yCbCrChan2Mat = new Mat();
    Mat submat = new Mat();
    // Определение диапазона синего цвета в HSV
    public Scalar lowerBlue = new Scalar(160, 40, 40);
    public Scalar upperBlue = new Scalar(255, 255, 255);
    // Определение диапазона красного цвета
    Scalar lowerRed = new Scalar(0,20,20);
    Scalar upperRed = new Scalar(160,255,255);

    /**
     * метод инициализации, значения устанавливаются при запуске visionportal
     * @param width длинна
     * @param height высота
     * @param calibration настройки камеры
     */
        @Override
        public void init(int width, int height, CameraCalibration calibration) {
        }


    /**
     * обработчик кадров
     * @param input входная картинка(обычно с камеры)
     * @param captureTimeNanos время обновления картинки в наносекундах
     * @return возвращает выбор одного из двух прямоугольников, у которого больше значение value
     */
    @Override
        public Object processFrame(Mat input, long captureTimeNanos) {

            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2GRAY);
            Imgproc.threshold(yCbCrChan2Mat, yCbCrChan2Mat, 120, 255, Imgproc.THRESH_BINARY_INV);
            Imgproc.cvtColor(yCbCrChan2Mat, input, Imgproc.COLOR_GRAY2RGB);

            valLeft = getAverageValue(input, leftRect);
            valRight = getAverageValue(input, rightRect);

            if (valRight >= 122){
                return selcted = Selcted.Right;
            } else if (valLeft >= 122){
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

    /**
     * отрисовщик объектов на экране
     * @param canvas
     * @param onscreenWidth
     * @param onscreenHeight
     * @param scaleBmpPxToCanvasPx
     * @param scaleCanvasDensity
     * @param userContext
     */
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

