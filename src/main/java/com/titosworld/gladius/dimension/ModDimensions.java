package com.titosworld.gladius.dimension;

import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
	public static final int gladiusDimId = 3;
	public static DimensionType gladiusDimension;
    //public static final DimensionType GLADIUS_DIMENSION = DimensionType.register("GLADIUS", "_gladius", gladiusDimId, GladiusWorldProvider.class, false);

    public static void init() {
        registerDimensions();
    }

    private static void registerDimensions() {
      //DimensionManager.registerDimension(gladiusDimId, GLADIUS_DIMENSION);
    }
}
