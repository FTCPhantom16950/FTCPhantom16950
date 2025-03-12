package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.KRUT_2_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.KRUT_START_POWER;
import static org.firstinspires.ftc.teamcode.FORTEST.Zx.brat;
import static org.firstinspires.ftc.teamcode.FORTEST.Zx.brat2;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.krutgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.krutpos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.peredacha;
import static org.firstinspires.ftc.teamcode.FORTEST.Zx.zx;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.zxgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.zxpos;
import static org.firstinspires.ftc.teamcode.own.positions.HorSliderPos.HorPos.SLOZ;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;
import org.firstinspires.ftc.teamcode.own.positions.HorSliderPos;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;
@com.acmerobotics.dashboard.config.Config
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
        sl_power = startLeftPower;
        sr_power = startRightPower;
        inited = true;
        horPos = SLOZ;
        while (opMode.opModeInInit()){
            sL.setPower(startLeftPower);
            sR.setPower(startRightPower);
        }
    }
    public void play(){
        switch(horPos) {
            case SLOZ:
                if(horGO && !Config.ACTIONINWORK){
                    sL.setPower(startLeftPower - 0.01);
                    sR.setPower(startRightPower);
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
        if (opMode.gamepad2.left_trigger == 1){
            vidvig();
        } else if (opMode.gamepad2.right_trigger == 1) {
            zaxvat();
        }
    }

    public  void vidvig() {
        horPos = HorSliderPos.HorPos.VIDVIG;
        horGO = true;
        zxpos = ZxPos.ZX.OTPUSK;
        zxgo = true;
        opMode.sleep(500);
        krutpos = ZxPos.KRUT.ZAXVAT;
        krutgo = true;

    }
    public static void vidvigAuto(){
        sL.setPower(0.3);
        sR.setPower(-0.3);
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
        klesh.setPower(-0.13);
        Zxnew.zx.setPower(0.6);
        opMode.sleep(200);
        Zxnew.brat.setPower(KRUT_START_POWER);
        Zxnew.brat2.setPower(KRUT_2_START_POWER);
            sL.setPower(0 - 0.01);
            sR.setPower(0);
            opMode.sleep(1000);
           peredacha();
        krutgo = false;
    }

}

