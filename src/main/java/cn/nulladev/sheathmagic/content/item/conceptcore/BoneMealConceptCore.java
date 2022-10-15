package cn.nulladev.sheathmagic.content.item.conceptcore;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.BonemealableBlock;

public class BoneMealConceptCore extends BaseConceptCore implements ConceptCoreWand {
    public BoneMealConceptCore(Properties props, int cooldown) {
        super(props, cooldown);
    }

    @Override
    public InteractionResult wandUseOn(UseOnContext ctx) {
        return fertilize(ctx);
    }

    static InteractionResult fertilize(UseOnContext ctx) {
        var level = ctx.getLevel();
        var blockPos = ctx.getClickedPos();
        var targetBlockState = level.getBlockState(blockPos);

        if (targetBlockState.getBlock() instanceof BonemealableBlock bonemealableBlock) {
            if (bonemealableBlock.isValidBonemealTarget(level, blockPos, targetBlockState, level.isClientSide)) {
                if (level instanceof ServerLevel) {
                    if (bonemealableBlock.isBonemealSuccess(level, level.random, blockPos, targetBlockState)) {
                        bonemealableBlock.performBonemeal((ServerLevel) level, level.random, blockPos, targetBlockState);
                    }

                    level.levelEvent(1505, blockPos, 0);
                }

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
}
