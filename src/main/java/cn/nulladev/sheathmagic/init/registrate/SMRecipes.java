package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.init.SheathMagic;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SMRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SheathMagic.MODID);

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);
    }

}
