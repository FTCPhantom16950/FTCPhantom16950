package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

import java.util.Arrays;
import java.util.List;

public class OpModeManager {
    private static List<PhantomOpMode> opModes = Arrays.asList(Config.opModes);

    private static OpModeMeta addOpmodeDescription(OpModeMeta.Flavor flavor, String group, String name) {
        return new OpModeMeta.Builder()
                .setName(name)
                .setGroup(group)
                .setFlavor(flavor)
                .build();
    }

    @OpModeRegistrar
    public static void addOpmode(com.qualcomm.robotcore.eventloop.opmode.OpModeManager manager) {
        for (PhantomOpMode opMode : opModes) {
            manager.register(addOpmodeDescription(opMode.getFlavor(), opMode.getGroup(), opMode.getName()), opMode);
        }
    }

}
