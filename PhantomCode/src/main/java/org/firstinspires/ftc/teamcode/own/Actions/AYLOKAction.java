package org.firstinspires.ftc.teamcode.own.Actions;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.ftccommon.SoundPlayer;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;

public class AYLOKAction extends Action {
    boolean soundPlaying = false;
    @Override
    public void execute() {
        while (opMode.opModeIsActive()){
            if (!soundPlaying){
                soundPlaying = true;
                int soundID = myApp.getResources().getIdentifier("korol_lich", "raw", myApp.getPackageName());
                multipleTelemetry.addData("Playing", soundID);
                SoundPlayer.getInstance().startPlaying(myApp, soundID, params, null,
                        new Runnable() {
                            public void run() {
                                soundPlaying = false;
                            }} );
            }

        }
    }
}
