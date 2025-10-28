package org.firstinspires.ftc.teamcode.own.Utils.Action.Groups;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Utils.Robot;

public abstract class Group {
    LinearOpMode opMode;
    public Group() {
        this.opMode = Robot.opMode;
    }

    /// Метод для реализации выполнения действия
    public abstract void execute();

}
