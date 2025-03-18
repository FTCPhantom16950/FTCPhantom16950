package org.firstinspires.ftc.teamcode.own.Utils;

import com.pedropathing.follower.Follower;
import com.pedropathing.pathgen.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;

public abstract class PhantomOpMode extends LinearOpMode {
    public Follower follower;
    public boolean holdEnd = true;
    /// List of linear auto actions before their path chain
    public List<AutoActions> beforePTLinear = new ArrayList();
    /// List of linear auto actions after their path chain
    public List<AutoActions> afterPTLinear = new ArrayList();
    /// List of parallel auto actions after their path chain
    public List<AutoActions> beforePTParallel = new ArrayList();
    /// List of parallel auto actions after their path chain
    public List<AutoActions> afterPTParallel = new ArrayList();
    /// Thread for telemetry, won't disturb main thread
    Thread telemetryThread = new Thread(() -> {
        while (opModeIsActive()) {
            telemetryDebug();
            telemetry.update();
        }
    });

    // shouldn't be used
    @Override
    public void runOpMode() throws InterruptedException {
        initMechanism();
        waitForStart();
        afterWaitForStart();
        telemetryThread.start();
        while (opModeIsActive()) {
            play();
        }
    }
    /// runs after waitForStart()
    public abstract void afterWaitForStart();
    /// runs in init
    public abstract void initMechanism();
    /// runs while opModeIsActive
    public abstract void play();
    /// runs action while opModeIsActive in different threads
    public void playActionOpMode(TeleOpActions... teleOpActions){
        for(int i = 0; i < teleOpActions.length;i++){
            teleOpActions[i].setOpMode(this);
            teleOpActions[i].start();
        }
    }
    /// updates follower
    Thread fUpdate = new Thread(() -> {
        while (opModeIsActive()) {
            follower.update();
        }
    });
    /// adds linear actions to list
    public void beforePTLinear(AutoActions... autoActions){
        if (autoActions == null || autoActions.length == 0) {
            throw new IllegalArgumentException("Пустой список");  // Or throw an exception if empty input is not allowed.
        }
        List<Integer> pathNumbers = new ArrayList<>();
        for (AutoActions autoAction : autoActions) {

            if (autoAction.pathNumber < 0) {
                throw new IllegalArgumentException("Номер пути не может быть отрицательным: " + autoAction.pathNumber);
            }
            if (pathNumbers.contains(autoAction.pathNumber)) {
                throw new IllegalArgumentException("Найден дублирующий номер пути: " + autoAction.pathNumber);
            }
            if (autoAction == null) {
                throw new IllegalArgumentException("AutoAction не может быть null.");
            }
            pathNumbers.add(autoAction.pathNumber);
            beforePTLinear.add(autoAction.pathNumber, autoAction);
        }
    }
    /// adds parallel actions to list
    public void beforePTParallel(AutoActions... autoActions){
        if (autoActions == null || autoActions.length == 0) {
            throw new IllegalArgumentException("Пустой список");  // Or throw an exception if empty input is not allowed.
        }
        List<Integer> pathNumbers = new ArrayList<>();
        for (AutoActions autoAction : autoActions) {

            if (autoAction.pathNumber < 0) {
                throw new IllegalArgumentException("Номер пути не может быть отрицательным: " + autoAction.pathNumber);
            }
            if (pathNumbers.contains(autoAction.pathNumber)) {
                throw new IllegalArgumentException("Найден дублирующий номер пути: " + autoAction.pathNumber);
            }
            if (autoAction == null) {
                throw new IllegalArgumentException("AutoAction не может быть null.");
            }
            pathNumbers.add(autoAction.pathNumber);
            beforePTParallel.add(autoAction.pathNumber, autoAction);
        }
    }
    /// adds linear actions to list
    public void afterPTLinear(AutoActions... autoActions){
        if (autoActions == null || autoActions.length == 0) {
            throw new IllegalArgumentException("Пустой список");  // Or throw an exception if empty input is not allowed.
        }
        List<Integer> pathNumbers = new ArrayList<>();
        for (AutoActions autoAction : autoActions) {

            if (autoAction.pathNumber < 0) {
                throw new IllegalArgumentException("Номер пути не может быть отрицательным: " + autoAction.pathNumber);
            }
            if (pathNumbers.contains(autoAction.pathNumber)) {
                throw new IllegalArgumentException("Найден дублирующий номер пути: " + autoAction.pathNumber);
            }
            if (autoAction == null) {
                throw new IllegalArgumentException("AutoAction не может быть null.");
            }
            pathNumbers.add(autoAction.pathNumber);
            afterPTLinear.add(autoAction.pathNumber, autoAction);
        }
    }
    /// adds parallel actions to list
    public void afterPTParallel(AutoActions... autoActions){
        if (autoActions == null || autoActions.length == 0) {
            throw new IllegalArgumentException("Пустой список");  // Or throw an exception if empty input is not allowed.
        }
        List<Integer> pathNumbers = new ArrayList<>();
        for (AutoActions autoAction : autoActions) {
            if (autoAction.pathNumber < 0) {
                throw new IllegalArgumentException("Номер пути не может быть отрицательным: " + autoAction.pathNumber);
            }
            if (pathNumbers.contains(autoAction.pathNumber)) {
                throw new IllegalArgumentException("Найден дублирующий номер пути: " + autoAction.pathNumber);
            }
            if (autoAction == null) {
                throw new IllegalArgumentException("AutoAction не может быть null.");
            }
            pathNumbers.add(autoAction.pathNumber);
            afterPTParallel.add(autoAction.pathNumber, autoAction);
        }
    }
    ///  Follows pathChains first is 0
    public void pedroFollowOpMode(Follower follower, PathChain... pathChains){
        // adds follower
        this.follower = follower;
        if(!fUpdate.isAlive()){
            fUpdate.start();
        }
        for (int i = 0; i < pathChains.length; i++) {
            if (pathChains[i] != null) {
                if(i == 0){
                    if (beforePTLinear.get(i) != null) {
                        beforePTLinear.get(i).linearAction();
                    }
                    if (beforePTParallel.get(i) != null) {
                        beforePTLinear.get(i).start();
                    }
                    follower.followPath(pathChains[i], holdEnd);
                    if (afterPTLinear.get(i) != null) {
                        afterPTLinear.get(i).linearAction();
                    }
                    if (afterPTParallel.get(i) != null){
                        afterPTParallel.get(i).start();
                    }

                }
                else {
                    if(!follower.isBusy()&& (follower.getPose().getX() > (follower.getCurrentPath().getLastControlPoint().getX() - 1) && follower.getPose().getY() > (follower.getCurrentPath().getLastControlPoint().getY() - 1) )|| follower.isRobotStuck()){
                        if(holdEnd){
                            follower.holdPoint(follower.getCurrentPath().getLastControlPoint(), follower.getCurrentPath().getClosestPointHeadingGoal());
                        }
                        if (beforePTLinear.get(i) != null) {
                            beforePTLinear.get(i).linearAction();
                        }
                        if (beforePTParallel.get(i) != null) {
                            beforePTParallel.get(i).start();
                        }
                        follower.followPath(pathChains[i], holdEnd);
                        if (afterPTLinear.get(i) != null) {
                            afterPTLinear.get(i).linearAction();
                        }
                        if (afterPTParallel.get(i) != null){
                            afterPTParallel.get(i).start();
                        }
                    }
                }
            }
            else{
                break;
            }
        }
    }
    ///adds telemetry
    public abstract void telemetryDebug();
    ///building paths
    public abstract void pathBuilding();
}

