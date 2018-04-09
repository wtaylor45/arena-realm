package com.titosworld.gladius.world;

import java.util.Random;

import com.google.common.base.Predicate;
import com.titosworld.gladius.block.ModBlocks;
import com.titosworld.gladius.util.Utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockMatcher;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {
	private final WorldGenMinable oreGenOverworld;
	private final int OFFSET = 8;
	
	public WorldGenOre() {
		oreGenOverworld = new WorldGenMinable(ModBlocks.BLOOD_DIAMOND.getDefaultState(), 20, 
				BlockMatcher.forBlock(Blocks.STONE));
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimension()) {
			case 0:
				genSurface(world, random, chunkX, chunkZ);
				break;
			default:
				break;
		}
	}
	
	private void genSurface(World world, Random random, int chunkX, int chunkZ) {
		addOreSpawn(world, random, chunkX, chunkZ, 20, 10, 108);
	}
	
	
	private void addOreSpawn( World world, Random random, int chunkX, int chunkZ,
			int chance, int minY, int maxY) {
		BlockPos chunkPos = new BlockPos(16*chunkX, 0, 16*chunkZ);
		for(int i=0;i<chance;i++) {
			int posX = random.nextInt(16);
			int posY = minY + random.nextInt(maxY-minY);
			int posZ = random.nextInt(16);
			oreGenOverworld.generate(world, random, chunkPos.add(posX, posY, posZ));
		}
	}
}
