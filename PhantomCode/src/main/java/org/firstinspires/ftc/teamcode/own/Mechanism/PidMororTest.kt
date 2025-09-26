package org.firstinspires.ftc.teamcode.own.Mechanism

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import org.firstinspires.ftc.teamcode.own.Utils.Mechanism
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode

class PidMororTest(var opMode: PhantomOpMode): Mechanism {
    companion object{
        lateinit var motorPid: DcMotorEx

    }
    override fun init(): Boolean {
        motorPid = opMode.hardwareMap.get(DcMotorEx::class.java,"motorPid")
        motorPid.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        motorPid.mode = DcMotor.RunMode.RUN_USING_ENCODER
        motorPid.direction = DcMotorSimple.Direction.REVERSE
        motorPid.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
        return true
    }
}