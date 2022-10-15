package cn.nulladev.sheathmagic.content.crafting;

import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import net.minecraft.resources.ResourceLocation;

public class DefaultCrystalRecipe extends AbstractCrystalRecipe<DefaultCrystalRecipe> {
    public DefaultCrystalRecipe(ResourceLocation id) {
        super(id, SMRecipes.RECIPE_SERIALIZER_CRYSTAL_DEFAULT.get());
    }
}
