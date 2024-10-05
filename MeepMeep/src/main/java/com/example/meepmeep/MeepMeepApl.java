package com.example.meepmeep;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.DriveTrainType;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepApl {
    public static void main(String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        MeepMeep meepMeep= new MeepMeep(600, 165);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                .setDriveTrainType(DriveTrainType.MECANUM)
                .setStartPose(new Pose2d(-36,-61.5,Math.toRadians(90)))
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 20, Math.toRadians(180), Math.toRadians(180), 11)
                .build();
        RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
                .setDriveTrainType(DriveTrainType.MECANUM)
                .setStartPose(new Pose2d(36,61.5,Math.toRadians(-90)))
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(30, 15, Math.toRadians(180), Math.toRadians(180), 11)
                .build();


        myBot.runAction(myBot.getDrive().actionBuilder(myBot.getPose())
                .strafeToLinearHeading(new Vector2d(-11, -34), Math.toRadians(-90))
                .waitSeconds(3)
                .splineTo(new Vector2d(-38.5,-23), Math.toRadians(180))
                .waitSeconds(1)
                .lineToX(-38)
                .splineTo(new Vector2d(-55,-56), Math.toRadians(225))
                .waitSeconds(3)
                .splineTo(new Vector2d(-38.5,-23), Math.toRadians(180))
                .strafeTo(new Vector2d(-48,-23))
                .waitSeconds(1)
                .lineToX(-47)
                .splineTo(new Vector2d(-55,-56), Math.toRadians(225))
                .build());

        myBot2.runAction(myBot.getDrive().actionBuilder(myBot2.getPose())
                        .strafeToLinearHeading(new Vector2d(11, 34), Math.toRadians(90))
                        .waitSeconds(3)
                        .splineTo(new Vector2d(38.5,23), Math.toRadians(0))
                        .waitSeconds(1)
                        .lineToX(38)
                        .splineTo(new Vector2d(55,56), Math.toRadians(45))
                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .addEntity(myBot2)

                .start();
    }
}
