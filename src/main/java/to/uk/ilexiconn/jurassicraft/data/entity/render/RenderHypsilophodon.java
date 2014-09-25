package to.uk.ilexiconn.jurassicraft.data.entity.render;

import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import to.uk.ilexiconn.jurassicraft.JurassiCraft;
import to.uk.ilexiconn.jurassicraft.data.entity.Dinosaur;
import to.uk.ilexiconn.jurassicraft.data.entity.RenderDinosaur;
import to.uk.ilexiconn.jurassicraft.data.entity.entity.EntityHypsilophodon;
import to.uk.ilexiconn.jurassicraft.data.entity.model.ModelHypsilophodon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderHypsilophodon extends RenderDinosaur {
	
	public RenderHypsilophodon(Dinosaur dino) {
		super(new ModelHypsilophodon(), dino, 0.55F);
	}

	public ResourceLocation getEntityTexture(Entity var1) {
		EntityHypsilophodon dino = (EntityHypsilophodon) var1;
		switch (dino.getCreatureTexture()) {
			case 0:
			default:
				return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/hypsilophodon1.png");
			case 1:
				return new ResourceLocation(JurassiCraft.getModId() + "textures/entity/hypsilophodon2.png");
		}
	}
}
