package com.titosworld.gladius.dimension;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderGladius extends WorldProvider {

	@Override
	public DimensionType getDimensionType() {
		return ModDimensions.GLADIUS_DIMENSION;
	}
	
	@Override
	public IChunkGenerator createChunkGenerator()
	{
		return new GladiusChunkGenerator(this.world);
	}
	
	@Override
	public Biome getBiomeForCoords(BlockPos pos)
	{
		return Biomes.VOID;
	}
	
	@Override
	public boolean canCoordinateBeSpawn(int x, int z) {
		return true;
	}
	
	@Override
	public boolean canRespawnHere() {
		return true;
	}
	
	@Override
	public boolean isSurfaceWorld() {
		return true;
	}
	
	@Override
	public int getDimension() {
		return ModDimensions.gladiusDimId;
	}
}
