package org.firstinspires.ftc.teamcode.testnt.opmode;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.psilynx.psikit.core.Logger;
import org.psilynx.psikit.core.wpi.math.Pose2d;
import org.psilynx.psikit.core.wpi.math.Pose3d;
import org.psilynx.psikit.core.wpi.math.Rotation2d;
import org.psilynx.psikit.core.wpi.math.Rotation3d;
import org.psilynx.psikit.ftc.FtcLoggingSession;
import org.psilynx.psikit.ftc.autolog.PsiKitAutoLog;


@TeleOp
public class TestNT4Opmode extends LinearOpMode {
    private final FtcLoggingSession psiKit = new FtcLoggingSession();

    @Override
    public void runOpMode() {
        try {
            psiKit.start(this, 5800);

            while (opModeInInit()) {
                Logger.periodicBeforeUser();
                psiKit.logOncePerLoop(this);
                Logger.recordOutput("Pose", new Pose3d(1,1,1,new Rotation3d(1,1,1)));
                Logger.periodicAfterUser(0.0, 0.0);
                idle();
            }

            waitForStart();

            while (opModeIsActive()) {
                Logger.periodicBeforeUser();
                Logger.periodicAfterUser(0.0, 0.0);
                idle();
            }
        } finally {
            psiKit.end();
        }
    }
}