package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;

public class FallingBlockConceptCore extends BaseContainableConceptCore implements ConceptCoreWand {
    public static final List<Item> validItems = List.of(Items.GRAVEL, Items.SAND);

    public FallingBlockConceptCore(Properties props) {
        super(props, 2, validItems);
    }


    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        ItemStack core = InteroperationWand.readTagWandCore(ctx.getItemInHand());
        if (ItemContainable.hasContent(core)) {
            Block b = ItemContainable.getBlock(core);
            return ConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), b);
        } else {
            return InteractionResult.PASS;
        }
    }
}
