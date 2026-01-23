package org.firstinspires.ftc.teamcode.Own.Actions.AutoActions;
import static org.firstinspires.ftc.teamcode.Own.Utils.Robot.*;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Own.Utils.Action.Action;

public class MoveAction extends Action {
    private double x,y,rot,time;

    public MoveAction(double x, double y, double rot, double time) {
        this.x = x;
        this.y = y;
        this.rot = rot;
        this.time = time;
    }

    @Override
    public void execute() throws InterruptedException {
        x = Range.clip(x,-1,1);
        y = Range.clip(y,-1,1);
        rot = Range.clip(rot,-1,1);
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rot), 1);
        double frontLeftPower = ((y + x + rot) / denominator);
        double backLeftPower = ((y - x + rot) / denominator);
        double frontRightPower = ((y - x - rot) / denominator);
        double backRightPower = (y + x - rot) / denominator;
        rf.setPower(frontRightPower);
        rb.setPower(backRightPower);
        lb.setPower(backLeftPower);
        lf.setPower(frontLeftPower);
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while(timer.milliseconds() < time){

        }
        rf.setPower(0);
        rb.setPower(0);
        lb.setPower(0);
        lf.setPower(0);
    }
}
