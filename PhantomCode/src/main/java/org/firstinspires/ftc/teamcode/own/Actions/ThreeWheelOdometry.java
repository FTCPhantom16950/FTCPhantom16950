package org.firstinspires.ftc.teamcode.own.Actions;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;

public class ThreeWheelOdometry extends Action {
    ElapsedTime time = new ElapsedTime();
    private final double WHEBASE_RADIUS = 0.4;
    private static DcMotorEx leftEncoder, rightEncoder, frontEncoder;
    double prevLeftPos = 0, prevRightPos = 0, prevFrontPos = 0;
    private double ticksToDistance = (0.048 * Math.PI) / 2000.0; // метры
    private double distanceBetweenHorizontal = 0;
    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            double currentTime = time.seconds();
            int currentLeft = leftEncoder.getCurrentPosition();
            int currentRight = rightEncoder.getCurrentPosition();
            int currentFront = frontEncoder.getCurrentPosition();

            // Вычисляем перемещения в мм
            double deltaLeft = (currentLeft - prevLeftPos) / ticksToDistance;
            double deltaRight = (currentRight - prevRightPos) / ticksToDistance;
            double deltaFront = (currentFront - prevFrontPos) / ticksToDistance;

            // Обновляем предыдущие значения
            prevLeftPos = currentLeft;
            prevRightPos = currentRight;
            prevFrontPos = currentFront;

            // Вычисляем изменение ориентации
            double deltaTheta = (deltaRight - deltaLeft) / (2 * WHEBASE_RADIUS);

            // Среднее перемещение по X
            double deltaXLocal = (deltaLeft + deltaRight) / 2.0;

            // Преобразуем в глобальные координаты
            double deltaXGlobal = deltaXLocal * Math.cos(rot) - deltaFront * Math.sin(rot);
            double deltaYGlobal = deltaXLocal * Math.sin(rot) + deltaFront * Math.cos(rot);

            // Обновляем позицию
            x += deltaXGlobal;
            y += deltaYGlobal;
            rot += deltaTheta;

            // Нормализуем угол
            rot = AngleUnit.normalizeDegrees(rot);
            vx = deltaXGlobal / time.seconds();
            vy = deltaYGlobal / time.seconds();
            vRot = deltaTheta / time.seconds();
            time.reset();
        }
    }
}
