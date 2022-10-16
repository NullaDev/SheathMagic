package cn.nulladev.sheathmagic.client.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.xkmc.l2library.base.menu.BaseContainerScreen;
import dev.xkmc.l2library.base.menu.SpriteManager;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;

import javax.annotation.Nullable;

public class CrystalScreen extends BaseContainerScreen<CrystalMenu> {
    public CrystalScreen(CrystalMenu cont, Inventory plInv, Component title) {
        super(cont, plInv, title);
    }

    @Override
    public void render(PoseStack p_98418_, int p_98419_, int p_98420_, float p_98421_) {
        this.renderBackground(p_98418_);
        super.render(p_98418_, p_98419_, p_98420_, p_98421_);
        this.renderTooltip(p_98418_, p_98419_, p_98420_);
    }

    @Override
    protected void renderBg(PoseStack matrix, float p_97788_, int p_97789_, int p_97790_) {
        SpriteManager sm = menu.sprite;
        SpriteManager.ScreenRenderer sr = sm.getRenderer(this);
        sr.start(matrix);
    }

    @Override
    protected void slotClicked(@Nullable Slot slot, int index, int key, ClickType type) {
        if (slot != null && slot.index == 36 + menu.getSize() * menu.getSize() && slot.hasItem()) {
            this.onClose();
        } else {
            super.slotClicked(slot, index, key, type);
        }
    }
}
