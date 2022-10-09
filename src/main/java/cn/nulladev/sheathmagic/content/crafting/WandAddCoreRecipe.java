package cn.nulladev.sheathmagic.content.crafting;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.conceptcore.ConceptCoreWand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.level.Level;

public class WandAddCoreRecipe extends CustomRecipe {
    public static final RecipeSerializer<WandAddCoreRecipe> SERIALIZER = new SimpleRecipeSerializer<>(WandAddCoreRecipe::new);

    public WandAddCoreRecipe(ResourceLocation location) {
        super(location);
    }

    @Override
    public boolean matches(CraftingContainer container, Level level) {
        boolean foundCore = false;
        boolean foundWand = false;

        for (int i = 0; i < container.getMaxStackSize(); i++) {
            var stack = container.getItem(i);

            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof InteroperationWand && !InteroperationWand.hasCore(stack)) {
                    if (foundWand) {
                        return false;
                    }
                    else {
                        foundWand = true;
                    }
                } else if (stack.getItem() instanceof ConceptCoreWand) {
                    if (foundCore) {
                        return false;
                    }
                    else {
                        foundCore = true;
                    }
                } else {
                    return false;
                }
            }
        }
        return foundCore && foundWand;
    }

    @Override
    public ItemStack assemble(CraftingContainer container) {
        ItemStack core = ItemStack.EMPTY;
        ItemStack wand = ItemStack.EMPTY;

        for (int i = 0; i < container.getMaxStackSize(); i++) {
            ItemStack stack = container.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof InteroperationWand) {
                    wand = stack;
                } else if (stack.getItem() instanceof ConceptCoreWand) {
                    core = stack;
                }
            }
        }

        if (core.isEmpty() || wand.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack wand_with_core = wand.copy();
        InteroperationWand.writeTagCore(wand_with_core, core);

        return wand_with_core;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
