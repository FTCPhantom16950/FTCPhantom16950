package org.firstinspires.ftc.teamcode.own.actions.teleactions;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.PhantomMath;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.regulators.FullRegulator;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfEncoder;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

public class NewWheelBaseAction implements Action {
    double frontLeftPower,backLeftPower,frontRightPower,backRightPower, denominator;
    double x, y, rot;
    double kASide= 0, kVSide= 0, kPSide= 0, kDSide= 0, kISide= 0, dfSide= 0, outputSide = 0, targetSide = 0, sideSpeed = 0;
    double kAFront= 0, kVFront= 0, kPFront= 0, kDFront= 0, kIFront= 0, dfFront= 0, outputFront = 0, targetFront = 0, frontSpeed = 0;
    double kASpin= 0, kVSpin= 0, kPSpin= 0, kDSpin= 0, kISpin= 0, dfSpin= 0, outputSpin = 0, targetSpin = 0, spinSpeed = 0;
    FullRegulator side= new FullRegulator(kVSide, kASide,kPSide,kDSide,kISide,dfSide,outputSide,targetSide,sideSpeed),
            front = new FullRegulator(kVFront,kAFront,kPFront,kDFront,kIFront,dfFront,outputFront,targetFront,frontSpeed),
            spin = new FullRegulator(kVSpin, kASpin, kPSpin,kDSpin,kISpin, dfSpin,outputSpin,targetSpin,spinSpeed);

    @Override
    public void execute() throws InterruptedException {
        SfMotor rb = Robot.INSTANCE.get(SfMotor.class, "rb");
        SfMotor lb = Robot.INSTANCE.get(SfMotor.class, "lb");
        SfMotor rf = Robot.INSTANCE.get(SfMotor.class, "rf");
        SfMotor lf = Robot.INSTANCE.get(SfMotor.class, "lf");
        SfEncoder leftOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lb"), 2000);
        SfEncoder rightOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "rb"), 2000);
        SfEncoder backOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lf"), 2000);
        while (Robot.INSTANCE.opMode.opModeIsActive()){
            x = PhantomMath.makeLinearToCubic(Robot.INSTANCE.gamepadDriver.left_stick_x + Robot.INSTANCE.gamepadDriver.right_stick_x * 0.8);
            y = PhantomMath.makeLinearToCubic(-Robot.INSTANCE.gamepadDriver.left_stick_y - Robot.INSTANCE.gamepadDriver.right_stick_y * 0.8);
            rot = Robot.INSTANCE.gamepadDriver.right_trigger - Robot.INSTANCE.gamepadDriver.left_trigger;

            side.setkA(kASide);
            front.setkA(kAFront);
            spin.setkA(kASpin);
            side.setkV(kVSide);
            front.setkV(kVFront);
            spin.setkV(kVSpin);
            side.setkP(kPSide);
            front.setkP(kPFront);
            spin.setkP(kPSpin);
            side.setkD(kDSide);
            front.setkD(kDFront);
            spin.setkD(kDSpin);
            side.setkI(kISide);
            front.setkI(kIFront);
            spin.setkI(kISpin);
            side.setDerivativeFilter(dfSide);
            front.setDerivativeFilter(dfFront);
            spin.setDerivativeFilter(dfSpin);
            side.setMotorVelocity(backOdo.getVelocity());
            spin.setMotorVelocity(Robot.INSTANCE.imu.getRobotAngularVelocity(AngleUnit.DEGREES).zRotationRate);
            front.setMotorVelocity((leftOdo.getVelocity() + rightOdo.getVelocity()) / 2);
            side.setTarget((double) Robot.INSTANCE.getData("MAXSIDESPEEDSIDE") * x);
            spin.setTarget((double) Robot.INSTANCE.getData("MAXSIDESPEEDSPIN") * rot);
            front.setTarget((double) Robot.INSTANCE.getData("MAXSIDESPEEDFRONT") * y);

            outputSide = side.calculate();
            outputFront = front.calculate();
            outputSpin = spin.calculate();
            denominator = Math.max(Math.abs(x) + Math.abs(y) + Math.abs(rot), 1);
            frontLeftPower = (outputFront + outputSide + outputSpin) / denominator;
            backLeftPower = (outputFront - outputSide + outputSpin) / denominator;
            frontRightPower = (outputFront - outputSide - outputSpin) / denominator;
            backRightPower = (outputFront + outputSide - outputSpin) / denominator;
            rb.setPower(backRightPower);
            lb.setPower(backLeftPower);
            rf.setPower(frontRightPower);
            lf.setPower(frontLeftPower);
        }
    }
}
