package to.uk.ilexiconn.jurassicraft.client.render.tile;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.client.model.block.ModelDNAExtractorBase;
import to.uk.ilexiconn.jurassicraft.client.model.block.ModelDNAExtractorGlass;
import to.uk.ilexiconn.jurassicraft.tile.TileDNAExtractor;

public class TileDNAExtractorRenderer extends TileEntitySpecialRenderer
{

    private static final ResourceLocation texture = new ResourceLocation(Util.getModId() + "textures/blocks/dnaExtractor.png");
    private ModelDNAExtractorBase modelBase = new ModelDNAExtractorBase();
    private ModelDNAExtractorGlass modelGlass = new ModelDNAExtractorGlass();

    public TileDNAExtractorRenderer()
    {

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        if (tileEntity instanceof TileDNAExtractor)
        {
            TileDNAExtractor tileEntityModel = (TileDNAExtractor) tileEntity;
            int angle = 0;
            if (tileEntityModel.getWorldObj() == null)
            {
                angle = 0;
            }
            else
            {
                int direction = tileEntityModel.getBlockMetadata();
                switch (direction)
                {
                    case 0:
                        angle = -180;
                        break;
                    case 1:
                        angle = -90;
                        break;
                    case 2:
                        angle = 0;
                        break;
                    case 3:
                        angle = -270;
                        break;
                    default:
                        angle = -180;
                        break;
                }
            }
            GL11.glPushMatrix();
            GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(angle + 180, 0.0F, 1.0F, 0F);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            this.modelBase.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.55F);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            this.modelGlass.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glPopMatrix();
        }
    }
}