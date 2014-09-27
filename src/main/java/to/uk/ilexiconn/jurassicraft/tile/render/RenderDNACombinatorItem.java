package to.uk.ilexiconn.jurassicraft.tile.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;
import to.uk.ilexiconn.jurassicraft.tile.TileDNACombinator;

public class RenderDNACombinatorItem implements IItemRenderer
{

    private TileEntitySpecialRenderer render = new DNACombinatorRenderer();
    private TileEntity tileEntity = new TileDNACombinator();

    public RenderDNACombinatorItem()
    {

    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if (type == IItemRenderer.ItemRenderType.ENTITY)
        {
            GL11.glTranslatef(-0.5F, 1.75F, -0.5F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
        }
        else if (type == IItemRenderer.ItemRenderType.INVENTORY)
        {
            GL11.glTranslatef(-0.45F, 1.4F, -0.5F);
            GL11.glScalef(1.2F, 1.2F, 1.2F);
        }
        else if (type == IItemRenderer.ItemRenderType.EQUIPPED_FIRST_PERSON)
        {
            GL11.glRotatef(-90F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.45F, 2.25F, -0.5F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
        }
        else if (type == IItemRenderer.ItemRenderType.EQUIPPED)
        {
            GL11.glTranslatef(-0.1F, 2.25F, -0.4F);
            GL11.glScalef(1.5F, 1.5F, 1.5F);
        }
        this.render.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}