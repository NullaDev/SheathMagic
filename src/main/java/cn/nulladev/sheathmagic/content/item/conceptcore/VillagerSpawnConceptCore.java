package cn.nulladev.sheathmagic.content.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class VillagerSpawnConceptCore extends BaseConceptCore implements ConceptCoreWand {
    public VillagerSpawnConceptCore(Properties props) {
        super(props, 2400);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        double x = player.getX();
        double y = player.getY() + 0.5D;
        double z = player.getZ();

        Villager villager = new Villager(EntityType.VILLAGER, level);
        villager.setPos(new Vec3(x, y, z));
        villager.setAge(-1200 * 20);
        level.addFreshEntity(villager);

        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
}
