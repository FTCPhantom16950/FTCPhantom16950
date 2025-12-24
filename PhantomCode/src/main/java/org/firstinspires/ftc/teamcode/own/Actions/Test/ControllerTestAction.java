package org.firstinspires.ftc.teamcode.own.Actions.Test;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.FeedForwardController;
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDFController;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

@Configurable
@Config
public class ControllerTestAction extends Action {
    public static int MODE = 0;
    public static double kp=0,ki=0,kd=0,ks=0,kv=0,kf=0, derivativeFilter = 0.5, target, output = 0;
    private final PIDFController pidfController = new PIDFController(kf,ki,kd,kp);
    private FeedForwardController feedForwardController = new FeedForwardController(kv,ks);
    @Override
    public void execute() throws InterruptedException {
        DcMotorEx shoot = Robot.get("shoot", DcMotorEx.class);
        while (Robot.opMode.opModeIsActive()){
            switch (MODE){
                case 1:
                    pidfController.setkP(kp);
                    pidfController.setkF(kf);
                    pidfController.setkD(kd);
                    pidfController.setkI(ki);
                    pidfController.setTarget(target);
                    pidfController.setMotorVelocity(PhantomMath.convertToRPM(shoot.getVelocity(), 28));
                    pidfController.setDerivativeFilter(derivativeFilter);
                    output = pidfController.calculate();
                    break;
                case 2:
                    feedForwardController.setTarget(target);
                    feedForwardController.setkA(ks);
                    feedForwardController.setkV(kv);
                    output = feedForwardController.calculate();
                    break;
                case 3:
                    pidfController.setkP(kp);
                    pidfController.setkF(kf);
                    pidfController.setkD(kd);
                    pidfController.setkI(ki);
                    pidfController.setTarget(target);
                    pidfController.setMotorVelocity(PhantomMath.convertToRPM(shoot.getVelocity(), 28));
                    pidfController.setDerivativeFilter(derivativeFilter);
                    feedForwardController.setTarget(target);
                    feedForwardController.setkA(ks);
                    feedForwardController.setkV(kv);
                    output = feedForwardController.calculate() + pidfController.calculate();
                default:

                    break;
            }
            shoot.setPower(output);
        }
    }
}
