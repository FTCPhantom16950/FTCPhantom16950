package org.firstinspires.ftc.teamcode.FORTEST.Controllers;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.jetbrains.annotations.NotNull;
/// класс контроллеров для моторов
public class FtcControllers {
    // объявляем переменные
    LinearOpMode OPMode;
    /**
     * Класс контроллеров для моторов
     * @param OPMode опмод в котором он будет работать
     */
    public FtcControllers(LinearOpMode OPMode) {
        this.OPMode = OPMode;
    }

    /**
     * метод для настройки оригинального пидф регулятора для моторов
     * @param motor DcMotorEx мотор в для которого будет настроен PIDF регулятор
     * @param p p коэффициент
     * @param i i коэффициент
     * @param d d коэффициент
     * @param f f коэффициент
     */
    public void originalPidf(@NotNull DcMotorEx motor, double p, double i, double d, double f) {
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(p, i, d, f);
        motor.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        OPMode.telemetry.addData("P,I,D,F (modified)", "%.04f, %.04f, %.04f, %.04f",
                pidfCoefficients.p, pidfCoefficients.i, pidfCoefficients.d, pidfCoefficients.f);
        OPMode.telemetry.update();
        int errorThere = motor.getTargetPosition() - motor.getCurrentPosition();
        OPMode.telemetry.addData("Error: ", errorThere);
        OPMode.telemetry.update();
    }
}


