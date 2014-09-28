package to.uk.ilexiconn.jurassicraft.entity.render;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.entity.Dinosaur;
import to.uk.ilexiconn.jurassicraft.entity.dinosaur.EntityVelociraptor;
import to.uk.ilexiconn.jurassicraft.client.model.entity.ModelVelociraptor;

public class RenderVelociraptor extends RenderDinosaur
{
    public RenderVelociraptor(Dinosaur dino)
    {
        super(new ModelVelociraptor(), dino, 0.65F);
    }

    public ResourceLocation getEntityTexture(Entity var1)
    {
        EntityVelociraptor dino = (EntityVelociraptor) var1;
        switch (dino.getCreatureTexture())
        {
            case 0:
                return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/velociraptor1.png");
            case 1:
                return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/velociraptor2.png");
            default:
                return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/velociraptor1.png");
        }
    }
}
