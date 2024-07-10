package org.firstinspires.ftc.teamcode.Utils;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FTCcontroolers {
    double K_P, K_I, K_D;
    Config config = new Config();
    public class PIDFcontroller{
        double currentError, errorPerTime, cInput, previousError;
        DcMotorEx encoder;
        ElapsedTime timer = new ElapsedTime();
        double currentTime, previousTime;

        public PIDFcontroller(DcMotorEx encoder) {
            this.encoder = encoder;
        }
        Thread Tcurrent = new Thread(() -> {
            currentTime = timer.startTime();
            while (true){
                currentTime = timer.time();
            }
        });

        public void calc_PD(double current, double target){
            K_P = config.k_p;
            K_D = config.k_d;
            Tcurrent.start();
            timer.reset();
            while(Tcurrent.isAlive()){
                currentError = target - current;
                errorPerTime = (currentError - previousError) / (currentTime - previousTime);

                cInput = K_P * currentError + K_D * (-currentError) + K_I * currentError * currentTime;

                previousError = currentError;
                currentTime = previousTime;
            }
        }
    }

}
