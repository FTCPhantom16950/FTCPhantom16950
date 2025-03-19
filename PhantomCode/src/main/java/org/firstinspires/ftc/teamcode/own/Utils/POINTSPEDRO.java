package org.firstinspires.ftc.teamcode.own.Utils;

import com.acmerobotics.dashboard.config.Config;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.PathChain;

@Config
public class POINTSPEDRO {
    public static final Pose toBucket = new Pose(127.8,18.1, Math.toRadians(135));
    public static final Pose toBucketCoontrol = new Pose(121.60578661844485,37.2368896925859);
    public static final Pose to1Sample = new Pose(118.2, 25, Math.toRadians(180));
    public static final Pose to2Sample = new Pose(119.2, 15.7, Math.toRadians(180));
    public static final Pose to3Sample = new Pose(108.1, 21.3, 4.17123375711215);
    public static final Pose toPark2 = new Pose(80.20253164556962,50, Math.toRadians(270));
    public static final Pose ToPark2Control = new Pose(70.56781193490055,8.59312839059675);
    public static final Pose startPosetoSpiceman = new Pose(134.3, 62.5, Math.toRadians(0));
    public static final Pose toSetSpiceman = new Pose(106.6, 72.9, Math.toRadians(0));
    public static final Pose toGetSpiceman = new Pose(124.6, 121.7, Math.toRadians(0));
    public static final Pose toSetSpiceman1Help = new Pose(115,72.9, Math.toRadians(180));
    public static final Pose toSetSpiceman1Help3 = new Pose(78.6,100, Math.toRadians(180));
    public static final Pose toSetSpiceman1Help2 = new Pose(118.5,100, Math.toRadians(180));
    public static final Pose toSpiceman1 = new Pose(100, 113.2, Math.toRadians(180));
    public static final Pose toSpiceman2 = new Pose(100, 122.9, Math.toRadians(180));
    public static final Pose toSpiceman3 = new Pose(100, 127.6, Math.toRadians(180));
    public static final Pose toSpiceman1Control1 = new Pose(143.2, 138.2, Math.toRadians(180));
    public static final Pose toSpiceman2Control1 = new Pose(68.1, 115.4, Math.toRadians(180));
    public static final Pose toSpiceman3Control1 = new Pose(41.7, 132.2, Math.toRadians(180));
    public static final Pose toSpiceman1Stop = new Pose(124.6, 113.2, Math.toRadians(180));
    public static final Pose toSpiceman2Stop = new Pose(124.6, 122.9, Math.toRadians(180));
    public static final Pose toSpiceman3Stop = new Pose(124.6, 127.6, Math.toRadians(180));
    public static PathChain toBucketStart, toBucketPC, to1SamplePC, to2SamplePC, to2SamplePC2, to3SamplePC,toPark2PC,toBucketPCthird,toBucketPCfirst,toBucketPCsecond,
    toSetSpicemanPC, to1SpicemanPC, to2SpicemanPC, to3SpicemansPC, toCapture1SpicemanPC, toGetSpicemanPC, toSetSpiceman2_5PC, toReturnPC;

}
