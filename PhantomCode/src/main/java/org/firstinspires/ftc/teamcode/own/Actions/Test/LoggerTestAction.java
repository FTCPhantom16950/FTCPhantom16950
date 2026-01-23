package org.firstinspires.ftc.teamcode.Own.Actions.Test;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.Own.Utils.Robot;
import org.psilynx.psikit.core.Logger;

public class LoggerTestAction extends Action {
    @Override
    public void execute() throws InterruptedException {
        while (Robot.opMode.opModeIsActive()){
            double beforeUserStart = Logger.getTimestamp();
            Logger.periodicBeforeUser();
            double beforeUserEnd = Logger.getTimestamp();
            Robot.opMode.sleep(200);
            double afterUserStart = Logger.getTimestamp();
            Logger.periodicAfterUser(
                    afterUserStart - beforeUserEnd,
                    beforeUserEnd - beforeUserStart
            );
        }
    }
}
