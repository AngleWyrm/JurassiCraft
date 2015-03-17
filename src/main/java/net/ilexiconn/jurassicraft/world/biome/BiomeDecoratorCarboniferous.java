package net.ilexiconn.jurassicraft.world.biome;

import net.ilexiconn.jurassicraft.world.core.DimensionProperties;
import net.ilexiconn.jurassicraft.world.feature.WorldGenSpiderLair;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;

/**
 * @author ProPercivalalb
 **/
public class BiomeDecoratorCarboniferous extends BiomeDecorator {

	public WorldGenerator limestoneGen;
	public WorldGenerator sedimentGen;
	public WorldGenerator hermiteGen;
	public WorldGenerator pyriteGen;
	public WorldGenerator antHillGen;
	public WorldGenerator coralGen;
	public int sedimentPerChunk;
	public int antHillsPerChunk;
	public int coralPerChunk;
	
	public BiomeDecoratorCarboniferous() {
		super();
        DimensionProperties.decoratorProperties(this);
	}
	
	@Override
	protected void genDecorations(BiomeGenBase p_150513_1_) {
		int i;
	    int j;
	    int k;
		for (i = 0; i < this.coralPerChunk; ++i) {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.coralGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }
	    for (i = 0; i < 1; ++i) {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
        	WorldGenSpiderLair burrows = new WorldGenSpiderLair(true);
        	burrows.generate(this.currentWorld, this.randomGenerator, j, 0, k);
        }
		for (i = 0; i < this.antHillsPerChunk; ++i) {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.antHillGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getHeightValue(j, k), k);
        }
		for (i = 0; i < this.sedimentPerChunk; ++i) {
            j = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
            k = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
            this.sedimentGen.generate(this.currentWorld, this.randomGenerator, j, this.currentWorld.getTopSolidOrLiquidBlock(j, k), k);
        }
		super.genDecorations(p_150513_1_);
    }
	
	@Override
	protected void generateOres() {
		MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Pre(currentWorld, randomGenerator, chunk_X, chunk_Z));
	    if (TerrainGen.generateOre(currentWorld, randomGenerator, limestoneGen, chunk_X, chunk_Z, CUSTOM))
	    this.genStandardOre1(20, limestoneGen, 0, 128);
		if (TerrainGen.generateOre(currentWorld, randomGenerator, dirtGen, chunk_X, chunk_Z, DIRT))
	    this.genStandardOre1(15, this.dirtGen, 0, 128);
	    if (TerrainGen.generateOre(currentWorld, randomGenerator, goldGen, chunk_X, chunk_Z, GOLD))
	    this.genStandardOre1(2, this.goldGen, 0, 32);
	    if (TerrainGen.generateOre(currentWorld, randomGenerator, diamondGen, chunk_X, chunk_Z, DIAMOND))
	    this.genStandardOre1(1, this.diamondGen, 0, 18);
	    if (TerrainGen.generateOre(currentWorld, randomGenerator, hermiteGen, chunk_X, chunk_Z, CUSTOM))
		this.genStandardOre1(27, hermiteGen, 30, 60);
	    if (TerrainGen.generateOre(currentWorld, randomGenerator, pyriteGen, chunk_X, chunk_Z, CUSTOM))
		this.genStandardOre1(20, pyriteGen, 1, 52);
	    MinecraftForge.ORE_GEN_BUS.post(new OreGenEvent.Post(currentWorld, randomGenerator, chunk_X, chunk_Z));
    }
}
