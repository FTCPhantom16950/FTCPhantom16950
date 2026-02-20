package org.firstinspires.ftc.teamcode.own.actions.testactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;

import org.firstinspires.ftc.teamcode.own.utils.Positions;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfCrServo;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;
@Configurable
@Config
public class CaptureTestAction implements Action {
    public static double palPower = -0.7;
    public boolean capturing = false, uncapturing = false;
    public static double kV = 1.0 / 6000, kA = 0.06, kP = 0, kI = 0, kD= 0, df = 0.5, output = 0, motorVelocity = 0, target = 6000;
    private final FullRegulator fullRegulator = new FullRegulator(kV,kA,kP,kD,kI,df,output,target,motorVelocity);
    @Override
    public void execute() throws InterruptedException {
        Robot.INSTANCE.addData("removed", false);
        SfCrServo pal = Robot.INSTANCE.get(SfCrServo.class, "pal");
        SfMotor capture = Robot.INSTANCE.get(SfMotor.class, "capture");
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            if (Robot.INSTANCE.gamepadOperator.x){
                capturing = !capturing;
                Robot.INSTANCE.opMode.sleep(300);
            }
            else if (Robot.INSTANCE.gamepadOperator.y){
                uncapturing = !uncapturing;
                Robot.INSTANCE.opMode.sleep(300);
            }
            if (Robot.INSTANCE.gamepadOperator.left_trigger >= 0.5f || Robot.INSTANCE.getData(Boolean.class, "shoot")){
                pal.setPower(palPower);
                if (Robot.INSTANCE.balls.containsKey(Robot.INSTANCE.getData(Positions.class,"positionSpin"))){
                    Robot.INSTANCE.balls.remove(Robot.INSTANCE.getData(Positions.class,"positionSpin"));
                    Robot.INSTANCE.addData("removed", true);
                }
            } else{
                pal.setPower(0);
            }
            if (capturing) {
                fullRegulator.setTarget(target);
            } else if (uncapturing) {
                fullRegulator.setTarget(-target);
            } else {
                fullRegulator.setTarget(0);
            }
            fullRegulator.setkA(kA);
            fullRegulator.setkV(kV);
            fullRegulator.setkP(kP);
            fullRegulator.setkD(kD);
            fullRegulator.setkI(kI);
            fullRegulator.setDerivativeFilter(df);
            fullRegulator.setMotorVelocity(capture.getVelocity());
            output = fullRegulator.calculate();
            capture.setPower(output);
        }
    }
}
