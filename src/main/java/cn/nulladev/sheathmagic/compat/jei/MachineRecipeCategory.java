package cn.nulladev.sheathmagic.compat.jei;

import cn.nulladev.sheathmagic.content.crafting.MachineCoreOutputRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;

public class MachineRecipeCategory implements IRecipeCategory<MachineCoreOutputRecipe> {

    private final ResourceLocation bg;
    private final ResourceLocation uid;
    private final ItemStack stack;
    private final String translation;

    private IDrawable background, icon;

    public MachineRecipeCategory(ResourceLocation uid, ItemStack stack, ResourceLocation bg, String translation) {
        this.uid = uid;
        this.bg = bg;
        this.stack = stack;
        this.translation = translation;
    }

    public MachineRecipeCategory init(IGuiHelper guiHelper) {
        background = guiHelper.drawableBuilder(bg, 7, 16, 162, 18)
                .addPadding(0, 0, 0, 0)
                .build();
        icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, stack);
        return this;
    }

    @Override
    public RecipeType<MachineCoreOutputRecipe> getRecipeType() {
        return new RecipeType<>(this.uid, MachineCoreOutputRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.translatable(translation);
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, MachineCoreOutputRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 80, 17).addIngredients(Ingredient.of(recipe.input));
        var outputIngredients = recipe.outputs.keySet().stream().map(Ingredient::of).toList();
        var outputs = new ArrayList<>(outputIngredients);
        for (int i = 0; i < 27 - outputIngredients.size(); i++) {
            outputs.add(Ingredient.EMPTY);
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                builder.addSlot(RecipeIngredientRole.OUTPUT, 8 + j * 18, 53 + i * 18).addIngredients(outputs.get(i * 9 + j));
            }
        }
    }
}
