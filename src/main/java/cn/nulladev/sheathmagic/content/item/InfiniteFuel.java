package cn.nulladev.sheathmagic.content.item;

import cn.nulladev.sheathmagic.init.registrate.SMItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;

public class InfiniteFuel extends Item {
    public InfiniteFuel(Properties props) {
        super(props.stacksTo(1));
    }

    @SubscribeEvent
    public static void furnaceFuelBurnTimeEvent(FurnaceFuelBurnTimeEvent event) {
        if (event.getItemStack().getItem() == SMItems.INFINITE_FUEL.get()) {
            event.setBurnTime(67);
        }
    }

    @Override
    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
        return 67;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack)
    {
        if (!hasCraftingRemainingItem(itemStack))
        {
            return ItemStack.EMPTY;
        }

        return new ItemStack(SMItems.INFINITE_FUEL.get());
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack)
    {
        return true;
    }
}
