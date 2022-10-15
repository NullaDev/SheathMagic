package cn.nulladev.sheathmagic.init;

import cn.nulladev.sheathmagic.init.registrate.SMCreativeModeTab;
import cn.nulladev.sheathmagic.init.registrate.SMItems;
import cn.nulladev.sheathmagic.init.registrate.SMMenus;
import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import com.mojang.logging.LogUtils;
import dev.xkmc.l2library.base.L2Registrate;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SheathMagic.MODID)
public class SheathMagic {
    public static final String MODID = "sheathmagic";
    public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
    private static final Logger LOGGER = LogUtils.getLogger();

    private static void registerRegistrates(IEventBus bus) {
        SMCreativeModeTab.register();
        SMItems.register();
        SMRecipes.register(bus);
        SMMenus.register();
    }

    public SheathMagic() {
        FMLJavaModLoadingContext ctx = FMLJavaModLoadingContext.get();
        IEventBus bus = ctx.getModEventBus();
        registerRegistrates(bus);
    }
}
