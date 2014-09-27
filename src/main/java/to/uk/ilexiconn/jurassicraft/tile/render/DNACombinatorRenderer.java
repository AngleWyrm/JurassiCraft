package to.uk.ilexiconn.jurassicraft.tile.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.Util;
import to.uk.ilexiconn.jurassicraft.block.model.ModelDnaCombiner;
import to.uk.ilexiconn.jurassicraft.tile.TileDNACombinator;

public class DNACombinatorRenderer extends TileEntitySpecialRenderer
{

    private static final ResourceLocation texture = new ResourceLocation(Util.getModId() + "textures/blocks/dnaCombinator.png");
    private ModelDnaCombiner model = new ModelDnaCombiner();

    public DNACombinatorRenderer()
    {

    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
        if (tileEntity instanceof TileDNACombinator)
        {
            TileDNACombinator tileEntityModel = (TileDNACombinator) tileEntity;
            int angle = 0;
            GL11.glPushMatrix();
            if (tileEntityModel.getWorldObj() == null)
            {
                angle = 0;
            }
            else
            {
                int direction = tileEntityModel.getBlockMetadata();
                switch (direction)
                {
                    case 2:
                        angle = -180;
                        GL11.glTranslatef((float) x + 1.0F, (float) y + 1.5F, (float) z + 1.0F);
                        break;
                    case 3:
                        angle = 0;
                        GL11.glTranslatef((float) x, (float) y + 1.5F, (float) z);
                        break;
                    case 4:
                        angle = -270;
                        GL11.glTranslatef((float) x + 1.0F, (float) y + 1.5F, (float) z);
                        break;
                    case 5:
                        angle = -90;
                        GL11.glTranslatef((float) x, (float) y + 1.5F, (float) z + 1.0F);
                        break;
                    default:
                        angle = -180;
                        GL11.glTranslatef((float) x, (float) y + 1.5F, (float) z);
                        break;
                }
            }
            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(angle + 180, 0.0F, 1.0F, 0F);
            Minecraft.getMinecraft().renderEngine.bindTexture(texture);
            this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
            GL11.glPopMatrix();
        }
    }
}