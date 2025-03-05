package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.kleshPower;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

public class Zxnew {
    public static double gain = 103;
    org.firstinspires.ftc.teamcode.own.Utils.Config config = new org.firstinspires.ftc.teamcode.own.Utils.Config();
    static LinearOpMode opMode;
    public static CRServo zx, brat, brat2, brat3;
    HardwareMap hw;

    public Zxnew(LinearOpMode opMode) {
        this.opMode = opMode;

    }

    public static RevColorSensorV3 colorSensor;
    public static ZxPos.KRUT krutpos;
    public static ZxPos.ZX zxpos;
    public static final double KRUT_START_POWER = -0.55;
    public static final double KRUT_2_START_POWER = -0.3;
    private static final double ZX_START_POWER = 0.6;
    public static double g = 0;
    static double  zxpower, krut_power;
    public static boolean not = false;
    public static boolean zxgo, krutgo;
    public boolean inited = false;
    public static boolean captured = false;
    public static boolean canBeCaptured = true;

    public void init() {
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        brat = opMode.hardwareMap.get(CRServo.class, "krut");
        brat2 = opMode.hardwareMap.get(CRServo.class, "vrash2");
        brat3 = opMode.hardwareMap.get(CRServo.class, "vrash3");
        zx.setDirection(DcMotorSimple.Direction.REVERSE);
        brat2.setDirection(DcMotorSimple.Direction.REVERSE);
        zx.setPower(ZX_START_POWER);
        brat3.setPower(.2);
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
            zx.setPower(-0.5);
            zxgo = false;
            captured = false;
        }


    }

    public void play() {
        if (krutpos == ZxPos.KRUT.AUto && krutgo && !Config.ACTIONINWORK) {
            brat.setPower(0.67);
            brat2.setPower(-0.3);
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.ZAXVAT && krutgo && !Config.ACTIONINWORK) {
            brat.setPower(-0.1
            );
            brat2.setPower(-1);
            zxpos = ZxPos.ZX.OTPUSK;
            zxgo = true;
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.PEREDACHA && krutgo && !Config.ACTIONINWORK) {
            peredacha();
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.POXOD && krutgo && !Config.ACTIONINWORK) {
            brat.setPower(KRUT_START_POWER);
            brat2.setPower(KRUT_2_START_POWER);
            zxpower = 0.5;
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.Sputnik && krutgo && !Config.ACTIONINWORK) {
            brat.setPower(0.67);
            brat2.setPower(0.42);
            krutgo = false;
        }
    }
    public void run() {
        krut_power = brat2.getPower();
        //gorizontal
        if (opMode.gamepad2.right_stick_y == -1) {
            zxpower = -0.2;
        }
        // vertyical
        if (opMode.gamepad2.right_stick_y == 1) {
            zxpower = 0.5;
        }
        brat3.setPower(zxpower);
        play();
        play1();
        if (opMode.gamepad2.right_bumper) {
            if (zxpos == ZxPos.ZX.ZAXVAT) {
                zxpos = ZxPos.ZX.OTPUSK;
                opMode.sleep(300);
                zxgo = true;
            } else if (zxpos == ZxPos.ZX.OTPUSK) {
                zxpos = ZxPos.ZX.ZAXVAT;
                opMode.sleep(300);
                zxgo = true;
            }
        }
        if (opMode.gamepad2.y) {
            krutpos = ZxPos.KRUT.ZAXVAT;
            zxpos = ZxPos.ZX.OTPUSK;
            opMode.sleep(200);
            zxgo = true;
            krutgo = true;
        } else if (opMode.gamepad2.a) {
            krutpos = ZxPos.KRUT.PEREDACHA;
            opMode.sleep(200);
            krutgo = true;
        } else if (opMode.gamepad2.b) {
            krutpos = ZxPos.KRUT.POXOD;
            opMode.sleep(100);
            if (zxpower != 0.5) {
                zxpower = 0.5;
                brat3.setPower(zxpower);
                opMode.sleep(200);
            }
            krutgo = true;
        }
    }
public static void peredacha(){
        kleshPower = -0.13;
        klesh.setPower(-0.13);
        zx.setPower(ZX_START_POWER);
        if(zxpower != 0.5){
            zxpower = 0.5;
            brat3.setPower(zxpower);
            opMode.sleep(300);
        }
        opMode.sleep(300);
        brat2.setPower(0.8);
        brat.setPower(-0.2);
        opMode.sleep(1000);
        zx.setPower(-0.5);
        opMode.sleep(400);
        klesh.setPower(-0.35);
        opMode.sleep(400);
        brat2.setPower(KRUT_2_START_POWER);
        brat.setPower(KRUT_START_POWER);
        zxpower = 0.5;
        brat3.setPower(zxpower);
        krutgo = false;
    }
    public void newZxAuto(){
        zx.setPower(-0.5);
        zxpower = 0.5;
        brat3.setPower(zxpower);
        opMode.sleep(200);
        brat.setPower(-0.1);
        brat2.setPower(-1);
        opMode.sleep(650);
        zx.setPower(ZX_START_POWER);
        opMode.sleep(300);
        HorizontSlider.sloz();
        opMode.sleep(300);
        peredacha();
    }
    public void newZx3Auto(){
        zx.setPower(-0.5);
        zxpower = 0.5;
        brat3.setPower(zxpower);
        opMode.sleep(200);
        brat.setPower(-0.1);
        brat2.setPower(-1);
        opMode.sleep(650);
        zx.setPower(ZX_START_POWER);
        opMode.sleep(300);
        HorizontSlider.sloz();
        opMode.sleep(300);
        peredacha();
    }

}