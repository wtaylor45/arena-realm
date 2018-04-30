package com.titosworld.gladius.block;

import java.util.Random;

import com.titosworld.gladius.item.ModItems;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockBloodDiamondCore extends GladiusBlock {

	public BlockBloodDiamondCore() {
		super("blood_diamond_core", Material.IRON);
		this.setLightLevel(4.0f/16.0f);
	}

	@Override
	/**
     * Get the Item that this Block should drop when harvested.
     */
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return ModItems.BLOOD_DIAMOND;
    }
}

