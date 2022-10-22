package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.client.gui.*;
import dev.xkmc.l2library.repack.registrate.util.entry.MenuEntry;

import static cn.nulladev.sheathmagic.init.SheathMagic.REGISTRATE;

public class SMMenus {
    public static final MenuEntry<CrystalMenu> MENU_CRYSTAL =
            REGISTRATE.menu("space_crystal", CrystalMenu::fromNetwork, () -> CrystalScreen::new).register();
    public static final MenuEntry<BagMenu> MENU_BAG =
            REGISTRATE.menu("core_bag", BagMenu::fromNetwork, () -> BagScreen::new).register();

    public static final MenuEntry<InteroperatorMenu> MENU_INTEROPERATOR =
            REGISTRATE.menu(
                    "core_machine",
                    (type, id, inv) -> new InteroperatorMenu(type, id, inv),
                    () -> InteroperatorScreen::new
            ).register();

    public static void register() {}
}
