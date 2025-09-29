package org.firstinspires.ftc.teamcode.own.Actions;

import static org.firstinspires.ftc.teamcode.own.Mechanism.PidKal.xyn;
import static org.firstinspires.ftc.teamcode.own.Utils.GamepadControl.gamepadDriver;
import static org.firstinspires.ftc.teamcode.own.Utils.PhantomMath.makeLinearToCubic;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Mechanism.PidKal;
import org.firstinspires.ftc.teamcode.own.Utils.Action.InterruptibleAction;
import org.firstinspires.ftc.teamcode.own.Utils.GamepadControl;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
@Config
public class FunAction extends InterruptibleAction {
    public static double i = 0;
    public static int a = 1;
    @Override
    public void run() {
        if (gamepadDriver.left_bumper){
            i = Range.clip(i+0.001,-1,1);
            opMode.sleep(500);
        }
        if(gamepadDriver.right_bumper) {
            i = Range.clip(i-0.001, -1, 1);
            opMode.sleep(500);
        }
        xyn.setPower(i);
        if (gamepadDriver.a){
            isInterrupted = true;
        }
    }

    @Override
    public void handleInterrupt() {
        xyn.setPower(-1);
    }

    public FunAction(PhantomOpMode opMode) {
        super(opMode);
        this.opMode = opMode;
    }
    private PhantomOpMode opMode;
}
