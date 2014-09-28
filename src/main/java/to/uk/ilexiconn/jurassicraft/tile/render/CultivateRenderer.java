package to.uk.ilexiconn.jurassicraft.tile.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.ModBlocks;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.block.BlockCultivate;
import to.uk.ilexiconn.jurassicraft.block.model.ModelCultivate;
import to.uk.ilexiconn.jurassicraft.block.model.ModelEmbryo;
import to.uk.ilexiconn.jurassicraft.tile.TileCultivate;
import to.uk.ilexiconn.llib.client.render.RenderHelper;

public class CultivateRenderer extends TileEntitySpecialRenderer
{
    public String[] colors = {"black", "red", "green", "brown", "blue", "purple", "cyan", "light_gray", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white"};
    public ModelCultivate cultivate = new ModelCultivate();
    public ModelEmbryo embryo = new ModelEmbryo();
    public ResourceLocation[] cultivateTextures;
    public ResourceLocation embryoTextures;

    public CultivateRenderer()
    {
        cultivateTextures = new ResourceLocation[colors.length];
        for (int i = 0; i < colors.length; i++)
            cultivateTextures[i] = new ResourceLocation(Util.getModId() + "textures/blocks/cultivate_" + colors[i] + ".png");
        embryoTextures = new ResourceLocation(Util.getModId() + "textures/blocks/embryo.png");
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float meta)
    {
        GL11.glEnable(GL11.GL_BLEND);
        TileCultivate tile = (TileCultivate) tileEntity;

        if (tile.isHatching())
        {
            GL11.glPushMatrix();
            GL11.glColor4f(1f, 1f, 1f, 1f);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
            //GL11.glScalef(1f + (tile.progress / 10f + 0.1f), 1f + (tile.progress / 10f + 0.1f), 1f + (tile.progress / 10f + 0.1f));
            int rotation = BlockCultivate.getRotation(tile.getWorldObj(), tile.xCoord, tile.yCoord, tile.zCoord);
            GL11.glRotatef(rotation == 0 ? 0f : rotation == 1 ? -90f : rotation == 2 ? -180f : 90f, 0f, 1f, 0f);
            GL11.glRotatef(180f, 0f, 0f, 1f);
            Minecraft.getMinecraft().renderEngine.bindTexture(embryoTextures);
            embryo.render(tile);
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        GL11.glColor4f(1f, 1f, 1f, 1f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(cultivateTextures[tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)]);
        cultivate.render(false);
        GL11.glPopMatrix();

        int[] displayList = RenderHelper.getFluidDisplayLists(tile.getWorldObj(), ModBlocks.cultivateFluid, ModBlocks.cultivateLiquid);
        if (displayList != null)
        {
            GL11.glPushMatrix();
            GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            bindTexture(TextureMap.locationBlocksTexture);
            GL11.glTranslatef((float) x + 0.125f, (float) y + 1.29f, (float) z + 0.125f);
            GL11.glScalef(0.75f, 0.75f * 2.5f, 0.75f);
            GL11.glTranslatef(0, -0.5f, 0);
            GL11.glCallList(displayList[7]);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }

        GL11.glPushMatrix();
        GL11.glColor4f(1f, 1f, 1f, 0.7f);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef((float) x + 0.5f, (float) y + 1.5f, (float) z + 0.5f);
        GL11.glRotatef(180f, 0f, 0f, 1f);
        Minecraft.getMinecraft().renderEngine.bindTexture(cultivateTextures[tile.getWorldObj().getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)]);
        cultivate.renderGlass();
        GL11.glPopMatrix();
        GL11.glDisable(GL11.GL_BLEND);
    }
}
