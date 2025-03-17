package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.KRUT_2_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.ZX_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.brat;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.brat2;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.brat3;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.brat3_Hor;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.krutgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.krutpos;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.peredacha;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.zxgo;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.zxpos;
import static org.firstinspires.ftc.teamcode.own.positions.HorSliderPos.HorPos.SLOZ;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PhMath;
import org.firstinspires.ftc.teamcode.own.Utils.TeleOpActions;
import org.firstinspires.ftc.teamcode.own.positions.HorSliderPos;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;
@com.acmerobotics.dashboard.config.Config
public class HorizontSlider {
    static LinearOpMode opMode;
    public static CRServo sL, sR;
    HardwareMap hw;
    public boolean inited = false;
    public static HorSliderPos.HorPos horPos;
    public HorizontSlider(LinearOpMode opMode) {
        this.opMode = opMode;
        // this.setDaemon(true);
    }
    Thread thr = new Thread(() -> {
        while (opMode.opModeInInit()){
            sL.setPower(startLeftPower);
            sR.setPower(startRightPower);
        }
    });
    public static double startLeftPower = -0.062, startRightPower = 0, sl_power, sr_power;
    public static boolean horGO = false;
    public void init() {
        hw = opMode.hardwareMap;
        sL = opMode.hardwareMap.get(CRServo.class, "horL");
        sR = opMode.hardwareMap.get(CRServo.class, "horR");
        sl_power = startLeftPower;
        sr_power = startRightPower;
        inited = true;
        horPos = SLOZ;
        thr.start();
    }
    public void play(){
        switch(horPos) {
            case SLOZ:
                if(horGO && !Config.ACTIONINWORK){
                    sL.setPower(startLeftPower);
                    sR.setPower(startRightPower);
                    horGO = false;
                }
                break;
            case VIDVIG:
                if(horGO && !Config.ACTIONINWORK){
                    sL.setPower(0.48 - 0.05);
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

    }

    public static void vidvig() {
        sL.setPower(0.48 - 0.05);
        sR.setPower(-0.48);
        opMode.sleep(300);
        krutpos = ZxPos.KRUT.ZAXVAT;
        krutgo = true;
        opMode.sleep(300);
        zxpos = ZxPos.ZX.OTPUSK;
        zxgo = true;
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

    public static void  zaxvat(){
        brat3.setPower(PhMath.fromDegreesToPower(brat3_Hor, 270));
        opMode.sleep(100);
        Zxnew.zx.setPower(ZX_START_POWER);
        opMode.sleep(300);
            brat.setPower(PhMath.fromDegreesToPower(80,270));
            brat2.setPower(KRUT_2_START_POWER);
            sL.setPower(0 - 0.05);
            sR.setPower(0);
            opMode.sleep(500);
           peredacha();
        krutgo = false;
    }

}

