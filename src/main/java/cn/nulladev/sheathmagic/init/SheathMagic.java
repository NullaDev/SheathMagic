package cn.nulladev.sheathmagic.init;

import cn.nulladev.sheathmagic.init.data.recipe.RecipeGen;
import cn.nulladev.sheathmagic.init.registrate.*;
import com.mojang.logging.LogUtils;
import dev.xkmc.l2library.base.L2Registrate;
import dev.xkmc.l2library.repack.registrate.providers.ProviderType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(SheathMagic.MODID)
public class SheathMagic {
    public static final String MODID = "sheathmagic";
    public static final L2Registrate REGISTRATE = new L2Registrate(MODID);
    public static final Logger LOGGER = LogUtils.getLogger();

    private static void registerRegistrates(IEventBus bus) {
        SMCreativeModeTab.register();
        SMItems.register();
        SMBlocks.register();
        SMRecipes.register(bus);
        SMMenus.register();
        REGISTRATE.addDataGenerator(ProviderType.RECIPE, RecipeGen::genMachineCoreOutputRecipe);
    }

    public SheathMagic() {
        FMLJavaModLoadingContext ctx = FMLJavaModLoadingContext.get();
        IEventBus bus = ctx.getModEventBus();
        registerRegistrates(bus);
    }
}
