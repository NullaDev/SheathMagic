package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoolCarpetBlock;

public class AttachmentConceptCore extends BaseConceptCore implements ConceptCoreWand, ItemContainable {
    public AttachmentConceptCore(Properties props) {
        super(props, 2);
    }

    @Override
    public boolean isContentValid(ItemStack item) {
        var content = ItemContainable.readTagContent(item);

        if (content.getItem() instanceof BlockItem blockItem) {
            var block = blockItem.getBlock();
            return block instanceof WoolCarpetBlock || block instanceof BaseRailBlock;
        } else {
            return false;
        }
    }


    public static Block getBlock(ItemStack stack) {
        if (ItemContainable.readTagContent(stack).getItem() instanceof BlockItem blockItem) {
            return blockItem.getBlock();
        } else {
            return Blocks.AIR;
        }
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = InteroperationWand.readTagWandCore(ctx.getItemInHand());

        if (ItemContainable.hasContent(core)) {
            Block block = getBlock(core);
            return ConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), block);
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
            } else if (slotStack.getItem() instanceof BlockItem blockItem &&
                (blockItem.getBlock() instanceof WoolCarpetBlock || blockItem.getBlock() instanceof BaseRailBlock)) {
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
                if (slotStack.getItem() instanceof BlockItem blockItem &&
                        (blockItem.getBlock() instanceof WoolCarpetBlock || blockItem.getBlock() instanceof BaseRailBlock)
                ) {
                    InteroperationWand.writeTagCore(core, slotStack.split(1));
                    return true;
                }
            }
        }
        return false;
    }

}
