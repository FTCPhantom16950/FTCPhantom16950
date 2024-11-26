package org.firstinspires.ftc.teamcode.own.Mechanism

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.hardware.CRServo

class Zaxvat(private val opmode: LinearOpMode) {
    lateinit var zx: CRServo
    lateinit var krut: CRServo
    private val zx_start_power = 0.0
    private val krut_start_power = 0.0
    private val zx_power = 0.1
    private  val krut_skid = -1.0
    private var i = 0
    fun init(){
        zx = opmode.hardwareMap.get(CRServo::class.java, "zx")
        krut = opmode.hardwareMap.get(CRServo::class.java, "krut")
        zx.power = zx_start_power
        krut.power = krut_start_power
    }
    fun run(){

        if (opmode.gamepad2.b) {
            i += 1
        }

        if (i % 2 == 0) {
            zx.power = zx_start_power
        } else if (i % 2 == 1) {
            zx.power = zx_power
        }

        if (opmode.gamepad2.y) {
            krut.power = krut_skid
        } else if (opmode.gamepad2.a) {
            krut.power = krut_start_power
        }
    }

}