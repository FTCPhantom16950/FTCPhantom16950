package org.firstinspires.ftc.teamcode.own.Actions;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public class PodemAction extends Action {
    private static DcMotorEx podem;
    private boolean podnyat = false;
    @Override
    public void execute() {
        podem = Robot.get("podem", DcMotorEx.class);
        while(opMode.opModeIsActive()){
            if (gamepadDriver.dpad_up ) {
                podem.setPower(1);
                if (!soundPlaying) {
                    soundPlaying = true;
                    int soundID = myApp.getResources().getIdentifier("korol_lich", "raw", myApp.getPackageName());
                    PhantomOpMode.addData("playing", soundID);
                    SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                            new Runnable() {
                                public void run() {
                                    soundPlaying = false;
                                }
                            });
                }

            }else if(gamepadDriver.dpad_down){
                podem.setPower(-1);
                if (!soundPlaying) {
                    soundPlaying = true;
                    int soundID = myApp.getResources().getIdentifier("korol_lich", "raw", myApp.getPackageName());
                    PhantomOpMode.addData("playing", soundID);
                    SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                            new Runnable() {
                                public void run() {
                                    soundPlaying = false;
                                }
                            });
                }

            }else{
                podem.setPower(0.01);
            }

        }
    }
}
