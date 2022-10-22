package cn.nulladev.sheathmagic.content.block.entity;

import cn.nulladev.sheathmagic.client.gui.InteroperatorMenu;
import cn.nulladev.sheathmagic.content.item.conceptcore.BaseConceptCore;
import cn.nulladev.sheathmagic.init.registrate.SMMenus;
import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class WorldInteroperatorEntity extends BaseContainerBlockEntity implements WorldlyContainer {

    public final SimpleContainer container = new SimpleContainer(InteroperatorMenu.SIZE);

    public static BlockEntityTicker<WorldInteroperatorEntity> ticker = (level, pos, state, blockEntity) -> blockEntity.serverTick(blockEntity);

    public WorldInteroperatorEntity(BlockEntityType<WorldInteroperatorEntity> type, BlockPos blockPos, BlockState blockState) {
        super(type, blockPos, blockState);
    }


    // TODO
    private void serverTick(WorldInteroperatorEntity entity) {
        System.out.println("Ticking");
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.world_interoperator");
    }

    @Override
    protected AbstractContainerMenu createMenu(int windowId, Inventory inventory) {
        return new InteroperatorMenu(SMMenus.MENU_INTEROPERATOR.get(), windowId, inventory, container);
    }

    @Override
    public int getContainerSize() {
        return container.getContainerSize();
    }

    @Override
    public boolean isEmpty() {
        return container.isEmpty();
    }

    @Override
    public ItemStack getItem(int index) {
        return container.getItem(index);
    }

    @Override
    public ItemStack removeItem(int index, int num) {
        this.setChanged();
        return container.removeItem(index, num);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return this.container.removeItemNoUpdate(index);
    }

    @Override
    public void setItem(int index, ItemStack stack) {
        this.setChanged();
        this.container.setItem(index, stack);
    }

    @Override
    public boolean stillValid(Player player) {
        return this.level.getBlockEntity(this.worldPosition) == this;
    }

    @Override
    public void clearContent() {
        this.setChanged();
        this.container.clearContent();
    }


    @Override
    public int[] getSlotsForFace(Direction p_19238_) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int p_19235_, ItemStack p_19236_, @Nullable Direction p_19237_) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int p_19239_, ItemStack p_19240_, Direction p_19241_) {
        return false;
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 0) {
            return stack.getItem() instanceof BaseConceptCore;
        }
        else {
            return false;
        }
    }
}
