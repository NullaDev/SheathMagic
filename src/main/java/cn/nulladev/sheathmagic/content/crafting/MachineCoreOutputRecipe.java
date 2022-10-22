package cn.nulladev.sheathmagic.content.crafting;

import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import net.minecraft.resources.ResourceLocation;

public class MachineCoreOutputRecipe extends AbstractCoreOutputRecipe<MachineCoreOutputRecipe> {
    public MachineCoreOutputRecipe(ResourceLocation id) {
        super(id, SMRecipes.RECIPE_SERIALIZER_CORE_OUTPUT.get());
    }
}
