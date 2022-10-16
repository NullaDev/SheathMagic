package cn.nulladev.sheathmagic.content.item.conceptcore;

import cn.nulladev.sheathmagic.content.item.InteroperationWand;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

import java.util.List;
import java.util.Random;

public class FarmConceptCore extends BaseContainableConceptCore implements ConceptCoreWand {
    public static final List<Item> validItems = List.of(Items.POTATO, Items.CARROT, Items.BEETROOT_SEEDS, Items.WHEAT_SEEDS);
    public FarmConceptCore(Properties props) {
        super(props, 600, validItems);
    }

    @Override
    public InteractionResultHolder<ItemStack> wandUse(Level level, Player player, InteractionHand hand) {
        if (player.canEat(false)) {
            ItemStack core = InteroperationWand.readTagWandCore(player.getItemInHand(hand));
            ItemStack content = ItemContainable.readTagContent(core);

            if (content.getItem() == Items.POTATO) {
                player.getFoodData().eat(1, 0.6F);
            } else if (content.getItem() == Items.CARROT) {
                player.getFoodData().eat(3, 1.2F);
            } else if (content.getItem() == Items.BEETROOT_SEEDS) {
                player.getFoodData().eat(1, 1.2F);
            } else if (content.getItem() == Items.WHEAT_SEEDS) {
                if (new Random().nextInt(3) == 0) {
                    player.getFoodData().eat(5, 1.2F);
                }
            }

            return InteractionResultHolder.success(player.getItemInHand(hand));
        } else {
            return InteractionResultHolder.pass(player.getItemInHand(hand));
        }
    }
}
