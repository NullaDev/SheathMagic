package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.Random;

public class FarmConceptCore extends BaseConceptCore implements ItemContainable, ConceptCoreWand {
    public FarmConceptCore(Properties props) {
        super(props, 600);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            ItemStack core = InteroperationWand.readTagWandCore(player.getItemInHand(hand));
            ItemStack content = ItemContainable.readTagContent(core);

            if (content.getItem() == Items.POTATO) {
                player.getFoodData().eat(1, 0.6F);
            } else if (content.getItem() == Items.CARROT) {
                player.getFoodData().eat(3, 1.2F);
            } else if (content.getItem() == Items.BEETROOT_SEEDS) {
                player.getFoodData().eat(1, 1.2F);
            } else if (content.getItem() == Items.WHEAT_SEEDS) {
                if (new Random().nextInt(3) == 0) {
                    player.getFoodData().eat(5, 1.2F);
                }
            }

            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack core, Slot slot, ClickAction clickAction, Player player) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            var slotStack = slot.getItem();
            if (slotStack.isEmpty()) {
                ItemContainable.removeContent(core).ifPresent(slot::safeInsert);
                return true;
            } else if (slotStack.getItem() == Items.WHEAT_SEEDS
                    || slotStack.getItem() == Items.BEETROOT_SEEDS
                    || slotStack.getItem() == Items.CARROT
                    || slotStack.getItem() == Items.POTATO) {
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
            } else {
                if (slotStack.getItem() == Items.WHEAT_SEEDS
                        || slotStack.getItem() == Items.BEETROOT_SEEDS
                        || slotStack.getItem() == Items.POTATO
                        || slotStack.getItem() == Items.CARROT
                ) {
                    InteroperationWand.writeTagCore(core, slotStack.split(1));
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean isContentValid(ItemStack item) {
        var content = ItemContainable.readTagContent(item);

        return content.getItem() == Items.WHEAT_SEEDS
                || content.getItem() == Items.BEETROOT_SEEDS
                || content.getItem() == Items.CARROT
                || content.getItem() == Items.POTATO;
    }
}
