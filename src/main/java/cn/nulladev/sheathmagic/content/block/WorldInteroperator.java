package cn.nulladev.sheathmagic.content.block;

import cn.nulladev.sheathmagic.init.registrate.SMBlocks;
import dev.xkmc.l2library.block.impl.BlockEntityBlockMethodImpl;
import dev.xkmc.l2library.block.type.BlockMethod;

public class WorldInteroperator {
	public static final BlockMethod BLOCK_ENTITY = new BlockEntityBlockMethodImpl<>(SMBlocks.WORLD_INTEROPERATOR_ENTITY, WorldInteroperatorEntity.class);

}
