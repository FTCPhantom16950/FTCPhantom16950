package org.firstinspires.ftc.teamcode.Own.Actions.TeleOPActions;

import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.myApp;
import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.params;
import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.soundPlaying;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Regulators.FeedForwardController;
import org.firstinspires.ftc.teamcode.Own.Utils.Regulators.PIDFController;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

@Configurable
@Config
public class ShootAction extends Action {
    public static double kp=0.0025,ki=0,kd=0.0001,ks=0.195,kv=1/6200.0,kf=0, derivativeFilter = 0.65, target=0, output = 0;
    private final PIDFController pidfController = new PIDFController(kf,ki,kd,kp);
    private FeedForwardController feedForwardController = new FeedForwardController(kv,ks);
    public static double spin = 115;
    boolean makeShoot = false;
    DcMotorEx shootMotor;
    Thread thread = new Thread(() ->
    {
        while (Robot.opMode.opModeIsActive()){
            if (!soundPlaying && PhantomMath.convertToRPM(shootMotor.getVelocity(), 28) >= 3100){
                soundPlaying = true;
                int soundID = myApp.getResources().getIdentifier("pusk_razresh", "raw", myApp.getPackageName());
                SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                        new Runnable() {
                            public void run() {
                                soundPlaying = false;
                            }} );
            }
        }
    });
    @Override
    public void execute() throws InterruptedException {
        boolean shootState = false;
        shootMotor = Robot.get("shoot", DcMotorEx.class);
//        CRServo vrash = Robot.get("vrash", CRServo.class);
        thread.start();
        while (Robot.opMode.opModeIsActive()) {
            if (Robot.gamepadOperator.b){
                makeShoot = !makeShoot;
                Robot.opMode.sleep(300);
            }
            if (makeShoot){
                target = 3400;
            }
            else {
                target = 0;
            }
            pidfController.setkP(kp);
            pidfController.setkF(kf);
            pidfController.setkD(kd);
            pidfController.setkI(ki);
            pidfController.setTarget(target);
            pidfController.setMotorVelocity(PhantomMath.convertToRPM(shootMotor.getVelocity(), 28));
            pidfController.setDerivativeFilter(derivativeFilter);
            feedForwardController.setTarget(target);
            feedForwardController.setkA(ks);
            feedForwardController.setkV(kv);
            output = feedForwardController.calculate() + pidfController.calculate();
            if (Robot.gamepadOperator.left_stick_button){
                shootState = !shootState;
            }
            if (shootState){
                spin = 145;
            }
            else{
                spin = 110;
            }
            shootMotor.setPower(output);
            spin = Range.clip(spin, 0, 270);
//            vrash.setPower(PhantomMath.servoCRPowerToDegrees(spin, 270));
            PhantomOpMode.addData("shootPower", output);
            PhantomOpMode.addData("target", target);
            PhantomOpMode.addData("shootSpeed", PhantomMath.convertToRPM(shootMotor.getVelocity(), 28));
//            PhantomOpMode.addData("servoPower", vrash.getPower());
            PhantomOpMode.addData("degree", spin);
        }

    }
}
