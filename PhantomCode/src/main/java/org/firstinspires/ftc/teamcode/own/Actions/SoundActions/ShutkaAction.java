package org.firstinspires.ftc.teamcode.Own.Actions.SoundActions;
import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.*;

import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.PhantomOpMode;

public class ShutkaAction extends Action {
    ElapsedTime timer;
    @Override
    public void execute() {
        timer = new ElapsedTime();
        while (opMode.opModeIsActive()){
            if (timer.seconds() >= 100){
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
                PhantomOpMode.addData("time", timer.seconds());
            }
        }
    }
}
