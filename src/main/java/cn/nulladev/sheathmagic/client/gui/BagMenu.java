package cn.nulladev.sheathmagic.client.gui;

import cn.nulladev.sheathmagic.content.item.ConceptCoreBag;
import cn.nulladev.sheathmagic.content.item.conceptcore.BaseConceptCore;
import cn.nulladev.sheathmagic.init.SheathMagic;
import dev.xkmc.l2library.base.menu.BaseContainerMenu;
import dev.xkmc.l2library.base.menu.SpriteManager;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class BagMenu extends BaseContainerMenu<BagMenu> {

    public static class CoreBagContainer extends BaseContainer<BagMenu> {

        public CoreBagContainer(BagMenu menu, ItemStack bag) {
            super(ConceptCoreBag.SIZE, menu);
            ListTag list = ConceptCoreBag.getListTag(bag);
            for (int i = 0; i < Math.min(ConceptCoreBag.SIZE, list.size()); i++) {
                this.setItem(i, ItemStack.of(list.getCompound(i)));
            }
        }

        @Override
        public boolean canPlaceItem(int index, ItemStack stack) {
            return stack.getItem() instanceof BaseConceptCore;
        }
    }

    public static final SpriteManager CORE_BAG = new SpriteManager(SheathMagic.MODID, "core_bag");

    private final ItemStack bag;


    public static BagMenu fromNetwork(MenuType<?> type, int windowId, Inventory inv, FriendlyByteBuf buf) {
        InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        return new BagMenu(type, windowId, inv, inv.player.getItemInHand(hand));
    }

    public BagMenu(MenuType<?> type, int wid, Inventory plInv, ItemStack bag) {
        super(type, wid, plInv, CORE_BAG, menu -> new CoreBagContainer(menu, bag), false);
        this.bag = bag;

        var player = inventory.player;
        this.addSlot("grid", stack -> stack.getItem() instanceof BaseConceptCore);
        if (!player.level.isClientSide()) {
            ListTag tag = ConceptCoreBag.getListTag(bag);
            for (int i = 0; i < tag.size(); i++) {
                this.container.setItem(i, ItemStack.of((CompoundTag) tag.get(i)));
            }
        }
    }


    @Override
    protected boolean shouldLock(Inventory inv, int slot) {
        return slot == inv.selected && inv.getItem(slot).getItem() instanceof ConceptCoreBag;
    }

    @Override
    public void removed(Player player) {
        if (!player.level.isClientSide) {
            ListTag list = new ListTag();
            for (int i = 0; i < this.container.getContainerSize(); i++) {
                list.add(i, this.container.getItem(i).save(new CompoundTag()));
            }
            ConceptCoreBag.setListTag(this.bag, list);
        }
        super.removed(player);
    }
}
