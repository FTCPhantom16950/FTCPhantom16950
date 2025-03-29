package org.firstinspires.ftc.teamcode.own.Mechanism;


import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.KRUT_2_START_POWER;
import static org.firstinspires.ftc.teamcode.own.Mechanism.Zxnew.KRUT_START_POWER;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.own.Utils.Config;
import org.firstinspires.ftc.teamcode.own.Utils.PhMath;
import org.firstinspires.ftc.teamcode.own.positions.VerticalPOS;

public class VerticalSlider {
    ElapsedTime timer = new ElapsedTime();
    public static RevColorSensorV3 verx_color;
    boolean once;
    static LinearOpMode opMode;
    public static Rev2mDistanceSensor ds;
    public boolean colorState = false, previousColorState = colorState;
    double output = 0, targetPos = 0;
    // creation of the PID object
    HardwareMap hw;

    public VerticalSlider(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public static CRServo vrash, klesh, sample;
    public static DcMotorEx pod;
    public static final double KLESH_OTPUSK_POWER = PhMath.fromDegreesToPower(115, 270);
    public static double podPower = 0, vrashPower = -0.5, kleshPower = -0.35, vidvig = -0.6;
    public static boolean kleshgo = false, captured = false;
    public static VerticalPOS.KLESHPOS verticalPOS;
    static VerticalPOS.KLESHPOS prevPos;
    boolean pressed = false;

    public void init() {
        hw = opMode.hardwareMap;
        verx_color = opMode.hardwareMap.get(RevColorSensorV3.class, "color_verx");
        pod = opMode.hardwareMap.get(DcMotorEx.class, "pod");
        klesh = opMode.hardwareMap.get(CRServo.class, "klesh");
        vrash = opMode.hardwareMap.get(CRServo.class, "vrash");
        sample = opMode.hardwareMap.get(CRServo.class, "sample");
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(0.15);
        klesh.setPower(-0.35);
        vrash.setPower(PhMath.fromDegreesToPower(60, 270));
        sample.setPower(0.71);
        verticalPOS = VerticalPOS.KLESHPOS.ZAXVAT;
        ds = hw.get(Rev2mDistanceSensor.class, "ds");
    }

    public void play() {
        if (kleshgo && verticalPOS == VerticalPOS.KLESHPOS.ZAXVAT && !Config.actionInWork) {
            klesh.setPower(-0.35);
            kleshgo = false;
        } else if (kleshgo && verticalPOS == VerticalPOS.KLESHPOS.OTPUSK && !Config.actionInWork) {
            klesh.setPower(KLESH_OTPUSK_POWER);
            kleshgo = false;
        }
        if (opMode.gamepad1.a) {
            sample.setPower(-0.85);
        } else if (opMode.gamepad1.y) {
            sample.setPower(0.71);
        }
    }

    public void run() {
        play();

    }

    public static void podvesSpiecMan() {
        sample.setPower(-0.85);
        vrash.setPower(vidvig);
        Config.actionInWork = true;
        pod.setPower(0.9);
        opMode.sleep(700);
        pod.setPower(0.15);
        opMode.sleep(200);
        Config.actionInWork = false;
    }

    public static void spuskSpiceman() {
        sample.setPower(0.71);
        vrash.setPower(vidvig);
        Config.actionInWork = true;
        pod.setPower(-0.9);
        opMode.sleep(650);
        pod.setPower(0.15);
        opMode.sleep(200);
        Config.actionInWork = false;
    }

    public void podvesSample() {
        Config.actionInWork = true;
        HorizontSlider.vidvigAuto();
        vrash.setPower(1);
        opMode.sleep(700);
        klesh.setPower(KLESH_OTPUSK_POWER);
        opMode.sleep(200);
        vrash.setPower(PhMath.fromDegreesToPower(60, 270));
        opMode.sleep(400);

        Config.actionInWork = false;
    }

    public static void perviPodem() {
        vrash.setPower(vidvig);
        Config.actionInWork = true;
        pod.setPower(1.0);
        opMode.sleep(300);
        pod.setPower(0.13);
        Config.actionInWork = false;
    }

    public void spuskPosleBucket() {
        Config.actionInWork = true;
        vrash.setPower(PhMath.fromDegreesToPower(60, 270));
        opMode.sleep(200);
        pod.setPower(-1);
        opMode.sleep(600);
        pod.setPower(0.15);
        Zxnew.brat.setPower(KRUT_START_POWER);
        Zxnew.brat2.setPower(KRUT_2_START_POWER);
        Config.actionInWork = false;
    }


}
