package org.firstinspires.ftc.teamcode.own.Mechanism

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.CRServo

class Zaxvat(opmode: LinearOpMode) {
    val zx: CRServo = opmode.hardwareMap.get(CRServo::class.java, "zx")
    val krut: CRServo = opmode.hardwareMap.get(CRServo::class.java, "krut")
    val opmode: LinearOpMode = opmode
    val zx_start_power = 0.0
    val krut_start_power = 0.0
    val zx_power = 0.1
    val krut_skid = -1.0
    var i = 0
    fun init(){
        zx.power = zx_start_power
        krut.power = krut_start_power
    }
    fun run(){

        if(opmode.gamepad2.x){
            i += 1
        }

        if(i % 2 == 0) {
            zx.power = zx_start_power
        }
        else if(i % 2 == 1){
            zx.power = zx_power
        }

        if(opmode.gamepad2.dpad_up){
            krut.power = krut_skid
        }
        else if(opmode.gamepad2.dpad_down){
            krut.power = krut_start_power
        }
    }

}