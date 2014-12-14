package to.uk.ilexiconn.jurassicraft.gen;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import to.uk.ilexiconn.jurassicraft.ModBlocks;

import java.util.Random;

public class WorldGenAmberOre implements IWorldGenerator
{
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (world.provider.dimensionId == 0)
        {
            int x, y, z;

            x = random.nextInt(16) + (chunkX * 16);
            y = random.nextInt(7);
            z = random.nextInt(16) + (chunkZ * 16);

            (new WorldGenMinable(ModBlocks.amberOre, 7)).generate(world, random, x, y, z);
        }
    }
}
