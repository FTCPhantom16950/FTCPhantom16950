package org.firstinspires.ftc.teamcode.FORTEST.opModes;

import androidx.core.view.WindowInsetsAnimationCompat;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@TeleOp
public class TetsDED extends LinearOpMode {
    DcMotor leftF, leftB,rightB,rightF;
    public void  drive_tp(){
            float StickX = (gamepad1.right_stick_x);
            float StickY = (gamepad1.right_stick_y);
            float pwrTrigger = (gamepad1.left_trigger);
            float pwrTrigger2 = (gamepad1.right_trigger);
            float pwrTrigger6 = (gamepad2.left_trigger);
            float pwrTrigger5 = (gamepad2.right_trigger);
            float pwrTrigger3 = (float) (gamepad2.left_trigger * 0.66);
            float pwrTrigger4 = (float) (gamepad2.right_trigger * 0.66);
            boolean Bumper_left = (gamepad1.left_bumper);
            boolean Bumper_right = (gamepad1.right_bumper);
            float Stick2X = (float) (gamepad1.left_stick_x * 0.3);
            float Stick2Y = (float) (gamepad1.left_stick_y * 0.3);
            if (StickY != 0 || StickX != 0) {
                leftF.setPower((+StickY - StickX) + pwrTrigger);
                leftB.setPower((+StickY + StickX) + pwrTrigger);
                rightB.setPower((-StickY + StickX) + pwrTrigger2);
                rightF.setPower((-StickY - StickX) - pwrTrigger2);
            } else if (Stick2Y != 0 || Stick2X != 0) {
                leftF.setPower((+Stick2Y - Stick2X) + pwrTrigger);
                rightB.setPower((-Stick2Y + Stick2X) + pwrTrigger2);
                rightF.setPower((-Stick2Y - Stick2X) + pwrTrigger2);
                leftB.setPower((+Stick2Y + Stick2X) + pwrTrigger);
            } else if (pwrTrigger != 0) {
                leftF.setPower(0.6 * pwrTrigger);
                rightB.setPower(0.6 * pwrTrigger);
                rightF.setPower(0.6 * pwrTrigger);
                leftB.setPower(0.6 * pwrTrigger);
            } else if (pwrTrigger2 != 0) {
                leftF.setPower(-0.6 * pwrTrigger2);
                rightB.setPower(-0.6 * pwrTrigger2);
                rightF.setPower(-0.6 * pwrTrigger2);
                leftB.setPower(-0.6 * pwrTrigger2);
            } else if (gamepad1.left_bumper) {
                leftF.setPower(0.4);
                rightB.setPower(0.4);
                rightF.setPower(0.4);
                leftB.setPower(0.4);
            } else if (gamepad1.right_bumper) {
                leftF.setPower(-0.4);
                rightB.setPower(-0.4);
                rightF.setPower(-0.4);
                leftB.setPower(-0.4);
            } else {
                leftF.setPower(0);
                rightB.setPower(0);
                rightF.setPower(0);
                leftB.setPower(0);
            }


    }
    @Override
    public void runOpMode() throws InterruptedException {
        leftF = hardwareMap.dcMotor.get("lf");
        leftB = hardwareMap.dcMotor.get("lb");
        rightF = hardwareMap.dcMotor.get("rf");
        rightB = hardwareMap.dcMotor.get("rb");
        waitForStart();
        while (opModeIsActive()){
            drive_tp();
        }
    }
}
