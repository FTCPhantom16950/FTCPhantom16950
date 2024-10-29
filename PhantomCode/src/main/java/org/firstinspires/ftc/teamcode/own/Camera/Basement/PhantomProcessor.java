package org.firstinspires.ftc.teamcode.own.Camera.Basement;


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
    Selected selcted = Selected.None;
    /*
    создаём матрицы для будущего использования
    если создавать матрицы внутри processFrame, будет утечка памяти
     */
    Mat yCbCrChan2Mat = new Mat();
    Mat submat = new Mat();


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
            // конвертируем матрицы из одного формата в дргой
            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2GRAY);
            Imgproc.threshold(yCbCrChan2Mat, yCbCrChan2Mat, 120, 255, Imgproc.THRESH_BINARY_INV);
            Imgproc.cvtColor(yCbCrChan2Mat, input, Imgproc.COLOR_GRAY2RGB);
            // считываем значения в квадратах
            valLeft = getAverageValue(input, leftRect);
            valRight = getAverageValue(input, rightRect);
            // проверяем значения hue
            // если значение в правом квадрате 122, то выбираем правый
            if (valRight >= 122){
                return selcted = Selected.Right;
            // если значение в левои квадрате 122, то выбираем левый
            } else if (valLeft >= 122){
                return selcted = Selected.Left;
            // в любом другом случае не выбираем ни один
            } else {
                return selcted = Selected.None;
            }
        }
        //Метод для считывания цвета внутри прямоугольника
        protected int getAverageValue(Mat input, Rect rect){
            // присваеваем матрице значения цвета из нашего прямоугольника
            submat = input.submat(rect);
            //считываем средние значения цвета в hsv, для этого создаем список цвета
            Scalar color = Core.mean(submat);
            //возвращаем значения Value из списка и преобразуем в int
            return (int) Math.round(color.val[2]);
        }
        //метод для преобразования объекта прямоугольника из формта OpenCV в формат Android
        private android.graphics.Rect makeRectangle(Rect rect, float scaleBmpPXToCanvasPX) {
        // переносим координаты левой стороны из системы OpenCV в систему Android(экрана)
            int left = Math.round(rect.x * scaleBmpPXToCanvasPX);
            // переносим координаты верхней стороны из системы OpenCV в систему Android(экрана)
            int top = Math.round(rect.y * scaleBmpPXToCanvasPX);
            // переносим координаты правой стороны из системы OpenCV в систему Android(экрана)
            int right = left + Math.round(rect.width * scaleBmpPXToCanvasPX);
            // переносим координаты нижней стороны из системы OpenCV в систему Android(экрана)
            int bottom = top + Math.round(rect.width* scaleBmpPXToCanvasPX);
            // возвращаем объект прямоугольника, готового для отображения на экране
            return new android.graphics.Rect(left,top,right,bottom);
        }

    /**
     * отрисовщик объектов на экране
     */
        @Override
        public void onDrawFrame(Canvas canvas, int onscreenWidth, int onscreenHeight, float scaleBmpPxToCanvasPx, float scaleCanvasDensity, Object userContext) {
            //создаем обЪект стиля квалратов
            Paint rectPaint = new Paint();
            // указываем цвет
            rectPaint.setColor(Color.YELLOW);
            // указываем заполненность
            rectPaint.setStyle(Paint.Style.STROKE);
            // указываем ширину стенок
            rectPaint.setStrokeWidth(scaleCanvasDensity * 4);

            Paint nobselectedpaint = new Paint();
            nobselectedpaint.setColor(Color.GRAY);
            nobselectedpaint.setStyle(Paint.Style.STROKE);
            nobselectedpaint.setStrokeWidth(scaleCanvasDensity * 4);
            // создаем прямоугольник
            android.graphics.Rect drawRectRight = makeRectangle(rightRect, scaleBmpPxToCanvasPx);
            android.graphics.Rect drawRectLeft = makeRectangle(leftRect, scaleBmpPxToCanvasPx);
            // проверяем выбор наших прямоугольников
            switch (selcted){
                case None:
                    // отрисовываем прямоугольники
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
        //список для выбора квадратов


    }

