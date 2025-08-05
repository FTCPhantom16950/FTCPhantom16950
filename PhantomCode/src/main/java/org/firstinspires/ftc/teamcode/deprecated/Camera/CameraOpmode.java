package org.firstinspires.ftc.teamcode.deprecated.Camera;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
@TeleOp(name = "CameraOpmode", group = "Phantom")
public class CameraOpmode extends LinearOpMode {
    ElapsedTime time = new ElapsedTime();@Override
    public void runOpMode() throws InterruptedException {
        CameraStarter cameraStarter = new CameraStarter(this);
        cameraStarter.init();
        waitForStart();
        time.reset();
        while (opModeIsActive()) {
        cameraStarter.play();

//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(2, 4, blobsBlue);
//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(2, 4, blobRed);
//            ColorBlobLocatorProcessor.Util.filterByAspectRatio(2, 4, blobsYellow);
            // Step through the list of detections and display info for each one.


            // Add "key" information to telemetry

            telemetry.update();
            sleep(50);
        }
        if(isStopRequested()){
//            visionPortal.close();
        }
        }
}
