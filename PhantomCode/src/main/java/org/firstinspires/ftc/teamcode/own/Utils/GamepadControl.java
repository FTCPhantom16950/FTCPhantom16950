package org.firstinspires.ftc.teamcode.own.Utils;

import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.zaxvat;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.vidvig;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.horGO;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.horPos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.pod;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.verticalPOS;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrashPower;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.peredacha;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.zxpos;
import static org.firstinspires.ftc.teamcode.own.positions.HorSliderPos.HorPos.SLOZ;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider;
import org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew;
import org.firstinspires.ftc.teamcode.own.positions.HorSliderPos;
import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

public class GamepadControl extends Thread {
    LinearOpMode opMode;

    public GamepadControl(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public void run() {
        super.run();
        while (opMode.opModeIsActive()){
        if(opMode.gamepad2.right_stick_y > 0){
           Zxnew.povorot = ZxPos.POVOROT.Horizont;
            Zxnew.povGo = true;
        } else if (opMode.gamepad2.right_stick_y < 0) {
            Zxnew.povorot = ZxPos.POVOROT.Verticaaaaallll;
            Zxnew.povGo = true;
        }
        if (opMode.gamepad2.right_bumper) {
            if (zxpos == ZxPos.ZX.ZAXVAT) {
                zxpos = ZxPos.ZX.OTPUSK;
                opMode.sleep(300);
                Zxnew.zxgo = true;
            } else if (zxpos == ZxPos.ZX.OTPUSK) {
                zxpos = ZxPos.ZX.ZAXVAT;
                opMode.sleep(300);
                Zxnew.zxgo = true;
            }
        }
        if (opMode.gamepad2.y) {
            Zxnew.krutpos = ZxPos.KRUT.ZAXVAT;
            zxpos = ZxPos.ZX.OTPUSK;
            opMode.sleep(200);
            Zxnew.zxgo = true;
            Zxnew.krutgo = true;
        } else if (opMode.gamepad2.a) {
            Zxnew.krutpos = ZxPos.KRUT.PEREDACHA;
            opMode.sleep(200);
            Zxnew.krutgo = true;
        } else if (opMode.gamepad2.b) {
            Zxnew.krutpos = ZxPos.KRUT.POXOD;
            opMode.sleep(100);
            Zxnew.krutgo = true;
        } else if (opMode.gamepad2.x) {
            Zxnew.krutpos = ZxPos.KRUT.Sputnik;
            opMode.sleep(100);
            Zxnew.krutgo = true;
        }
        if(opMode.gamepad2.dpad_up){
            if (pod.getPower() <= 1){
                pod.setPower(1);
            } else {
                pod.setPower(1);
            }
        } else if (opMode.gamepad2.dpad_down) {
            if (pod.getPower() >= -1){
                pod.setPower(-1);
            } else {
                pod.setPower(-1);
            }
        } else {
            pod.setPower(0.15);
        }
        if(opMode.gamepad2.dpad_right){
            vrashPower = Range.clip(vrashPower + 0.1, PhMath.fromDegreesToPower(40, 270),1);
            vrash.setPower(vrashPower);
        } else if (opMode.gamepad2.dpad_left) {
            vrashPower = PhMath.fromDegreesToPower(60, 270);
            vrash.setPower(vrashPower);
        }
        if(opMode.gamepad2.left_bumper) {
            if (verticalPOS == VerticalPOS.KLESHPOS.ZAXVAT){
                verticalPOS = VerticalPOS.KLESHPOS.OTPUSK;
                opMode.sleep(200);
                VerticalSlider.kleshgo = true;
            } else if (verticalPOS == VerticalPOS.KLESHPOS.OTPUSK) {
                verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
                opMode.sleep(200);
                VerticalSlider.kleshgo = true;
            }
        }
        if (opMode.gamepad2.right_stick_button){
            horPos = SLOZ;
            opMode.sleep(200);
            horGO = true;
        } else if (opMode.gamepad2.left_stick_button){
            horPos = HorSliderPos.HorPos.VIDVIG;
            opMode.sleep(200);
            horGO = true;
        }
        if (opMode.gamepad2.right_trigger == 1){
            vidvig();
        } else if (opMode.gamepad2.left_trigger == 1) {
            zaxvat();
            peredacha();
        }
        }
    }
}
