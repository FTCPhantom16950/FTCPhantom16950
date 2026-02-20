package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import org.firstinspires.ftc.teamcode.own.utils.Positions;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

public class CapturePosAction implements Action {
    public static double palPower = -0.7;
    @Override
    public void execute() throws InterruptedException {
        SfCrServo pal = Robot.INSTANCE.get(SfCrServo.class, "pal");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            if (Robot.INSTANCE.getData(Boolean.class, "shoot")){
                pal.setPower(palPower);
                if (Robot.INSTANCE.balls.containsKey(Robot.INSTANCE.getData(Positions.class,"positionSpin"))){
                    Robot.INSTANCE.balls.remove(Robot.INSTANCE.getData(Positions.class,"positionSpin"));
                    Robot.INSTANCE.addData("removed", true);
                }
                Robot.INSTANCE.opMode.sleep(500);
            } else{
                pal.setPower(0);
            }
        }
    }
}
