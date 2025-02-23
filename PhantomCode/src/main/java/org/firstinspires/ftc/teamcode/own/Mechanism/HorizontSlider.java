package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.KRUT_2_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.KRUT_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.brat2;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.krutpos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.peredacha;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zx;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zxgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zx.zxpos;
import static org.firstinspires.ftc.teamcode.own.positions.HorSliderPos.HorPos.SLOZ;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;
import org.firstinspires.ftc.teamcode.own.positions.HorSliderPos;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

public class HorizontSlider {
    LinearOpMode opMode;
    public static CRServo sL, sR;
    HardwareMap hw;
    public boolean inited = false;
    static HorSliderPos.HorPos horPos;
    public HorizontSlider(LinearOpMode opMode) {
        this.opMode = opMode;
        // this.setDaemon(true);
    }
    public static double startLeftPower = 0, startRightPower = 0, sl_power, sr_power;
    public static boolean horGO = false;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        sL.setPower(startLeftPower);
        sR.setPower(startRightPower);
        sl_power = startLeftPower;
        sr_power = startRightPower;
        inited = true;
        horPos = SLOZ;
    }
    public void play(){
        switch(horPos) {
            case SLOZ:
                if(horGO && !Config.ACTIONINWORK){
                    sL.setPower(-0.1- 0.01);
                    sR.setPower(0.1);
                    horGO = false;
                }
                break;
            case VIDVIG:
                if(horGO && !Config.ACTIONINWORK){
                    sL.setPower(0.48);
                    sR.setPower(-0.48);
                    horGO = false;
                }
                break;
        }
    }

    public TeleOpActions teleOpActions = new TeleOpActions() {
        @Override
        public void play() {
            moving();
        }
    };
    public void moving(){
        play();
        if (opMode.gamepad2.right_stick_button){
            horPos = SLOZ;
            opMode.sleep(200);
            horGO = true;
        } else if (opMode.gamepad2.left_stick_button){
            horPos = HorSliderPos.HorPos.VIDVIG;
            opMode.sleep(200);
            horGO = true;
        }
        if (opMode.gamepad2.right_trigger!= 0.0){
            vidvig();
        } else if (opMode.gamepad2.left_trigger!= 0.0) {
            zaxvat();
        }
    }

    public  void vidvig(){
        horPos = HorSliderPos.HorPos.VIDVIG;
        krutpos = ZxPos.KRUT.ZAXVAT;
        zxpos = ZxPos.ZX.OTPUSK;
        krutgo = true;
        zxgo = true;
        horGO = true;
    }
    public static void vidvigAuto(){
        sL.setPower(0.43);
        sR.setPower(-sL.getPower());
    }
    public static void nepolniVidvig(){
        sL.setPower(0.32);
        sR.setPower(-sL.getPower());
}
    public static void sloz(){
        sL.setPower(0);
        sR.setPower(0);
    }

    public void zaxvat(){
        zx.setPower(0.23);
        opMode.sleep(200);
        brat.setPower(KRUT_START_POWER);
        brat2.setPower(KRUT_2_START_POWER);
            sL.setPower(0 - 0.01);
            sR.setPower(0);
            opMode.sleep(1000);
           peredacha();
        krutgo = false;

    }

}

