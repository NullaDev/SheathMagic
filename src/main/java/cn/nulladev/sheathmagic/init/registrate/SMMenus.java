package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.client.gui.CrystalMenu;
import cn.nulladev.sheathmagic.client.gui.CrystalScreen;
import dev.xkmc.l2library.repack.registrate.util.entry.MenuEntry;

import static cn.nulladev.sheathmagic.init.SheathMagic.REGISTRATE;

public class SMMenus {
    public static final MenuEntry<CrystalMenu> MENU_CRYSTAL = REGISTRATE.menu("space_crystal", CrystalMenu::fromNetwork, () -> CrystalScreen::new).register();

    public static void register() {}
}
