package org.firstinspires.ftc.teamcode.eocvsim;

public class PhantomMath {
    public boolean leftPose, rightPose;
    public void pipeLine(CameraReworked cameraReworked){
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
