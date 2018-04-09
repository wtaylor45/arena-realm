package com.titosworld.gladius.world;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModWorldGenerators {
	public static void registerWorldGen() {
		GameRegistry.registerWorldGenerator(new WorldGenOre(), 0);
	}
}
