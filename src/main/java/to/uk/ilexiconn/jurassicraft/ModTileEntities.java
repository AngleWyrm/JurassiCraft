package to.uk.ilexiconn.jurassicraft;

import cpw.mods.fml.common.registry.GameRegistry;
import to.uk.ilexiconn.jurassicraft.tile.TileCultivate;
import to.uk.ilexiconn.jurassicraft.tile.TileDNACombinator;
import to.uk.ilexiconn.jurassicraft.tile.TileDNAExtractor;
import to.uk.ilexiconn.llib.content.ContentHandler;
import to.uk.ilexiconn.llib.content.ContentType;

@ContentHandler(modid = "jurassicraft", type = ContentType.TILEENTITY)
public class ModTileEntities
{
    public void init()
    {
    	GameRegistry.registerTileEntity(TileDNACombinator.class, "jurassicraft:dna_combinator");
    	GameRegistry.registerTileEntity(TileDNAExtractor.class, "jurassicraft:dna_extractor");
    	GameRegistry.registerTileEntity(TileCultivate.class, "jurassicraft:cultivator");
    }
}