package org.firstinspires.ftc.teamcode.own.Actions.Test;
import static org.firstinspires.ftc.teamcode.own.Utils.Robot.*;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import org.firstinspires.ftc.teamcode.own.Utils.Action.Action;
import org.firstinspires.ftc.teamcode.own.Utils.PhantomOpMode;
import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.wpi.Pose2d;
import org.psilynx.psikit.core.wpi.Rotation2d;


public class ForTestAction extends Action {

    @Override
    public void execute() {
        while (!opMode.isStopRequested()){
            rot = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            TelemetryPacket packet = new TelemetryPacket();
            FtcDashboard.getInstance().sendTelemetryPacket(packet);
        }
    }
}
