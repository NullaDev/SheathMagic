package cn.nulladev.sheathmagic.content.item;

import net.minecraft.world.item.ItemStack;

public interface ItemCooldown {

    String TAG_COOLDOWN = "cooldown";

    public int getTotalCooldown();

    public static int readTagCooldown(ItemStack item) {
        return item.getOrCreateTag().getInt(TAG_COOLDOWN);
    }

    public static void writeTagCooldown(ItemStack item, int newCooldown) {
        item.getOrCreateTag().putInt(TAG_COOLDOWN, newCooldown);
    }

}
