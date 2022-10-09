package cn.nulladev.sheathmagic.content.item.conceptcore;

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

public class BaseConceptCore extends Item {
    final int cooldown;

    public BaseConceptCore(Properties props, int cooldown) {
        super(props.stacksTo(1));
        this.cooldown = cooldown;
    }

    private static final String TAG_COOLDOWN = "cooldown";

    public int getTotalCooldown() {
        return cooldown;
    }

    public static int readTagCooldown(ItemStack item) {
        return item.getOrCreateTag().getInt(TAG_COOLDOWN);
    }

    public static void writeTagCooldown(ItemStack item, int newCooldown) {
        item.getOrCreateTag().putInt(TAG_COOLDOWN, newCooldown);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        var currentCoolingDown = readTagCooldown(stack);
        if (currentCoolingDown > 0) {
            writeTagCooldown(stack, currentCoolingDown - 1);
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return readTagCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return Math.round(13F - 13F * readTagCooldown(stack) / this.cooldown);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        float f = Math.max(0.0F, (this.cooldown - readTagCooldown(stack)) / (float) cooldown);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable("sheathmagic.misc.total_cd", readTagCooldown(item)));
        if (readTagCooldown(item) > 0) {
            list.add((Component.translatable("sheathmagic.misc.cooling_down",
                    readTagCooldown(item)).withStyle(ChatFormatting.RED)));
        } else {
            list.add(Component.translatable("sheathmagic.misc.available").withStyle(ChatFormatting.GREEN));
        }
    }

    public static boolean canUse(ItemStack core) {
        return readTagCooldown(core) <= 0;
    }

}
