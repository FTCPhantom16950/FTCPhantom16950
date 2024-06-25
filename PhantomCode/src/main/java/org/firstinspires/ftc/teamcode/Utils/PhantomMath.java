package org.firstinspires.ftc.teamcode.Utils;

import org.firstinspires.ftc.teamcode.Camera.Basement.PhantomProcessor;

public class PhantomMath {
    public boolean leftPose, rightPose;
    public void pipeLine(PhantomProcessor cameraReworked){
        int valLeft = cameraReworked.valLeft;
        int valRight = cameraReworked.valRight;
        if (valLeft >= 122){
            leftPose = true;
        } else {
            leftPose = false;
        }
        if (valRight >= 122){
            rightPose = true;
        } else {
            rightPose = false;
        }
    }
}
