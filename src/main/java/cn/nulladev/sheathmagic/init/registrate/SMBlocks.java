package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.content.block.WorldInteroperator;
import cn.nulladev.sheathmagic.content.block.WorldInteroperatorEntity;
import cn.nulladev.sheathmagic.init.SheathMagic;
import dev.xkmc.l2library.block.DelegateBlock;
import dev.xkmc.l2library.block.DelegateBlockProperties;
import dev.xkmc.l2library.repack.registrate.util.entry.BlockEntityEntry;
import dev.xkmc.l2library.repack.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;

import static cn.nulladev.sheathmagic.init.SheathMagic.REGISTRATE;

public class SMBlocks {

	private static final DelegateBlockProperties GENERAL_PROP = DelegateBlockProperties.copy(Blocks.STONE);

	/* Blocks */
	public static final BlockEntry<DelegateBlock> WORLD_INTEROPERATOR =
			REGISTRATE.block("world_interoperator", p -> DelegateBlock.newBaseBlock(
					GENERAL_PROP, WorldInteroperator.BLOCK_ENTITY
			)).blockstate((ctx, pvd) -> pvd.simpleBlock(ctx.getEntry(), pvd.models().cubeTop(
					ctx.getName(),
					new ResourceLocation(SheathMagic.MODID, "block/" + ctx.getName() + "_side"),
					new ResourceLocation(SheathMagic.MODID, "block/" + ctx.getName() + "_top")
			))).item().build().register();


	/* Block Entities */
	public static final BlockEntityEntry<WorldInteroperatorEntity> WORLD_INTEROPERATOR_ENTITY =
			REGISTRATE.blockEntity("world_interoperator", WorldInteroperatorEntity::new).validBlock(WORLD_INTEROPERATOR).register();

	public static void register() {
	}
}
