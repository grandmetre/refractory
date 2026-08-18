package de.henry.refractory.client;

import de.henry.refractory.CableMachineMenu;
import de.henry.refractory.ModItems;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public final class CableMachineScreen extends AbstractContainerScreen<CableMachineMenu> {
    public CableMachineScreen(CableMachineMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        imageWidth = 176;
        imageHeight = 146;
        inventoryLabelY = 52;
    }

    @Override
    protected void renderBg(GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        int left = leftPos;
        int top = topPos;
        graphics.fill(left, top, left + imageWidth, top + imageHeight, 0xffc6c6c6);
        graphics.fill(left + 3, top + 3, left + imageWidth - 3, top + imageHeight - 3, 0xff8b8b8b);
        graphics.fill(left + 4, top + 4, left + imageWidth - 4, top + imageHeight - 4, 0xffc6c6c6);
        drawSlot(graphics, left + 61, top + 26);
        drawSlot(graphics, left + 97, top + 26);
        for (int row = 0; row < 3; row++) for (int column = 0; column < 9; column++)
            drawSlot(graphics, left + 7 + column * 18, top + 63 + row * 18);
        for (int column = 0; column < 9; column++) drawSlot(graphics, left + 7 + column * 18, top + 121);
        graphics.renderItem(new ItemStack(ModItems.RUBBER.get()), left + 43, top + 27);
        graphics.renderItem(new ItemStack(Items.COPPER_INGOT), left + 116, top + 27);
    }

    private static void drawSlot(GuiGraphics graphics, int x, int y) {
        graphics.fill(x, y, x + 18, y + 18, 0xff373737);
        graphics.fill(x + 1, y + 1, x + 18, y + 18, 0xffffffff);
        graphics.fill(x + 1, y + 1, x + 17, y + 17, 0xff8b8b8b);
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
