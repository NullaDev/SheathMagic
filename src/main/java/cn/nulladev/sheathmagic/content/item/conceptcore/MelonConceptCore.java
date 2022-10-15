package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;

import java.util.Random;

public class MelonConceptCore extends BaseConceptCore implements ItemContainable, ConceptCoreWand {
    public MelonConceptCore(Properties props) {
        super(props, 12000);
    }

    @Override
    public boolean isContentValid(ItemStack stack) {
        var content = ItemContainable.readTagContent(stack);
        return content.getItem() == Items.PUMPKIN || content.getItem() == Items.MELON;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        ItemStack core = InteroperationWand.readTagWandCore(player.getItemInHand(hand));
        ItemStack content = ItemContainable.readTagContent(core);
        if (content.getItem() != Items.MELON_SEEDS || !player.canEat(false)) {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }

        int i = 3 + new Random().nextInt(5);
        while (i-- > 0) {
            if (player.canEat(false)) {
                player.getFoodData().eat(2, 0.6F);
            } else {
                break;
            }
        }

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = InteroperationWand.readTagWandCore(ctx.getItemInHand());
        ItemStack content = ItemContainable.readTagContent(core);

        if (content.getItem() == Items.PUMPKIN_SEEDS) {
            return ConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), Blocks.PUMPKIN);
        } else {
            return InteractionResult.PASS;
        }
    }


    @Override
    public boolean overrideStackedOnOther(ItemStack core, Slot slot, ClickAction clickAction, Player player) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            var slotStack = slot.getItem();
            if (slotStack.isEmpty()) {
                ItemContainable.removeContent(core).ifPresent(slot::safeInsert);
                return true;
            } else if (slotStack.getItem() == Items.MELON_SEEDS || slotStack.getItem() == Items.PUMPKIN_SEEDS) {
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
                if (slotStack.getItem() == Items.MELON_SEEDS || slotStack.getItem() == Items.PUMPKIN_SEEDS) {
                    InteroperationWand.writeTagCore(core, slotStack.split(1));
                    return true;
                }
            }
        }
        return false;
    }

}
