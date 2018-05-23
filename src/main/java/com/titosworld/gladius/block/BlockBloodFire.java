package com.titosworld.gladius.block;

import com.titosworld.gladius.util.Utils;

import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockBloodFire extends BlockFire {
	
	protected BlockBloodFire()
	{	
		super();
        GladiusBlock.setBlockName(this, "blood_fire");
	}
	
	@Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!ModBlocks.GLADIUS_PORTAL.trySpawnPortal(worldIn, pos))
        {
            if (!worldIn.getBlockState(pos.down()).isOpaqueCube() && !this.canNeighborCatchFire(worldIn, pos))
            {
                worldIn.setBlockToAir(pos);
            }
            else
            {
                worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn) + worldIn.rand.nextInt(10));
            }
        }
    }
    
	
    private boolean canNeighborCatchFire(World worldIn, BlockPos pos)
    {
    	for (EnumFacing enumfacing : EnumFacing.values())
        {
            if (this.canCatchFire(worldIn, pos.offset(enumfacing), enumfacing.getOpposite()))
            {
                return true;
            }
        }

        return false;
    }
}
