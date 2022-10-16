package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WoolCarpetBlock;

import java.util.List;

public class AttachmentConceptCore extends BaseContainableConceptCore implements ConceptCoreWand {
    public static final List<Item> validItems = List.of(
            Items.BLACK_CARPET,
            Items.BLUE_CARPET,
            Items.BROWN_CARPET,
            Items.CYAN_CARPET,
            Items.GRAY_CARPET,
            Items.GREEN_CARPET,
            Items.LIGHT_BLUE_CARPET,
            Items.LIGHT_GRAY_CARPET,
            Items.LIME_CARPET,
            Items.MAGENTA_CARPET,
            Items.ORANGE_CARPET,
            Items.PINK_CARPET,
            Items.PURPLE_CARPET,
            Items.RED_CARPET,
            Items.WHITE_CARPET,
            Items.YELLOW_CARPET,
            Items.RAIL,
            Items.ACTIVATOR_RAIL,
            Items.DETECTOR_RAIL,
            Items.POWERED_RAIL
    );
    public AttachmentConceptCore(Properties props) {
        super(props, 2, validItems);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = InteroperationWand.readTagWandCore(ctx.getItemInHand());

        if (ItemContainable.hasContent(core)) {
            Block block = ItemContainable.getBlock(core);
            return ConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), block);
        } else {
            return InteractionResult.PASS;
        }
    }

}
