package org.firstinspires.ftc.teamcode.Own.Actions.Test;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Regulators.FeedForwardController;
import org.firstinspires.ftc.teamcode.Own.Utils.Regulators.PIDFController;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;
import org.psilynx.psikit.core.Logger;

@Configurable
@Config
public class ControllerTestAction extends Action {
    public static int MODE = 3;
    public static double kp=0,ki=0.02,kd=0.0000001,ks=0.211,kv=1/6000.0,kf=0, derivativeFilter = 0.65, target= 500, output = 0;
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
            output = output * Robot.voltageCompenser;
            shoot.setPower(output);
            Logger.recordOutput("motor Velocity", PhantomMath.convertToRPM(shoot.getVelocity(), 28));
            PhantomOpMode.addData("motor Speed", shoot.getVelocity());
            Logger.recordOutput("motor Power", shoot.getPower());
            PhantomOpMode.addData("motor Power", shoot.getPower());
            PhantomOpMode.addData("motor Velocity", PhantomMath.convertToRPM(shoot.getVelocity(), 28));
        }
    }
}
