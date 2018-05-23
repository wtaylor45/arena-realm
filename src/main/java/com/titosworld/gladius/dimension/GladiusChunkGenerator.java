package com.titosworld.gladius.dimension;

import java.util.List;

import com.titosworld.gladius.util.Utils;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;

public class GladiusChunkGenerator implements IChunkGenerator
{
	private final World worldObj;
	
	public GladiusChunkGenerator(final World world) {
		this.worldObj = world;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();
		Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);

        chunk.generateSkylightMap();
		return chunk;
	}

	@Override
	public void populate(int x, int z) {
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z){
		
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}
}