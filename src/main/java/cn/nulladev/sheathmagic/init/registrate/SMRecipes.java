package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.client.gui.CrystalMenu;
import cn.nulladev.sheathmagic.content.crafting.AbstractCrystalRecipe;
import cn.nulladev.sheathmagic.content.crafting.DefaultCrystalRecipe;
import cn.nulladev.sheathmagic.init.SheathMagic;
import dev.xkmc.l2library.base.recipe.BaseRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static cn.nulladev.sheathmagic.init.SheathMagic.REGISTRATE;
public class SMRecipes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPE = DeferredRegister.create(Registry.RECIPE_TYPE_REGISTRY, SheathMagic.MODID);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZER = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SheathMagic.MODID);

    public static RegistryObject<RecipeType<AbstractCrystalRecipe<?>>> RECIPE_TYPE_CRYSTAL = REGISTRATE.recipe(RECIPE_TYPE, "crystal_crafting");
    public static final RegistryObject<BaseRecipe.RecType<DefaultCrystalRecipe, AbstractCrystalRecipe<?>, CrystalMenu.CrystalContainer>> RECIPE_SERIALIZER_CRYSTAL_DEFAULT =
            RECIPE_SERIALIZER.register("crystal_default", () -> new BaseRecipe.RecType<>(DefaultCrystalRecipe.class, RECIPE_TYPE_CRYSTAL));

    public static void register(IEventBus bus) {
        RECIPE_TYPE.register(bus);
    }

}
