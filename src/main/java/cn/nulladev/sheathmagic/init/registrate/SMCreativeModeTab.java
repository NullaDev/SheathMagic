package cn.nulladev.sheathmagic.init.registrate;

import cn.nulladev.sheathmagic.init.SheathMagic;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class SMCreativeModeTab extends CreativeModeTab {
    public static final SMCreativeModeTab INSTANCE = new SMCreativeModeTab();

    public SMCreativeModeTab() {
        super(SheathMagic.MODID);
    }

    @Override
    public ItemStack makeIcon() {
        return SMItems.INTEROPERATION_WAND.get().getDefaultInstance();
    }


    static {
        SheathMagic.REGISTRATE.creativeModeTab(() -> SMCreativeModeTab.INSTANCE);
    }

    public static void register() {}
}
