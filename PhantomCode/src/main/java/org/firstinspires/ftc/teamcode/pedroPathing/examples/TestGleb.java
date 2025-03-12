package org.firstinspires.ftc.teamcode.pedroPathing.examples;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.FConstants;
import org.firstinspires.ftc.teamcode.pedroPathing.constants.LConstants;
@Autonomous
public class TestGleb extends OpMode {
    private Telemetry telemetryA;



    private Follower follower;

    private PathChain circle;

    /**
     * This initializes the Follower and creates the PathChain for the "circle". Additionally, this
     * initializes the FTC Dashboard telemetry.
     */
    @Override
    public void init() {
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap, FConstants.class, LConstants.class);

        circle = follower.pathBuilder()
                .addPath(
                        // Line 1
                        new BezierCurve(
                                new Point(120.848, 128.730, Point.CARTESIAN),
                                new Point(142.358, 36.452, Point.CARTESIAN),
                                new Point(65.843, 61.409, Point.CARTESIAN),
                                new Point(136.447, 135.133, Point.CARTESIAN),
                                new Point(126.431, 19.047, Point.CARTESIAN)
                        ))
                .build();

        follower.followPath(circle);

        telemetryA = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetryA.addLine("This will run in a roughly circular shape of radius "
                + ", starting on the right-most edge. So, make sure you have enough "
                + "space to the left, front, and back to run the OpMode.");
        telemetryA.update();
    }

    /**
     * This runs the OpMode, updating the Follower as well as printing out the debug statements to
     * the Telemetry, as well as the FTC Dashboard.
     */
    @Override
    public void loop() {
        follower.update();
        if (follower.atParametricEnd()) {
            follower.followPath(circle);
        }

        follower.telemetryDebug(telemetryA);
    }
}
