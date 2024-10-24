package org.firstinspires.ftc.teamcode.FORTEST;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;


//TODO: одометры стоящие параллельно вставлять в 1 и 3 слоты моторов
/**
 * класс для одометрии
 */
public class Odometry {
    // опмод для считывания данных
    LinearOpMode opMode;
    // коодинаты в милиметрах и оборотах моторов
    public double[] coordinates = new double[1];
    public double[] MMcoordinates = new double[1];
    // постоянные величины связанные с размерами одорметров
    final double diameter = 48;
    final double length = diameter * Math.PI;
    final double RPC = 2000;
    /**
     * получаем опмод при запуске
     * @param opMode ваш опмод
     */
    public Odometry(LinearOpMode opMode) {
        this.opMode = opMode;
    }

    //наши одометры, как классы моторов
    public DcMotorEx odoLeft, odoRight, odoCenter;

    /**
     * инициализация одометров
     */
    public void initODO(){
        odoLeft = opMode.hardwareMap.get(DcMotorEx.class, "lb");
        odoRight = opMode.hardwareMap.get(DcMotorEx.class, "rf");
        odoCenter = opMode.hardwareMap.get(DcMotorEx.class, "rb");
    }

    /**
     * конвертор в ММ
     */
    public void convertorToMM(){
        MMcoordinates[0] = coordinates[0] / RPC * length;
        MMcoordinates[1] = coordinates[1] / RPC * length;
        MMcoordinates[2] = coordinates[2] / RPC * length;
    }

    /**
     * конвертор в обороты
     */
    public void convertorToRotations(){
        coordinates[0] = MMcoordinates[0] * RPC / length;
        coordinates[1] = MMcoordinates[1] * RPC / length;
        coordinates[2] = MMcoordinates[2] * RPC / length;
    }

    /**
     * считываем повороиты
     */
    public void getRotations(){
        coordinates[0] = odoLeft.getCurrentPosition();
        coordinates[1] = odoCenter.getCurrentPosition();
        coordinates[2] = odoRight.getCurrentPosition();
    }

    /**
     * считываем миллимтры
     */
    public void getMM(){
        getRotations();
        convertorToMM();
    }
}
