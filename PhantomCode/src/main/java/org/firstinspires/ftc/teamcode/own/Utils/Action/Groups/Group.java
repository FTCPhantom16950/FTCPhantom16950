package org.firstinspires.ftc.teamcode.own.Utils.Action.Groups;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.own.Utils.Mechanism;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class Group {
    LinearOpMode opMode;
    public Group(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    /// Метод для реализации выполнения действия
    public abstract void execute();

}
