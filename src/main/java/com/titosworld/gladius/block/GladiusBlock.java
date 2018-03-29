package com.titosworld.gladius.block;

import com.titosworld.gladius.Gladius;
import com.titosworld.gladius.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class GladiusBlock extends Block {

	public GladiusBlock(final String name, Material materialIn) {
		super(materialIn);
		setBlockName(this, name);
		setCreativeTab(Gladius.creativeTab);
	}
	
	public static void setBlockName(final Block block, final String name) {
		block.setRegistryName(Reference.MODID, name);
		block.setUnlocalizedName(block.getRegistryName().toString());
	}
}
