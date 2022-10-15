package cn.nulladev.sheathmagic.content.item.conceptcore;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Blocks;

public class FrameBreakerConceptCore extends BaseConceptCore implements ConceptCoreWand {
    public FrameBreakerConceptCore(Properties props) {
        super(props, 20);
    }


    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return ConceptCoreWand.removeBlock(ctx, Blocks.END_PORTAL_FRAME);
    }
}
