package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class GamepadControl implements Mechanism{
    public static Gamepad gamepadDriver, gamepadOperator;
    private LinearOpMode opMode;

    public GamepadControl(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    @Override
    public boolean init() {
        gamepadDriver = opMode.gamepad1;
        gamepadOperator = opMode.gamepad2;

        return true;
    }
}
