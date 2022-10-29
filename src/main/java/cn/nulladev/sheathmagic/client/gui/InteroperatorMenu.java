package cn.nulladev.sheathmagic.client.gui;

import cn.nulladev.sheathmagic.content.item.conceptcore.BaseConceptCore;
import cn.nulladev.sheathmagic.init.SheathMagic;
import dev.xkmc.l2library.base.menu.BaseContainerMenu;
import dev.xkmc.l2library.base.menu.SpriteManager;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MenuType;

import java.util.function.Function;

public class InteroperatorMenu extends BaseContainerMenu<InteroperatorMenu> {

    public static final int SIZE = 28;

    public static final SpriteManager CORE_MACHINE = new SpriteManager(SheathMagic.MODID, "core_machine");

    public InteroperatorMenu(MenuType<?> type, int wid, Inventory plInv, Function<InteroperatorMenu, SimpleContainer> factory) {
        super(type, wid, plInv, CORE_MACHINE, factory, false);
        this.addSlot("core", stack -> stack.getItem() instanceof BaseConceptCore);
        this.addSlot("grid", stack -> false);
    }

    public InteroperatorMenu(MenuType<?> type, int windowId, Inventory inventory) {
        this(type, windowId, inventory, menu -> new BaseContainer<>(SIZE, menu));
    }

    public InteroperatorMenu(MenuType<?> type, int windowId, Inventory inventory, SimpleContainer container) {
        this(type, windowId, inventory, e -> container);
    }

    @Override
    protected boolean shouldLock(Inventory inv, int slot) {
        return slot >= 37 && slot <= 64;
    }
}
