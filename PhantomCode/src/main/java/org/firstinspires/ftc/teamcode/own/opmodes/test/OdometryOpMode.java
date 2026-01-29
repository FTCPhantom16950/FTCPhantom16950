package org.firstinspires.ftc.teamcode.own.opmodes.test;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.own.actions.teleactions.NewWheelBaseAction;
import org.firstinspires.ftc.teamcode.own.mechanism.OdometryMechanism;
import org.firstinspires.ftc.teamcode.own.mechanism.WheelBaseMechanism;
import org.firstinspires.ftc.teamcode.own.utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;

@TeleOp(group = "test")

public class OdometryOpMode extends PhantomOpMode {
    @Override
    public void customOpModeSettings() {
//        mechanism.add(new WheelBaseMechanism());
        mechanism.add(new OdometryMechanism());
        actions = new Action() {
            @Override
            public void execute() throws InterruptedException {
                while (opModeIsActive()){

                }
            }
        };
    }
}
