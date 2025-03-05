package org.firstinspires.ftc.teamcode.FORTEST;

import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sL;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sR;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lbSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.leftBack;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.leftFront;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lfSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rbSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rfSpeed;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rightBack;

import static org.firstinspires.ftc.teamcode.FORTEST.Zx.brat;
import static org.firstinspires.ftc.teamcode.FORTEST.Zx.zx;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rightFront;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

/**
 * класс для вывода телеметрии
 */
public class PHTelemetry {
    private LinearOpMode opMode;
    private boolean isWheelBase, isZX, isVerticalSlider, isPodves, isHorizontalSlider;

    public PHTelemetry(LinearOpMode opMode, boolean isWheelBase, boolean isZX, boolean isVerticalSlider, boolean isPodves, boolean isHorizontalSlider) {
        this.opMode = opMode;
        this.isWheelBase = isWheelBase;
        this.isZX = isZX;
        this.isVerticalSlider = isVerticalSlider;
        this.isPodves = isPodves;
        this.isHorizontalSlider = isHorizontalSlider;
    }

    public PHTelemetry(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public void start_telemetry(){
        Telemetry telemetry = opMode.telemetry;
        if(isZX){
            telemetry.addData("zx pos:", zx.getPower());
            telemetry.addData("krut pos:", brat.getPower());
        }
        if (isWheelBase){
            telemetry.addData("rbspeed", rbSpeed);
            telemetry.addData("rfspeed", rfSpeed);
            telemetry.addData("lbspeed", lbSpeed);
            telemetry.addData("lfspeed", lfSpeed);
            telemetry.addData("rbtick", rightBack.getCurrentPosition());
            telemetry.addData("rftick", rightFront.getCurrentPosition());
            telemetry.addData("lbtick", leftBack.getCurrentPosition());
            telemetry.addData("lftick", leftFront.getCurrentPosition());
            telemetry.addData("rightBack", rightBack.getPower());
            telemetry.addData("leftBack", leftBack.getPower());
            telemetry.addData("rightFront", rightFront.getPower());
            telemetry.addData("leftFront", leftFront.getPower());
            telemetry.addData("rightBackCurr", rightBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftBackCurr", leftBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("rightFrontCurr", rightFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftFrontCurr", leftFront.getCurrent(CurrentUnit.AMPS));
        }
        if (isVerticalSlider){
            telemetry.addData("pod power:", pod.getPower());
            telemetry.addData("kleshna power:", klesh.getPower());
            telemetry.addData("vrash power:", vrash.getPower());
        } if(isHorizontalSlider){
            telemetry.addData("horL power:", sL.getPower());
            telemetry.addData("horR power:", sR.getPower());
        }
        telemetry.update();
    }
}
