package cn.nulladev.sheathmagic;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(SheathMagic.MODID)
public class SheathMagic {
    public static final String MODID = "sheathmagic";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
}
