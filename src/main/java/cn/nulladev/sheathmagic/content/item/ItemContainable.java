package cn.nulladev.sheathmagic.content.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public interface ItemContainable {
    String TAG_CONTENT = "sheath_content";

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

    boolean isContentValid(ItemStack item);

    static Optional<ItemStack> removeContent(ItemStack item) {
        if (!hasContent(item)) {
            return Optional.empty();
        } else {
            var content = readTagContent(item);
            writeTagContent(item, ItemStack.EMPTY);
            return Optional.of(content);
        }
    }
}
