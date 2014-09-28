package to.uk.ilexiconn.jurassicraft.entity.render;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.entity.Dinosaur;
import to.uk.ilexiconn.jurassicraft.entity.dinosaur.EntityCearadactylus;
import to.uk.ilexiconn.jurassicraft.client.model.entity.ModelCearadactylus;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCearadactylus extends RenderDinosaur
{
    public RenderCearadactylus(Dinosaur dino)
    {
        super(new ModelCearadactylus(), dino, 0.5F);
    }

    public ResourceLocation getEntityTexture(Entity var1)
    {
        EntityCearadactylus dino = (EntityCearadactylus) var1;
        if (dino.isMale())
        {
            return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/cearadactylus1.png");
        } else {
            return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/cearadactylus2.png");
        }
    }
}