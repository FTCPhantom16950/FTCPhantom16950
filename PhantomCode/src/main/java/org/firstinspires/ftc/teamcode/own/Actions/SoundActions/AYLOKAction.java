package org.firstinspires.ftc.teamcode.own.Actions.SoundActions;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.ftccommon.SoundPlayer;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;

public class AYLOKAction extends Action {

    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            if (!soundPlaying){
                soundPlaying = true;
                int soundID = myApp.getResources().getIdentifier("korol_lich", "raw", myApp.getPackageName());
                PhantomOpMode.addData("playing",soundID);
                SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                        new Runnable() {
                            public void run() {
                                soundPlaying = false;
                            }} );
            }

        }
    }
}
