package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.content.crafting.WandAddCoreRecipe;
import cn.nulladev.sheathmagic.content.crafting.WandRemoveCoreRecipe;
import cn.nulladev.sheathmagic.init.SheathMagic;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SMRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SheathMagic.MODID);

    public static final RegistryObject<RecipeSerializer<WandAddCoreRecipe>> WAND_ADD_CORE_RECIPE = RECIPE_SERIALIZER.register("wand_add_core", () -> WandAddCoreRecipe.SERIALIZER);
    public static final RegistryObject<RecipeSerializer<WandRemoveCoreRecipe>> WAND_REMOVE_CORE_RECIPE = RECIPE_SERIALIZER.register("wand_remove_core", () -> WandRemoveCoreRecipe.SERIALIZER);

    public static void register(IEventBus bus) {
        RECIPE_SERIALIZER.register(bus);
    }

}
