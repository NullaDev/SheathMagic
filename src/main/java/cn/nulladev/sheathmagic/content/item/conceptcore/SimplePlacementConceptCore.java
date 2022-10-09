package cn.nulladev.sheathmagic.content.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class SimplePlacementConceptCore extends BaseConceptCore implements ConceptCoreWand {
    private final Block block;

    public SimplePlacementConceptCore(Properties props, int cooldown, Block block) {
        super(props, cooldown);
        this.block = block;
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return ConceptCoreWand.placeBlock(new BlockPlaceContext(ctx), block);
    }
}
