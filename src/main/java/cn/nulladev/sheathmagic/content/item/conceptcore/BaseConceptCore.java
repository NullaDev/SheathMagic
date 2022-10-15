package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.ItemCooldown;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class BaseConceptCore extends Item implements ItemCooldown {
    final int cooldown;

    public BaseConceptCore(Properties props, int cooldown) {
        super(props.stacksTo(1));
        this.cooldown = cooldown;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        var currentCoolingDown = ItemCooldown.readTagCooldown(stack);
        if (currentCoolingDown > 0) {
            ItemCooldown.writeTagCooldown(stack, currentCoolingDown - 1);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return ItemCooldown.readTagCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13F - 13F * ItemCooldown.readTagCooldown(stack) / this.cooldown);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, (this.cooldown - ItemCooldown.readTagCooldown(stack)) / (float) cooldown);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("sheathmagic.misc.total_cd", ItemCooldown.readTagCooldown(item)));
        if (ItemCooldown.readTagCooldown(item) > 0) {
            list.add((Component.translatable("sheathmagic.misc.cooling_down",
                    ItemCooldown.readTagCooldown(item)).withStyle(ChatFormatting.RED)));
        } else {
            list.add(Component.translatable("sheathmagic.misc.available").withStyle(ChatFormatting.GREEN));
        }
    }

    public static boolean canUse(ItemStack core) {
        return ItemCooldown.readTagCooldown(core) <= 0;
    }

    @Override
    public int getTotalCooldown() {
        return cooldown;
    }
}
