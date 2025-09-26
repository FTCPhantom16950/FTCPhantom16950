package org.firstinspires.ftc.teamcode.own.Actions

import org.firstinspires.ftc.teamcode.own.Mechanism.PidMororTest.Companion.motorPid
import org.firstinspires.ftc.teamcode.own.Utils.Action.Action
import org.firstinspires.ftc.teamcode.own.Utils.GamepadControl.Companion.gamepadDriver
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDCofficients
import org.firstinspires.ftc.teamcode.own.Utils.Regulators.PIDRegulator


class PidAction(var opMode: PhantomOpMode) : Action(opMode) {
    companion object {
        var kP = 0.1
        var kI = 0.0
        var kD = 0.0
        var target = -10000
        var out = 0.0
        var error = 0
        var measured = 0
    }

    val coefficients: PIDCofficients = PIDCofficients(kP, kI, kD)
    val regulator = PIDRegulator(coefficients, opMode)
    override fun execute() {
        regulator.setDcMotorEx(motorPid)
        regulator.setTarget(target)
        regulator.start()
        while (opMode.opModeIsActive()) {
            if (gamepadDriver.a){
                gamepadDriver.rumble(1000)
            }
            coefficients.setkP(kP)
            coefficients.setkI(kI)
            coefficients.setkD(kD)
            regulator.setPidCofficients(coefficients)
            motorPid.power = regulator.out()
            out = regulator.out()
            measured = regulator.measured
            error = regulator.error
        }
    }
}