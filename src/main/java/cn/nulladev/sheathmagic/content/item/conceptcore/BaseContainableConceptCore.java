package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class BaseContainableConceptCore extends BaseConceptCore implements ItemContainable {
    private final List<Item> validItems;

    public BaseContainableConceptCore(Properties props, int cooldown, List<Item> validItems) {
        super(props, cooldown);
        this.validItems = validItems;

    }

    @Override
    public Component getName(ItemStack item) {
        if (ItemContainable.hasContent(item)) {
            MutableComponent component = super.getName(item).plainCopy();
            component.append("(");
            component.append(ItemContainable.readTagContent(item).getDisplayName().plainCopy().withStyle(ChatFormatting.GREEN));
            component.append(")");
            return component;
        } else {
            return super.getName(item);
        }
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack core, Slot slot, ClickAction clickAction, Player player) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            var slotStack = slot.getItem();
            if (slotStack.isEmpty()) {
                ItemContainable.removeContent(core).ifPresent(slot::safeInsert);
                return true;
            } else if (validItems.contains(slotStack.getItem()) && !ItemContainable.hasContent(core)) {
                ItemContainable.writeTagContent(core, slotStack.split(1));
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack core, ItemStack slotStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (slotStack.isEmpty()) {
                ItemContainable.removeContent(core).ifPresent(slotAccess::set);
                return true;
            } else if (validItems.contains(slotStack.getItem()) && !ItemContainable.hasContent(core)) {
                ItemContainable.writeTagContent(core, slotStack.split(1));
                return true;

            }
        }
        return false;
    }
}
