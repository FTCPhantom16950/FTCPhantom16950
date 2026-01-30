package org.firstinspires.ftc.teamcode.own.actions.utilactions;

import com.acmerobotics.dashboard.config.Config;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.own.utils.Robot;
import org.firstinspires.ftc.teamcode.own.utils.actions.Action;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfEncoder;
import org.firstinspires.ftc.teamcode.own.utils.safehardware.SfMotor;

@Configurable
@Config
public class MaxSpeedGetter implements Action {
    public static boolean spin = false, side = false, front= false;
    @Override
    public void execute() throws InterruptedException {
        SfMotor rb = Robot.INSTANCE.get(SfMotor.class, "rb");
        SfMotor lb = Robot.INSTANCE.get(SfMotor.class, "lb");
        SfMotor rf = Robot.INSTANCE.get(SfMotor.class, "rf");
        SfMotor lf = Robot.INSTANCE.get(SfMotor.class, "lf");
        SfEncoder leftOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lb"), 2000);
        SfEncoder rightOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "rb"), 2000);
        SfEncoder backOdo = new SfEncoder(Robot.INSTANCE.hw.get(DcMotorEx.class, "lf"), 2000);
        while  (Robot.INSTANCE.opMode.opModeIsActive()){
            if (spin){
                rb.setPower(-1);
                rf.setPower(-1);
                lb.setPower(1);
                lf.setPower(1);
                double speedSpin = Robot.INSTANCE.imu.getRobotAngularVelocity(AngleUnit.DEGREES).zRotationRate;
                if (speedSpin >  Robot.INSTANCE.getData(Double.class,"MAXSIDESPEEDSPIN")){
                    Robot.INSTANCE.addData("MAXSIDESPEEDSPIN", speedSpin);
                }
            } else if (side) {
                double speedSide = backOdo.getVelocity();
                if (speedSide > (double) Robot.INSTANCE.getData(Double.class,"MAXSIDESPEEDSPIN")){
                    Robot.INSTANCE.addData("MAXSIDESPEEDSPIN", speedSide);
                }
            } else if (front){
                double speedSide = (leftOdo.getVelocity() + rightOdo.getVelocity()) / 2;
                if (speedSide > Robot.INSTANCE.getData(Double.class,"MAXSIDESPEEDSPIN")){
                    Robot.INSTANCE.addData("MAXSIDESPEEDSPIN", speedSide);
                }
            }
        }
    }
}
