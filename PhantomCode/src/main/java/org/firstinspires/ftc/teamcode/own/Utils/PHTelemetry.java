package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zx;

public class PHTelemetry {
    private LinearOpMode opMode;
    boolean isHorSlider = false, isVertSlider = false, isZX = false, isWheelBAse = false;

    public PHTelemetry(LinearOpMode opMode, boolean isHorSlider, boolean isVertSlider, boolean isZX, boolean isWheelBAse) {
        this.opMode = opMode;
        this.isHorSlider = isHorSlider;
        this.isVertSlider = isVertSlider;
        this.isZX = isZX;
        this.isWheelBAse = isWheelBAse;
    }
    Zx zx;
    VerticalSlider verticalSlider;
    HorizontSlider horizontSlider;
    WheelBase wheelBase;
    public void start_telemetry(){
        Telemetry telemetry = opMode.telemetry;
        if(isZX){
            telemetry.addData("zx pos:", zx.zx.getPower());
            telemetry.addData("krut pos:", zx.krut.getPower());
        }
        if (isWheelBAse){
            telemetry.addData("rbspeed", wheelBase.rbSpeed);
            telemetry.addData("rfspeed", wheelBase.rfSpeed);
            telemetry.addData("lbspeed", wheelBase.lbSpeed);
            telemetry.addData("lfspeed", wheelBase.lfSpeed);
            telemetry.addData("rbtick", wheelBase.rightBack.getCurrentPosition());
            telemetry.addData("rftick", wheelBase.rightFront.getCurrentPosition());
            telemetry.addData("lbtick", wheelBase.leftBack.getCurrentPosition());
            telemetry.addData("lftick", wheelBase.leftFront.getCurrentPosition());
            telemetry.addData("rightBack", wheelBase.rightBack.getPower());
            telemetry.addData("leftBack", wheelBase.leftBack.getPower());
            telemetry.addData("rightFront", wheelBase.rightFront.getPower());
            telemetry.addData("leftFront", wheelBase.leftFront.getPower());
            telemetry.addData("rightBackCurr", wheelBase.rightBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftBackCurr", wheelBase.leftBack.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("rightFrontCurr", wheelBase.rightFront.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("leftFrontCurr", wheelBase.leftFront.getCurrent(CurrentUnit.AMPS));
        }
        if (isVertSlider){
            telemetry.addData("pod power:", verticalSlider.RunPowers[0]);
            telemetry.addData("kleshna power:", verticalSlider.RunPowers[1]);
            telemetry.addData("vrash power:", verticalSlider.RunPowers[2]);
        } if (isHorSlider){
            telemetry.addData("horL power:", horizontSlider.sL);
            telemetry.addData("horR power:", horizontSlider.sR);
        }
        telemetry.update();
    }
}
