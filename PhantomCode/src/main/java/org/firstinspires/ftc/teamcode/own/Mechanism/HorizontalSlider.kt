package org.firstinspires.ftc.teamcode.own.Mechanism

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.CRServo

class HorizontalSlider(private val opMode: LinearOpMode){
    lateinit var sL:CRServo
    lateinit var sR:CRServo
    private var i = 0.0
    private var sl_power = 0.0
    private var sr_power = 0.0

    fun init(){
        sL = opMode.hardwareMap.get(CRServo::class.java, "horL")
        sL.power = sl_power
        sR.power = sr_power
    }

    fun run(){
        sl_power= i
        sr_power = -i
        sL.power = sl_power
        sR.power = sr_power
        if (opMode.gamepad2.right_trigger != 0.0f && i < 0.6){
            i += opMode.gamepad2.right_trigger * 0.05
        } else if (i >= 0.6){
            i = 0.6
        }
        if (opMode.gamepad2.left_trigger != 0.0f && i > 0){
            i -= opMode.gamepad2.left_trigger * 0.05
        }
        else if (i <= 0){
            i = 0.0
        }
        if (opMode.gamepad2.left_stick_button) {
            i = 0.0
        } else if (opMode.gamepad2.right_stick_button) {
            i = 0.6
        }

    }

}