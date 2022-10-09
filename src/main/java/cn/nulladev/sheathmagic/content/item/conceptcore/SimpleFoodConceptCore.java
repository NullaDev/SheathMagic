package cn.nulladev.sheathmagic.content.item.conceptcore;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SimpleFoodConceptCore extends BaseConceptCore implements ConceptCoreWand {
    private final int hunger;
    private final float saturation;

    public SimpleFoodConceptCore(Properties props, int cooldown, int hunger, float saturation) {
        super(props, cooldown);
        this.hunger = hunger;
        this.saturation = saturation;
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            player.getFoodData().eat(hunger, saturation);
            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
