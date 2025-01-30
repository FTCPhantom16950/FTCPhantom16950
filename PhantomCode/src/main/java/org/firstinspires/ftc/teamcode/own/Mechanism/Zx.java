package org.firstinspires.ftc.teamcode.own.Mechanism;

import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.klesh;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrash;
import static org.firstinspires.ftc.teamcode.own.Mechanism.VerticalSlider.vrashPower;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.own.Utils.Color;
import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;
import org.firstinspires.ftc.teamcode.own.positions.ZxPos;

import java.util.Objects;


public class Zx {
    public static double gain = 103;
    org.firstinspires.ftc.teamcode.own.Utils.Config config = new org.firstinspires.ftc.teamcode.own.Utils.Config();
    static LinearOpMode opMode;
    public static CRServo zx, brat, brat2;
    HardwareMap hw;

    public Zx(LinearOpMode opMode) {
        this.opMode = opMode;

    }

    public static RevColorSensorV3 colorSensor;
    public static ZxPos.KRUT krutpos;
    public static ZxPos.ZX zxpos;
    public static final double krut_start_power = -0.3;
    public static final double krut2_start_power = -0.3;
    private static final double zx_start_power = 0;
    public static double g = 0;
    double krut2_power, krut_power;
    public static boolean not = false;
    public static boolean zxgo, krutgo;
    public boolean inited = false;
    public static boolean captured = false;
    static Color color = new Color();
    public static boolean canBeCaptured = true;

    public void init() {
        hw = opMode.hardwareMap;
        zx = opMode.hardwareMap.get(CRServo.class, "zx");
        brat = opMode.hardwareMap.get(CRServo.class, "krut");
        brat2 = opMode.hardwareMap.get(CRServo.class, "vrash2");
        colorSensor = hw.get(RevColorSensorV3.class, "color");
        brat2.setDirection(DcMotorSimple.Direction.REVERSE);
        zx.setPower(zx_start_power);
        brat.setPower(krut_start_power);
        brat2.setPower(krut2_start_power);
        krutpos = ZxPos.KRUT.POXOD;
        zxpos = ZxPos.ZX.OTPUSK;
        inited = true;
        colorSensor.setGain((float) gain);
    }

    public void play1() {
        if (zxpos == ZxPos.ZX.ZAXVAT && zxgo) {
            zx.setPower(0.23);
            zxgo = false;
            captured = true;
        } else if (zxpos == ZxPos.ZX.OTPUSK && zxgo) {
            zx.setPower(-0.33);
            zxgo = false;
            captured = false;
        }

    }

    public void play() {
        krut_power = brat2.getPower();

        if (krutpos == ZxPos.KRUT.AUto && krutgo) {
            brat.setPower(0.67);
            brat2.setPower(-0.3);
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.ZAXVAT && krutgo) {
            brat.setPower(0.67);
            brat2.setPower(0.42);
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.PEREDACHA && krutgo && colorSensor.getDistance(DistanceUnit.MM) <= 36) {
            peredacha();
            krutgo = false;
        } else if (krutpos == ZxPos.KRUT.POXOD && krutgo) {
            brat.setPower(krut_start_power);
            brat2.setPower(krut2_start_power);
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
        }

    }

    public static void bliz_zx() {
        vrashPower = -0.49;
        vrash.setPower(vrashPower);
        zx.setPower(-0.33);
//        krutpos = ZxPos.KRUT.ZAXVAT;
        brat.setPower(0.67);
        brat2.setPower(0.42);
        opMode.sleep(800);
//        zxpos = ZxPos.ZX.ZAXVAT;
        zx.setPower(0.23);
        captured = true;
        opMode.sleep(200);
    }

    public static void otpusk() {
        zxpos = ZxPos.ZX.OTPUSK;
        zx.setPower(-0.33);
        opMode.sleep(200);
        brat.setPower(0);
        brat2.setPower(0);
    }
    public static void peredacha(){
        klesh.setPower(0);
        brat.setPower(-0.15);
        brat2.setPower(-0.9);
        opMode.sleep(700);
        zx.setPower(-0.33);
        opMode.sleep(300);
        klesh.setPower(-0.35);
        brat.setPower(krut_start_power);
        brat2.setPower(krut2_start_power);
    }

    public static void zxAuto() {
        Config.ACTIONINWORK = true;
        HorizontSlider.nepolniVidvig();
        opMode.sleep(300);
        brat.setPower(0.67);
        brat2.setPower(0.42);
        opMode.sleep(200);
//        zxpos = ZxPos.ZX.OTPUSK;
        zx.setPower(-0.33);
        opMode.sleep(300);
        if (colorSensor.getDistance(DistanceUnit.MM) <= 36 || Objects.equals(color.color(colorSensor.getNormalizedColors().red, colorSensor.getNormalizedColors().green, colorSensor.getNormalizedColors().blue), "YELLOW")) {
            bliz_zx();
            opMode.sleep(150);
            HorizontSlider.sloz();
            peredacha();
            opMode.sleep(100);
            otpusk();
            canBeCaptured = true;
        } else {
            HorizontSlider.vidvigAuto();
            opMode.sleep(200);
            if (colorSensor.getDistance(DistanceUnit.MM) <= 36 || Objects.equals(color.color(colorSensor.getNormalizedColors().red, colorSensor.getNormalizedColors().green, colorSensor.getNormalizedColors().blue), "YELLOW")) {
                bliz_zx();
                HorizontSlider.sloz();
                peredacha();
                opMode.sleep(100);
                otpusk();
                canBeCaptured = true;
            } else {
                brat.setPower(krut_start_power);
                brat2.setPower(krut2_start_power);
                HorizontSlider.sloz();
                canBeCaptured = false;
            }
        }
        Config.ACTIONINWORK = false;
    }
}

