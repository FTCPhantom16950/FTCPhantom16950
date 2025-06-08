package org.firstinspires.ftc.teamcode.own.Utils;

import com.qualcomm.robotcore.eventloop.opmode.OpModeRegistrar;

import org.firstinspires.ftc.robotcore.internal.opmode.OpModeMeta;

import java.util.Arrays;
import java.util.List;

/**
 * Менеджер для создания OpMode и добавления их в список
 * Made by Hkial(Gleb)
 * Last Updated: 08.06.25 02:40
 */
public class OpModeManager {
    /// Список PhantomOpMode для добавления в их в список Driver Hub
    private static List<PhantomOpMode> opModes = Arrays.asList(Config.opModes);

    /**
     * Менеджер для создания OpMode и добавления их в список
     * @param flavor тип PhantomOpMode, TeleOp или Autonomous
     * @param group группа в списке Driver Hub
     * @param name имя PhantomOpMode
     * @return возвращает метадату для OpMode в менеджер
     */
    private static OpModeMeta addOpmodeDescription(OpModeMeta.Flavor flavor, String group, String name) {
        return new OpModeMeta.Builder()
                .setName(name)
                .setGroup(group)
                .setFlavor(flavor)
                .build();
    }
    /// добавление OpMode в менеджер
    @OpModeRegistrar
    public static void addOpmode(com.qualcomm.robotcore.eventloop.opmode.OpModeManager manager) {
        for (PhantomOpMode opMode : opModes) {
            manager.register(addOpmodeDescription(opMode.getFlavor(), opMode.getGroup(), opMode.getName()), opMode);
        }
    }

}
