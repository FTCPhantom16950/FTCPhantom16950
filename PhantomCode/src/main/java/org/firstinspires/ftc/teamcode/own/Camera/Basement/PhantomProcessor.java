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
//    Telemetry telemetry;
//
//    public PhantomProcessor(Telemetry telemetry) {
//        this.telemetry = telemetry;
//    }

    // объявляем константы для левого прямоугольника
    private static final int LEFT_RECT_X = 30;
    private static final int LEFT_RECT_Y = 40;
    private static final int LEFT_RECT_WIDTH = 20;
    private static final int LEFT_RECT_HEIGHT = 10;
    // объявляем константы для правого прямоугольника
    private static final int RIGHT_RECT_X = 300;
    private static final int RIGHT_RECT_Y = 300;
    private static final int RIGHT_RECT_WIDTH = 20;
    private static final int RIGHT_RECT_HEIGHT = 10;
    public int blockSize = 777;
    public double C = 25;
    public double max_value = 255;

    // значения правого и левого прямоугольника
    private int valLeft, valRight;
    private Rect leftRect;
    private Rect rightRect;
    // значения выбранного прямоугольника
    private Selected selcted = Selected.None;
    /*
    создаём матрицы для будущего использования
    если создавать матрицы внутри processFrame, будет утечка памяти
     */
    // создаем матрицу для черно-белого изображения
    private Mat yCbCrChan2Mat;
    // создаем дополнительую матрицу для обработки
    private Mat submat ;

    /**
     * метод инициализации, значения устанавливаются при запуске visionportal
     * @param width длинна
     * @param height высота
     * @param calibration настройки камеры
     */
        @Override
        public void init(int width, int height, CameraCalibration calibration) {
            // создаем объект левого прямоугольника
            leftRect = new Rect(LEFT_RECT_X , LEFT_RECT_Y, LEFT_RECT_WIDTH,LEFT_RECT_HEIGHT);
            // создаем объект правого прямоугольника
            rightRect = new Rect(RIGHT_RECT_X, RIGHT_RECT_Y, RIGHT_RECT_WIDTH, RIGHT_RECT_HEIGHT);
            submat = new Mat();
            yCbCrChan2Mat = new Mat();
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
//        // преобразуем из серого в ч/б формат
        Imgproc.threshold(yCbCrChan2Mat, yCbCrChan2Mat, 122, 255, Imgproc.THRESH_BINARY_INV);
 //
//        // преобразуем картинку назад в RGB
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
            rectPaint.setColor(Color.BLUE);
            // указываем заполненность
            rectPaint.setStyle(Paint.Style.STROKE);
            // указываем ширину стенок
            rectPaint.setStrokeWidth(scaleCanvasDensity * 4);
            Paint unselectedPaint = new Paint();
            unselectedPaint.setColor(Color.GRAY);
            unselectedPaint.setStyle(Paint.Style.STROKE);
            unselectedPaint.setStrokeWidth(scaleCanvasDensity * 4);
            // создаем прямоугольник
            android.graphics.Rect drawRectRight = makeRectangle(rightRect, scaleBmpPxToCanvasPx);
            android.graphics.Rect drawRectLeft = makeRectangle(leftRect, scaleBmpPxToCanvasPx);
            // проверяем выбор наших прямоугольников
            switch (selcted){
                case None:
                    // отрисовываем прямоугольники
                    canvas.drawRect(drawRectLeft, unselectedPaint);
                    canvas.drawRect(drawRectRight, unselectedPaint);
                    break;
                case Left:
                    canvas.drawRect(drawRectLeft, rectPaint);
                    canvas.drawRect(drawRectRight,unselectedPaint);
                    break;
                case Right:
                    canvas.drawRect(drawRectLeft, unselectedPaint);
                    canvas.drawRect(drawRectRight, rectPaint);
                    break;
            }
        }
        // геттеры для получения значений цвета
        public int getValLeft() {
            return valLeft;
        }

        public int getValRight() {
            return valRight;
        }

    }

