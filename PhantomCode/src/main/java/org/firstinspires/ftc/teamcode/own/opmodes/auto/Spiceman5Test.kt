package org.firstinspires.ftc.teamcode.own.opmodes.auto

import com.pedropathing.follower.Follower
import com.pedropathing.pathgen.BezierCurve
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO
import org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.startPosetoSpiceman
import org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpiceman
import org.firstinspires.ftc.teamcode.own.Utils.POINTSPEDRO.toSetSpicemanPC
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants

@Autonomous
class Spiceman5Test: PhantomOpMode() {

    override fun afterWaitForStart() {

    }

    override fun initMechanism() {
        follower = Follower(this.hardwareMap, FConstants::class.java, LConstants::class.java)
    }

    override fun play() {

    }

    override fun telemetryDebug() {

    }

    override fun pathBuilding() {
        toSetSpicemanPC = follower.pathBuilder().addPath(
            BezierCurve(
                startPosetoSpiceman,
                toSetSpiceman
            )
        ).build()
    }
}