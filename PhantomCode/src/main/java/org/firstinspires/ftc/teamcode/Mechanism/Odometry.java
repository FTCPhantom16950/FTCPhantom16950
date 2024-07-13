package org.firstinspires.ftc.teamcode.Mechanism;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Odometry {
    LinearOpMode opMode;
    public double[] coordinates = new double[1];
    public double[] MMcoordinates = new double[1];
    double diameter = 48;
    double length = diameter * Math.PI;
    double RPC = 2000;
    public Odometry(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    public DcMotorEx odoLeft, odoRight, odoCenter;
    public void initODO(){
        odoLeft = opMode.hardwareMap.get(DcMotorEx.class, "lb");
        odoRight = opMode.hardwareMap.get(DcMotorEx.class, "rf");
        odoCenter = opMode.hardwareMap.get(DcMotorEx.class, "rb");
    }

    public void convertorToMM(){
        MMcoordinates[0] = coordinates[0] / RPC * length;
        MMcoordinates[1] = coordinates[1] / RPC * length;
        MMcoordinates[2] = coordinates[2] / RPC * length;
    }

    public void convertorToRotations(){
        coordinates[0] = MMcoordinates[0] * RPC / length;
        coordinates[1] = MMcoordinates[1] * RPC / length;
        coordinates[2] = MMcoordinates[2] * RPC / length;
    }

    public void getRotations(){
        coordinates[0] = odoLeft.getCurrentPosition();
        coordinates[1] = odoCenter.getCurrentPosition();
        coordinates[2] = odoRight.getCurrentPosition();
    }

    public void getMM(){
        getRotations();
        convertorToMM();
    }
}
