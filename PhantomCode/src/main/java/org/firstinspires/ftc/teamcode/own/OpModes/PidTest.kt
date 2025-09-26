package org.firstinspires.ftc.teamcode.own.OpModes

import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.own.Actions.PidAction
import org.firstinspires.ftc.teamcode.own.Mechanism.PidMororTest
import org.firstinspires.ftc.teamcode.own.Utils.Action.Groups.ParallelGroup
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode
import org.firstinspires.ftc.teamcode.own.Utils.UnitedTelemetry

@TeleOp
class PidTest: PhantomOpMode() {
    override fun customOpModeSettings() {
        mechanism.add(PidMororTest(this))
        action = ParallelGroup(this, PidAction(this))
    }
}
