package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lb;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.lf;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rb;
import static org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase.rf;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase;
import org.firstinspires.ftc.teamcode.own.Utils.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;


public class DriveAction extends Action {
    PhantomOpMode opMode;
    public DriveAction(PhantomOpMode OpMode) {
        addNecessaryMechanism(new WheelBase(OpMode.hardwareMap));
        this.opMode = OpMode;
    }

    @Override
    public void execute() {
        while (opMode.opModeIsActive()) {
            double x, y, spin;
            x = opMode.gamepad1.left_stick_x * 1.1;
            y = -opMode.gamepad1.left_stick_y;
            spin = opMode.gamepad1.right_stick_x;
            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(spin), 1);
            double frontLeftPower = (y + x + spin) / denominator;
            double backLeftPower = (y - x + spin) / denominator;
            double frontRightPower = (y - x - spin) / denominator;
            double backRightPower = (y + x - spin) / denominator;
            lf.setPower(frontLeftPower);
            lb.setPower(backLeftPower);
            rf.setPower(frontRightPower);
            rb.setPower(backRightPower);
        }
    }
}
