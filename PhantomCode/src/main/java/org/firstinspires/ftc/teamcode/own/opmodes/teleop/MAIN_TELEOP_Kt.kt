package org.firstinspires.ftc.teamcode.own.opmodes.teleop
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime
import org.firstinspires.ftc.teamcode.own.Mechanism.HorizontalSlider
import org.firstinspires.ftc.teamcode.own.Mechanism.LynxModule
import org.firstinspires.ftc.teamcode.own.Mechanism.WheelBase
import org.firstinspires.ftc.teamcode.own.Mechanism.Zaxvat

@TeleOp
class MAIN_TELEOP_Kt: LinearOpMode() {
    val bool = true
    val time = ElapsedTime()
    val lynxModule = LynxModule(this)
    val wheelBase = WheelBase(this)
    val horizontalSlider = HorizontalSlider(this)
    val zaxvat = Zaxvat(this)
    override fun runOpMode() {
        lynxModule.init_Lynx()
        wheelBase.initWheelBase(this.hardwareMap)
        horizontalSlider.init()
        zaxvat.init()
        waitForStart()

        while(opModeIsActive()){
            if (bool == true){
                wheelBase.driveEasy()
            }
            else{
                wheelBase.driveFieldCentric()
            }
            zaxvat.run()
            horizontalSlider.run()
        }
    }
}