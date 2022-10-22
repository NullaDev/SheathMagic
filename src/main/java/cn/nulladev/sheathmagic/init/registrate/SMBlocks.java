package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.content.block.WorldInteroperator;
import cn.nulladev.sheathmagic.content.block.entity.WorldInteroperatorEntity;
import dev.xkmc.l2library.repack.registrate.util.entry.BlockEntityEntry;
import dev.xkmc.l2library.repack.registrate.util.entry.BlockEntry;
import dev.xkmc.l2library.repack.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.BlockItem;

import static cn.nulladev.sheathmagic.init.SheathMagic.REGISTRATE;

public class SMBlocks {
    /* Blocks */
    public static final BlockEntry<WorldInteroperator> WORLD_INTEROPERATOR =
            REGISTRATE.block("world_interoperator", WorldInteroperator::new).register();

    /* Block items */
    public static final ItemEntry<BlockItem> WORLD_INTEROPERATOR_ITEM =
            REGISTRATE.item("world_interoperator_item", (p) -> new BlockItem(WORLD_INTEROPERATOR.get(), p)).register();

    /* Block Entities */
    public static final BlockEntityEntry<WorldInteroperatorEntity> WORLD_INTEROPERATOR_ENTITY =
            REGISTRATE.blockEntity("world_interoperator", WorldInteroperatorEntity::new).register();

    public static void register() {
    }
}
