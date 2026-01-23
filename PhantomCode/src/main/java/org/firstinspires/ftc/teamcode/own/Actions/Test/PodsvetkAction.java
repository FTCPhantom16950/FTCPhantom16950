package org.firstinspires.ftc.teamcode.Own.Actions.Test;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomMath;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;

@Configurable
@Config
public class PodsvetkAction extends Action {
    public static boolean turnFunOn = false;
    @Override
    public void execute() throws InterruptedException {
        DigitalChannel digitalChannel = Robot.get("proz1", DigitalChannel.class);
        boolean toggle = false;
        while (Robot.opMode.opModeIsActive()){
            if (turnFunOn){
                if (Robot.gamepadDriver.x){
                    toggle = !toggle;
                }
                digitalChannel.setState(toggle ? !toggle : toggle );
            }
            else{
                digitalChannel.setState(true);
                Robot.opMode.sleep(1000);
                digitalChannel.setState(false);
                Robot.opMode.sleep(1000);
            }
            PhantomOpMode.addData("podsv", digitalChannel.getState());
        }
    }
}
