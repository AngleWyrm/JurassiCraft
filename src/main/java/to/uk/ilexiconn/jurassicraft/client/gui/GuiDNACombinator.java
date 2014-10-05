package to.uk.ilexiconn.jurassicraft.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.container.ContainerDNACombinator;
import to.uk.ilexiconn.jurassicraft.tile.TileDNACombinator;

public class GuiDNACombinator extends GuiContainer
{

    private TileDNACombinator dnaCombinator;

    public GuiDNACombinator(InventoryPlayer inventoryPlayer, TileDNACombinator tileEntity)
    {
        super(new ContainerDNACombinator(inventoryPlayer, tileEntity));
        this.dnaCombinator = tileEntity;
        this.xSize = 176;
        this.ySize = 188;
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.dnaCombinator"), this.xSize - this.fontRendererObj.getStringWidth("container.dnaCombinator"), 7, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 94, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        mc.renderEngine.bindTexture(new ResourceLocation(Util.getModId() + "textures/gui/guiDNACombinator.png"));
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        int i = this.dnaCombinator.getCombinationProgressScaled(22);
        this.drawTexturedModalRect(guiLeft + 93, guiTop + 36, 176, 0, 9, i);
    }
}
