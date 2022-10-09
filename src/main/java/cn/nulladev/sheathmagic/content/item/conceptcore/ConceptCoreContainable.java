package cn.nulladev.sheathmagic.content.item.conceptcore;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public interface ConceptCoreContainable {
    String TAG_CONTENT = "content";

    static ItemStack readTagContent(ItemStack item) {
        if (item.getOrCreateTag().contains(TAG_CONTENT)) {
            return ItemStack.of(item.getOrCreateTag().getCompound(TAG_CONTENT));
        } else {
            return ItemStack.EMPTY;
        }
    }

    static void writeTagContent(ItemStack item, ItemStack content) {
        CompoundTag tag = new CompoundTag();
        content.save(tag);
        item.getOrCreateTag().put(TAG_CONTENT, tag);
    }

    static boolean hasContent(ItemStack item) {
        return !(readTagContent(item).isEmpty());
    }

    static boolean isValid(ItemStack item, ItemStack compare) {
        var content = readTagContent(item);
        return  (item.getItem() == compare.getItem());
    }
}
