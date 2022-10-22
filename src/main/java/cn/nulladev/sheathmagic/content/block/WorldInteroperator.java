package cn.nulladev.sheathmagic.content.block;

import cn.nulladev.sheathmagic.content.block.entity.WorldInteroperatorEntity;
import cn.nulladev.sheathmagic.init.registrate.SMBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class WorldInteroperator extends BaseEntityBlock {
    public WorldInteroperator(Properties props) {
        super(props);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new WorldInteroperatorEntity(SMBlocks.WORLD_INTEROPERATOR_ENTITY.get(), blockPos, blockState);
    }


    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide()) {
            return createTickerHelper(type, SMBlocks.WORLD_INTEROPERATOR_ENTITY.get(), WorldInteroperatorEntity.ticker);
        }
        return null;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_60508_) {
        if (!level.isClientSide) {
            if (level.getBlockEntity(pos) instanceof WorldInteroperatorEntity) {
                MenuProvider menu = (MenuProvider)level.getBlockEntity(pos);
                NetworkHooks.openScreen((ServerPlayer) player, menu, buf -> buf.writeBlockPos(pos));
                return InteractionResult.CONSUME;
            }
        }

        return InteractionResult.SUCCESS;
    }

}
