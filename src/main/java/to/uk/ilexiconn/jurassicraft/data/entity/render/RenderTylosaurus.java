package to.uk.ilexiconn.jurassicraft.data.entity.render;

import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.data.entity.Dinosaur;
import to.uk.ilexiconn.jurassicraft.data.entity.RenderDinosaur;
import to.uk.ilexiconn.jurassicraft.data.entity.RenderSwimming;
import to.uk.ilexiconn.jurassicraft.data.entity.model.ModelTylosaurus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderTylosaurus extends RenderSwimming
{
    public RenderTylosaurus(Dinosaur dino)
    {
        super(new ModelTylosaurus(), dino, 1.0F);
    }

    public ResourceLocation getEntityTexture(Entity var1)
    {
        return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/Tylosaurus1.png");
    }
}
