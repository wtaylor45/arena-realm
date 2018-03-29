package com.titosworld.gladius.block;

import java.util.Random;

import com.titosworld.gladius.item.ModItems;
import com.titosworld.gladius.util.Utils;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class BlockBloodDiamond extends GladiusBlock {
	public BlockBloodDiamond() {
		super("blood_diamond_ore", Material.IRON);
		setDefaultState(blockState.getBaseState());
		setHardness(6.0f);
		Utils.getLogger().info(this.getRegistryName());
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
