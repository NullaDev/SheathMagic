package cn.nulladev.sheathmagic.init.data.recipe;

import cn.nulladev.sheathmagic.content.crafting.MachineCoreOutputRecipe;
import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import net.minecraft.world.item.ItemStack;

public class MachineCoreOutputRecipeBuilder extends AbstractCoreOutputRecipeBuilder<MachineCoreOutputRecipeBuilder, MachineCoreOutputRecipe> {
    public MachineCoreOutputRecipeBuilder(ItemStack input) {
        super(SMRecipes.RECIPE_SERIALIZER_CORE_OUTPUT.get(), input);
    }
}
