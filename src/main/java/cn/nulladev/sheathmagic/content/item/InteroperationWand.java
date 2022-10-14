package cn.nulladev.sheathmagic.content.item;

import cn.nulladev.sheathmagic.content.item.conceptcore.BaseConceptCore;
import cn.nulladev.sheathmagic.content.item.conceptcore.ConceptCoreWand;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

public class InteroperationWand extends Item {
    private static final String TAG_WAND_CORE = "wand_core";


    public InteroperationWand(Properties props) {
        super(props.stacksTo(1));
    }

    public static void writeTagCore(ItemStack wand, ItemStack core) {
        CompoundTag tag = new CompoundTag();
        core.save(tag);
        wand.getOrCreateTag().put(TAG_WAND_CORE, tag);
    }

    public static ItemStack readTagWandCore(ItemStack wand) {
        if (wand.getOrCreateTag().contains(TAG_WAND_CORE)) {
            return ItemStack.of(wand.getOrCreateTag().getCompound(TAG_WAND_CORE));
        } else {
            return ItemStack.EMPTY;
        }
    }

    public static boolean hasCore(ItemStack wand) {
        return !(readTagWandCore(wand).isEmpty());
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean selected) {
        if (readCoreTagCooldown(itemStack) > 0) {
            writeCoreTagCooldown(itemStack, readCoreTagCooldown(itemStack) - 1);
        }
    }

    private static int readCoreTagCooldown(ItemStack wand) {
        ItemStack core = readTagWandCore(wand);

        if (!core.isEmpty())
            return BaseConceptCore.readTagCooldown(core);
        else
            return 0;
    }

    private static void writeCoreTagCooldown(ItemStack wand, int cd) {
        ItemStack core = readTagWandCore(wand);

        if (!core.isEmpty())
            BaseConceptCore.writeTagCooldown(core, cd);
    }

    private static int getCoreCooldown(ItemStack wand) {
        ItemStack core = readTagWandCore(wand);

        if (core.getItem() instanceof BaseConceptCore coreItem) {
            return coreItem.getTotalCooldown();
        } else {
            return 0;
        }
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return readCoreTagCooldown(stack) > 0;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        if (getCoreCooldown(stack) == 0)
            return 0;
        return Math.round(13F - 13F * readCoreTagCooldown(stack) / getCoreCooldown(stack));
    }

    @Override
    public int getBarColor(ItemStack stack) {
        if (getCoreCooldown(stack) == 0)
            return 0;
        float f = Math.max(0.0F, (getCoreCooldown(stack) - readCoreTagCooldown(stack)) / (float) getCoreCooldown(stack));
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    @Override
    public Component getName(ItemStack item) {
        if (hasCore(item)) {
            MutableComponent component = super.getName(item).plainCopy();
            component.append("(");
            component.append(readTagWandCore(item).getDisplayName().plainCopy().withStyle(ChatFormatting.GREEN));
            component.append(")");
            return component;
        } else {
            return super.getName(item);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> list, TooltipFlag flags) {
        if (!hasCore(stack)) {
            list.add(Component.translatable("sheathmagic.misc.missing_core").withStyle(ChatFormatting.RED));
        } else if (readCoreTagCooldown(stack) > 0) {
            list.add(Component.translatable("sheathmagic.misc.in_cooldown",
                    readCoreTagCooldown(stack)).withStyle(ChatFormatting.RED));
        } else {
            list.add(Component.translatable("sheathmagic.misc.available").withStyle(ChatFormatting.DARK_GREEN));
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (BaseConceptCore.canUse(ctx.getItemInHand()) && ctx.getPlayer() != null && !ctx.getPlayer().isCreative()) {
            return InteractionResult.PASS;
        }

        var coreItem = readTagWandCore(ctx.getItemInHand()).getItem();
        if (coreItem instanceof BaseConceptCore baseCore && coreItem instanceof ConceptCoreWand wandCore) {
            InteractionResult result = wandCore.wandUseOn(ctx);

            if (result != InteractionResult.PASS)
                writeCoreTagCooldown(ctx.getItemInHand(), baseCore.getTotalCooldown());
            return result;
        }

        return InteractionResult.PASS;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var itemStackInHand = player.getItemInHand(hand);
        if (BaseConceptCore.canUse(itemStackInHand) && !player.isCreative()) {
            return InteractionResultHolder.pass(itemStackInHand);
        }

        var coreItem = readTagWandCore(itemStackInHand).getItem();
        if (coreItem instanceof BaseConceptCore baseCore && coreItem instanceof ConceptCoreWand wandCore) {
            InteractionResultHolder<ItemStack> result = wandCore.wandUse(level, player, hand);
            if (result.getResult() != InteractionResult.PASS) {
                writeCoreTagCooldown(player.getItemInHand(hand), baseCore.getTotalCooldown());
                return InteractionResultHolder.success(itemStackInHand);
            }
        }

        return InteractionResultHolder.pass(itemStackInHand);
    }

    @Override
    public boolean overrideStackedOnOther(ItemStack wand, Slot slot, ClickAction clickAction, Player player) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            var slotStack = slot.getItem();
            if (slotStack.isEmpty()) {
                removeCore(wand).ifPresent(slot::safeInsert);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean overrideOtherStackedOnMe(ItemStack wand, ItemStack slotStack, Slot slot, ClickAction clickAction, Player player, SlotAccess slotAccess) {
        if (clickAction == ClickAction.SECONDARY && slot.allowModification(player)) {
            if (slotStack.isEmpty()) {
                removeCore(wand).ifPresent(slotAccess::set);
                return true;
            } else {
                if (slotStack.getItem() instanceof ConceptCoreWand) {
                    InteroperationWand.writeTagCore(wand, slotStack);
                    slotStack.shrink(1);
                    return true;
                }
            }
        }
        return false;
    }

    private static Optional<ItemStack> removeCore(ItemStack wand) {
        if (!InteroperationWand.hasCore(wand)) {
            return Optional.empty();
        } else {
            var core = InteroperationWand.readTagWandCore(wand);
            writeTagCore(wand, ItemStack.EMPTY);
            return Optional.of(core);
        }
    }
}
