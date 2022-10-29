package cn.nulladev.sheathmagic.content.block;

import cn.nulladev.sheathmagic.client.gui.InteroperatorMenu;
import cn.nulladev.sheathmagic.content.item.ItemContainable;
import cn.nulladev.sheathmagic.content.item.ItemCooldown;
import cn.nulladev.sheathmagic.content.item.conceptcore.BaseConceptCore;
import cn.nulladev.sheathmagic.init.registrate.SMMenus;
import cn.nulladev.sheathmagic.init.registrate.SMRecipes;
import dev.xkmc.l2library.block.NameSetable;
import dev.xkmc.l2library.block.TickableBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.stream.IntStream;

public class WorldInteroperatorEntity extends BaseContainerBlockEntity implements WorldlyContainer,
        MenuProvider, TickableBlockEntity, NameSetable {

    public final SimpleContainer container = new SimpleContainer(InteroperatorMenu.SIZE);

    public WorldInteroperatorEntity(BlockEntityType<WorldInteroperatorEntity> type, BlockPos blockPos, BlockState blockState) {
        super(type, blockPos, blockState);
        this.container.addListener(container -> setChanged());
    }

    @Override
    public void tick() {
        var inputStack = container.getItem(0);

        if (BaseConceptCore.canUse(inputStack)) {
            addOutputs(inputStack);
        }

        updateConceptCoreCooldown(inputStack);

    }

    private void addOutputs(ItemStack input) {
        Objects.requireNonNull(level).getRecipeManager().getRecipeFor(SMRecipes.RECIPE_TYPE_CORE_OUTPUT.get(), this.container, this.level).ifPresent(
                (recipe) -> {
                    if (input.getItem() == recipe.input.getItem() &&
                            (ItemContainable.readTagContent(recipe.input).isEmpty() || ItemContainable.readTagContent(input).getItem() == ItemContainable.readTagContent(recipe.input).getItem())) {
                        for (var entry : recipe.outputs.entrySet()) {
                            var stack = entry.getKey();
                            var possibility = entry.getValue();
                            if (Math.random() * 100 <= possibility) {
                                if (container.canAddItem(stack)){
                                    container.addItem(stack);
                                }
                            }
                        }

                        resetConceptCoreCooldown(input);
                    }
                }
        );
    }

    private void updateConceptCoreCooldown(ItemStack item) {
        if (!BaseConceptCore.canUse(item)) {
            ItemCooldown.writeTagCooldown(item, ItemCooldown.readTagCooldown(item) - 1);
        }
    }

    private void resetConceptCoreCooldown(ItemStack item) {
        if (item.getItem() instanceof BaseConceptCore core){
            ItemCooldown.writeTagCooldown(item, core.getTotalCooldown());
        }
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
        return Objects.requireNonNull(level).getBlockEntity(this.worldPosition) == this;
    }

    @Override
    public void clearContent() {
        this.setChanged();
        this.container.clearContent();
    }

    @Override
    public boolean canPlaceItem(int index, ItemStack stack) {
        if (index == 0) {
            return stack.getItem() instanceof BaseConceptCore;
        } else {
            return false;
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("items", container.createTag());
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("items")) {
            container.fromTag((ListTag) Objects.requireNonNull(tag.get("items")));
        }
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return (LazyOptional<T>) LazyOptional.of(() -> new SidedInvWrapper(this, side));
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public int[] getSlotsForFace(Direction direction) {
        if (direction == Direction.UP) {
            return new int[]{0};
        } else {
            return IntStream.rangeClosed(1, 27).toArray();
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int index, ItemStack stack, @Nullable Direction direction) {
        return this.canPlaceItem(index, stack);
    }

    @Override
    public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction != Direction.UP || stack.getItem() instanceof BaseConceptCore;
    }
}
