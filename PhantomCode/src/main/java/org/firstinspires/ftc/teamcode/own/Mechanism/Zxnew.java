package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sL;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.sR;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.startLeftPower;
import static org.firstinspires.ftc.teamcode.own.Mechanism.HorizontSlider.startRightPower;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.KLESH_OTPUSK_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.verx_color;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PhMath;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;
@com.acmerobotics.dashboard.config.Config
public class Zxnew{
    public static double gain = 103;
    static LinearOpMode opMode;
    public static CRServo zx, brat, brat2, brat3;
    HardwareMap hw;

    public Zxnew(LinearOpMode opMode) {
        this.opMode = opMode;

    }

    public static RevColorSensorV3 colorSensor;
    public static ZxPos.KRUT krutpos;
    public static ZxPos.ZX zxpos;
    public static ZxPos.POVOROT povorot;
    public static final double brat_ZX= 90,
    brat2_ZX = 270,
    brat3_Hor = 155,
    brat3_Vert = 50,
            vrash_peredacha = 52;
    public static final double KRUT_START_POWER = PhMath.fromDegreesToPower(10,270);
    public static final double KRUT_2_START_POWER = PhMath.fromDegreesToPower(190,270);
    public static final double ZX_START_POWER = PhMath.fromDegreesToPower(98, 270),
    ZX_CAPTURE_POWER = PhMath.fromDegreesToPower(200, 270);
    public static double g = 0;
    static double  zxpower, krut_power;
    public static boolean not = false;
    public static boolean zxgo, krutgo, povGo;
    public boolean inited = false;
    public static boolean captured = false;
    public static boolean peredacha_at_work = false;

    public void init() {
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        brat = opMode.hardwareMap.get(CRServo.class, "krut");
        brat2 = opMode.hardwareMap.get(CRServo.class, "vrash2");
        brat3 = opMode.hardwareMap.get(CRServo.class, "vrash3");
        zx.setPower(ZX_START_POWER);
        brat3.setPower(PhMath.fromDegreesToPower(155, 270));
        brat.setPower(KRUT_START_POWER);
        brat2.setPower(KRUT_2_START_POWER);
        krutpos = ZxPos.KRUT.POXOD;
        zxpos = ZxPos.ZX.ZAXVAT;
        inited = true;
    }

    public void play1() {
        if (zxpos == ZxPos.ZX.ZAXVAT && zxgo) {
            zx.setPower(ZX_START_POWER);
            zxgo = false;
            captured = true;
        } else if (zxpos == ZxPos.ZX.OTPUSK && zxgo) {
            zx.setPower(ZX_CAPTURE_POWER);
            zxgo = false;
            captured = false;
        }


    }
    public void play2(){
        if(povGo) {
            switch (povorot) {
                case ZxPos.POVOROT.Horizont:
                    brat3.setPower(PhMath.fromDegreesToPower(brat3_Hor, 270));
                    povGo = false;
                    break;
                case ZxPos.POVOROT.Verticaaaaallll:
                    brat3.setPower(PhMath.fromDegreesToPower(brat3_Vert, 270));
                    povGo = false;
                    break;
            }
        }
    }
    public void play() {
        if (krutpos == ZxPos.KRUT.ZAXVAT && krutgo && !Config.actionInWork) {
            brat.setPower(PhMath.fromDegreesToPower(brat_ZX, 270));
            brat2.setPower(PhMath.fromDegreesToPower(brat2_ZX, 270));
            zxpos = ZxPos.ZX.OTPUSK;
            zxgo = true;
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.PEREDACHA && krutgo && !Config.actionInWork) {
            peredacha();
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.POXOD && krutgo && !Config.actionInWork) {
            brat.setPower(KRUT_START_POWER);
            brat2.setPower(KRUT_2_START_POWER);
            zxpower = 0.5;
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.Sputnik && krutgo && !Config.actionInWork) {
            brat.setPower(PhMath.fromDegreesToPower(80,270));
            brat2.setPower(KRUT_2_START_POWER);
            zxgo = true;
            krutgo = false;
        }
    }

    public void run() {
        krut_power = brat2.getPower();
        //gorizontal
        play();
        play1();
        play2();

    }
public static void peredacha(){
    peredacha_at_work = true;
    klesh.setPower(KLESH_OTPUSK_POWER);
    vrash.setPower(PhMath.fromDegreesToPower(vrash_peredacha, 270));
    zx.setPower(ZX_START_POWER);
    opMode.sleep(400);
    brat.setPower(PhMath.fromDegreesToPower(70, 270));
    brat2.setPower(PhMath.fromDegreesToPower(20, 270));
    opMode.sleep(800);
    sL.setPower(startLeftPower - 0.2);
    sR.setPower(startRightPower + 0.2);
    opMode.sleep(300);
    zx.setPower(ZX_START_POWER-0.1);
    opMode.sleep(300);
    zx.setPower(0.1);
    if (verx_color.getDistance(DistanceUnit.MM) <= 28) {
        VerticalSlider.captured = true;
    } else {
        VerticalSlider.captured = false;
    }
    opMode.sleep(1000);
    klesh.setPower(-0.35);
    sL.setPower(startLeftPower);
    sR.setPower(startRightPower);
    brat.setPower(KRUT_START_POWER);
    brat2.setPower(KRUT_2_START_POWER);
    opMode.sleep(300);
    zx.setPower(ZX_START_POWER);
    krutgo = false;
    peredacha_at_work = false;
    }
    public void newZxAuto(){
        peredacha_at_work = true;
        zx.setPower(ZX_CAPTURE_POWER);
        klesh.setPower(KLESH_OTPUSK_POWER);
        brat3.setPower(PhMath.fromDegreesToPower(brat3_Hor, 270));
        opMode.sleep(200);
        brat.setPower(PhMath.fromDegreesToPower(brat_ZX, 270));
        brat2.setPower(PhMath.fromDegreesToPower(brat2_ZX, 270));
        opMode.sleep(650);
        HorizontSlider.zaxvat();
        klesh.setPower(-0.35);
        peredacha_at_work = false;
    }
    public void newZx3Auto(){
        zx.setPower(ZX_CAPTURE_POWER);
        klesh.setPower(KLESH_OTPUSK_POWER);
        brat3.setPower(PhMath.fromDegreesToPower(230, 270));
        opMode.sleep(400);
        brat.setPower(PhMath.fromDegreesToPower(brat_ZX, 270));
        brat2.setPower(PhMath.fromDegreesToPower(brat2_ZX, 270));
        opMode.sleep(650);
        zx.setPower(ZX_START_POWER);
        HorizontSlider.zaxvat();
        klesh.setPower(-0.35);
    }

}