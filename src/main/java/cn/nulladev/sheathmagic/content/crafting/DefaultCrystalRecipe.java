package cn.nulladev.sheathmagic.content.crafting;

import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import dev.xkmc.l2library.serial.SerialClass;
import net.minecraft.resources.ResourceLocation;

@SerialClass
public class DefaultCrystalRecipe extends AbstractCrystalRecipe<DefaultCrystalRecipe> {
    public DefaultCrystalRecipe(ResourceLocation id) {
        super(id, SMRecipes.RECIPE_SERIALIZER_CRYSTAL_DEFAULT.get());
    }
}
