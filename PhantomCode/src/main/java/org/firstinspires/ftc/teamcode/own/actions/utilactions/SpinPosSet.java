package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Positions;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;

public class SpinPosSet implements Action {
    public Positions position = Positions.CENTER;
    public static int centerDegree = 270, leftDegree = 145, rightDegree = 0;

    @Override
    public void execute() throws InterruptedException {
        boolean once = false;
        Robot.INSTANCE.addData("once", once);
        SfCrServo spin = Robot.INSTANCE.get(SfCrServo.class, "spinServo");
        while (Robot.INSTANCE.opMode.opModeIsActive()) {
            once = Robot.INSTANCE.getData(Boolean.class, "once");
            position = Robot.INSTANCE.getData(Positions.class, "positionSpin");
            if (position == Positions.LEFT && !once) {
                Robot.INSTANCE.addData("once", true);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
            } else if (position == Positions.RIGHT && !once) {
                Robot.INSTANCE.addData("once", true);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(rightDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
            } else if (position == Positions.CENTER && !once) {
                Robot.INSTANCE.addData("once", true);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(leftDegree, 270));
                Robot.INSTANCE.opMode.sleep(300);
                spin.setPower(PhantomMath.servoCRPowerToDegrees(centerDegree, 270));
                Robot.INSTANCE.opMode.sleep(500);
            }
        }
    }
}
