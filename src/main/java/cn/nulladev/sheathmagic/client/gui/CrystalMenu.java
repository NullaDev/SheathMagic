package cn.nulladev.sheathmagic.client.gui;

import cn.nulladev.sheathmagic.content.crafting.AbstractCrystalRecipe;
import cn.nulladev.sheathmagic.content.item.SpaceCrystal;
import cn.nulladev.sheathmagic.init.SheathMagic;
import cn.nulladev.sheathmagic.init.registrate.SMItems;
import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import dev.xkmc.l2library.base.menu.BaseContainerMenu;
import dev.xkmc.l2library.base.menu.SpriteManager;
import dev.xkmc.l2library.base.recipe.BaseRecipe;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

import java.util.Objects;
import java.util.Optional;

public class CrystalMenu extends BaseContainerMenu<CrystalMenu> {

    public static class CrystalContainer extends BaseContainer<CrystalMenu> implements BaseRecipe.RecInv<AbstractCrystalRecipe<?>> {

        public CrystalContainer(int size, CrystalMenu menu) {
            super(size, menu);
        }

        public int getWidth() {
            return parent.getSize();
        }
    }

    public static final SpriteManager SPRITE_3 = new SpriteManager(SheathMagic.MODID, "crystal_3");
    public static final SpriteManager SPRITE_4 = new SpriteManager(SheathMagic.MODID, "crystal_4");
    public static final SpriteManager SPRITE_5 = new SpriteManager(SheathMagic.MODID, "crystal_5");

    private final Player player;

    private final ItemStack crystal;

    public CrystalMenu(MenuType<CrystalMenu> type, int windowID, Inventory inventory, ItemStack crystal) {
        super(type, windowID, inventory, getSprite(crystal),
                menu -> new CrystalContainer(SpaceCrystal.getSize(crystal) * SpaceCrystal.getSize(crystal) + 1, menu),
                true);
        this.player = inventory.player;
        this.crystal = crystal;
        this.addSlot("grid", stack -> true);
        this.addSlot("output_slot", stack -> false, slot -> slot.setPickup(() -> false));
    }

    public static CrystalMenu fromNetwork(MenuType<CrystalMenu> type, int windowId, Inventory inv, FriendlyByteBuf buf) {
        InteractionHand hand = buf.readBoolean() ? InteractionHand.MAIN_HAND : InteractionHand.OFF_HAND;
        return new CrystalMenu(type, windowId, inv, inv.player.getItemInHand(hand));
    }

    public int getSize() {
        return SpaceCrystal.getSize(this.crystal);
    }

    public static SpriteManager getSprite(ItemStack crystal) {
        if (crystal.getItem() == SMItems.SPACE_CRYSTAL_ULTIMATE.get()) {
            return SPRITE_5;
        }
        else if (crystal.getItem() == SMItems.SPACE_CRYSTAL_ADVANCED.get()) {
            return SPRITE_4;
        }
        else {
            return SPRITE_3;
        }
    }

    @Override
    protected boolean shouldLock(Inventory inv, int slot) {
        return slot == inv.selected && inv.getItem(slot).getItem() instanceof SpaceCrystal;
    }

    @Override
    public void slotsChanged(Container container) {
        if (!this.player.level.isClientSide) {
            Optional<AbstractCrystalRecipe<?>> optional = Objects.requireNonNull(player.getServer())
                    .getRecipeManager()
                    .getRecipeFor(
                            SMRecipes.RECIPE_TYPE_CRYSTAL.get(),
                            (CrystalContainer) this.container,
                            this.player.level
                    );

            if (optional.isPresent()) {
                AbstractCrystalRecipe<?> recipe = optional.get();
                ItemStack itemstack = recipe.assemble((CrystalContainer) this.container);
                this.container.setItem(getSize() * getSize(), itemstack);
            }
        }
        super.slotsChanged(container);
    }

    @Override
    protected void clearContainer(Player player, Container container) {
        Optional<AbstractCrystalRecipe<?>> optional = Objects.requireNonNull(player.getServer())
                .getRecipeManager()
                .getRecipeFor(
                        SMRecipes.RECIPE_TYPE_CRYSTAL.get(),
                        (CrystalContainer) this.container,
                        this.player.level
                );

        if (optional.isPresent()) {
            for (int i = 0; i < getSize() * getSize(); i++) {
                ItemStack stack = container.getItem(i);
                if (!stack.isEmpty())
                    stack.shrink(1);
            }
            crystal.shrink(1);
        }
        super.clearContainer(player, container);
    }

}
