package org.firstinspires.ftc.teamcode.own.Actions;
import static org.firstinspires.ftc.teamcode.own.Utils.PhantomMath.*;

import org.firstinspires.ftc.teamcode.own.Mechanism.CameraMechanism;
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.GamepadControl;
import org.firstinspires.ftc.teamcode.own.Utils.Robot;

import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

public class CameraAction extends Action {
    private static CRServo verticalCameraServo, horizontalCameraServo;

    public CameraAction() {
    }
    int i = 135, j = 0;
    @Override
    public void execute() {
        verticalCameraServo = Robot.get("vcs", CRServo.class);
        horizontalCameraServo = Robot.get("hcs", CRServo.class);
        while (opMode.opModeIsActive()){
            if (GamepadControl.Companion.getGamepadDriver().a){
                i = i + 10;
                opMode.sleep(500);
            } else if(GamepadControl.Companion.getGamepadDriver().y){
                i = i - 10;
                opMode.sleep(500);
            }
            if (GamepadControl.Companion.getGamepadDriver().x) {
                j = j + 10;
                opMode.sleep(500);
            } else if (GamepadControl.Companion.getGamepadDriver().b) {
                j -= 10;
                opMode.sleep(500);
            }
            i = Range.clip(i,0,270);
            j = Range.clip(j,0,270);
            CameraMechanism.getArtifactProcessor().setCameraRot(new float[]{0,j,i});
            verticalCameraServo.setPower(servoCRPowerToDegrees(i,270));
            horizontalCameraServo.setPower(servoCRPowerToDegrees(j,270));
            opMode.telemetry.addData("vsc", i);
            opMode.telemetry.addData("vsc", verticalCameraServo.getPower());
            opMode.telemetry.addData("hsc", j);
            opMode.telemetry.addData("hsc", horizontalCameraServo.getPower());
            opMode.telemetry.update();
        }

    }
}
