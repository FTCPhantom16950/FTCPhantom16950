package org.firstinspires.ftc.teamcode.own.Mechanism;

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
    public static final double KRUT_START_POWER = -0.65;
    public static final double KRUT_2_START_POWER = -0.3;
    private static final double ZX_START_POWER = 0;
    public static double g = 0;
    double zxpower, krut_power;
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
        brat2.setDirection(DcMotorSimple.Direction.REVERSE);
        zx.setPower(ZX_START_POWER);
        brat3.setPower(0);
        brat.setPower(KRUT_START_POWER);
        brat2.setPower(KRUT_2_START_POWER);
        krutpos = ZxPos.KRUT.POXOD;
        zxpos = ZxPos.ZX.OTPUSK;
        inited = true;
    }

    public void play1() {
        if (zxpos == ZxPos.ZX.ZAXVAT && zxgo) {
            zx.setPower(0.1);
            zxgo = false;
            captured = true;
        } else if (zxpos == ZxPos.ZX.OTPUSK && zxgo) {
            zx.setPower(-0.33);
            zxgo = false;
            captured = false;
        }
        if(opMode.gamepad2.right_stick_y != 0){
            zxpower = Range.clip(zxpower + opMode.gamepad2.right_stick_y, -1,1);
        }
        brat3.setPower(zxpower);
    }

    public void play() {
        krut_power = brat2.getPower();

        if (krutpos == ZxPos.KRUT.AUto && krutgo && !Config.ACTIONINWORK) {
            brat.setPower(0.67);
            brat2.setPower(-0.3);
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.ZAXVAT && krutgo && !Config.ACTIONINWORK) {
            brat.setPower(0.1);
            brat2.setPower(-1);
            zxpos = ZxPos.ZX.OTPUSK;
            zxgo = true;
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.PEREDACHA && krutgo && !Config.ACTIONINWORK) {
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
            opMode.sleep(200);
            krutgo = true;
        } else if(opMode.gamepad2.x){
            brat.setPower(0.67);
            brat2.setPower(0.42);
            opMode.sleep(500);
            zx.setPower(-0.33);
        }

    }
}