package com.titosworld.gladius.block;

import com.google.common.cache.LoadingCache;
import com.titosworld.gladius.dimension.ModDimensions;
import com.titosworld.gladius.dimension.TeleporterGladius;
import com.titosworld.gladius.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class BlockGladiusPortal extends BlockPortal {
	public static String name;
	public static final PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.<EnumFacing.Axis>create("axis",
			EnumFacing.Axis.class, new EnumFacing.Axis[] { EnumFacing.Axis.X, EnumFacing.Axis.Z });
	protected static final AxisAlignedBB X_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.375D, 1.0D, 1.0D, 0.625D);
	protected static final AxisAlignedBB Z_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.0D, 0.625D, 1.0D, 1.0D);
	protected static final AxisAlignedBB Y_AABB = new AxisAlignedBB(0.375D, 0.0D, 0.375D, 0.625D, 1.0D, 0.625D);

	protected BlockGladiusPortal() {

		super();
		this.setDefaultState(this.blockState.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
		this.setTickRandomly(true);
		GladiusBlock.setBlockName(this, "gladius_portal");
	}

	@Override
	public boolean trySpawnPortal(World worldIn, BlockPos pos) {
		BlockGladiusPortal.Size blockportal$size = new BlockGladiusPortal.Size(worldIn, pos, EnumFacing.Axis.X);

		if (blockportal$size.isValid() && blockportal$size.portalBlockCount == 0) {
			blockportal$size.placePortalBlocks();
			return true;
		} else {
			BlockGladiusPortal.Size blockportal$size1 = new BlockGladiusPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

			if (blockportal$size1.isValid() && blockportal$size1.portalBlockCount == 0) {
				blockportal$size1.placePortalBlocks();
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Called when a neighboring block was changed and marks that this state should
	 * perform any checks during a neighbor change. Cases may include when redstone
	 * power is updated, cactus blocks popping off due to a neighboring solid block,
	 * etc.
	 */
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		EnumFacing.Axis enumfacing$axis = (EnumFacing.Axis) state.getValue(AXIS);

		if (enumfacing$axis == EnumFacing.Axis.X) {
			BlockGladiusPortal.Size blockportal$size = new BlockGladiusPortal.Size(worldIn, pos, EnumFacing.Axis.X);

			if (!blockportal$size.isValid()
					|| blockportal$size.portalBlockCount < blockportal$size.width * blockportal$size.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		} else if (enumfacing$axis == EnumFacing.Axis.Z) {
			BlockGladiusPortal.Size blockportal$size1 = new BlockGladiusPortal.Size(worldIn, pos, EnumFacing.Axis.Z);

			if (!blockportal$size1.isValid()
					|| blockportal$size1.portalBlockCount < blockportal$size1.width * blockportal$size1.height) {
				worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
			}
		}
	}

	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
		if (!entityIn.isRiding() && !entityIn.isBeingRidden() && entityIn.isNonBoss()
				&& entityIn.getEntityBoundingBox().intersects(state.getBoundingBox(worldIn, pos).offset(pos))) {
			if (entityIn instanceof EntityPlayerMP) {
				EntityPlayerMP player = (EntityPlayerMP) entityIn;
				int destinationDim = player.dimension == ModDimensions.gladiusDimId ? 0 : ModDimensions.gladiusDimId;
				worldIn.getMinecraftServer().getPlayerList().transferPlayerToDimension(player,
						destinationDim,
						new TeleporterGladius(worldIn.getMinecraftServer().getWorld(0), 0, 0, 100, 0));
			}
		}

	}

	@Override
	public BlockPattern.PatternHelper createPatternHelper(World worldIn, BlockPos p_181089_2_) {
		EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.Z;
		BlockGladiusPortal.Size blockportal$size = new BlockGladiusPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.X);
		LoadingCache<BlockPos, BlockWorldState> loadingcache = BlockPattern.createLoadingCache(worldIn, true);

		if (!blockportal$size.isValid()) {
			enumfacing$axis = EnumFacing.Axis.X;
			blockportal$size = new BlockGladiusPortal.Size(worldIn, p_181089_2_, EnumFacing.Axis.Z);
		}

		if (!blockportal$size.isValid()) {
			return new BlockPattern.PatternHelper(p_181089_2_, EnumFacing.NORTH, EnumFacing.UP, loadingcache, 1, 1, 1);
		} else {
			int[] aint = new int[EnumFacing.AxisDirection.values().length];
			EnumFacing enumfacing = blockportal$size.rightDir.rotateYCCW();
			BlockPos blockpos = blockportal$size.bottomLeft.up(blockportal$size.getHeight() - 1);

			for (EnumFacing.AxisDirection enumfacing$axisdirection : EnumFacing.AxisDirection.values()) {
				BlockPattern.PatternHelper blockpattern$patternhelper = new BlockPattern.PatternHelper(
						enumfacing.getAxisDirection() == enumfacing$axisdirection ? blockpos
								: blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1),
						EnumFacing.getFacingFromAxis(enumfacing$axisdirection, enumfacing$axis), EnumFacing.UP,
						loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);

				for (int i = 0; i < blockportal$size.getWidth(); ++i) {
					for (int j = 0; j < blockportal$size.getHeight(); ++j) {
						BlockWorldState blockworldstate = blockpattern$patternhelper.translateOffset(i, j, 1);

						if (blockworldstate.getBlockState() != null
								&& blockworldstate.getBlockState().getMaterial() != Material.AIR) {
							++aint[enumfacing$axisdirection.ordinal()];
						}
					}
				}
			}

			EnumFacing.AxisDirection enumfacing$axisdirection1 = EnumFacing.AxisDirection.POSITIVE;

			for (EnumFacing.AxisDirection enumfacing$axisdirection2 : EnumFacing.AxisDirection.values()) {
				if (aint[enumfacing$axisdirection2.ordinal()] < aint[enumfacing$axisdirection1.ordinal()]) {
					enumfacing$axisdirection1 = enumfacing$axisdirection2;
				}
			}

			return new BlockPattern.PatternHelper(
					enumfacing.getAxisDirection() == enumfacing$axisdirection1 ? blockpos
							: blockpos.offset(blockportal$size.rightDir, blockportal$size.getWidth() - 1),
					EnumFacing.getFacingFromAxis(enumfacing$axisdirection1, enumfacing$axis), EnumFacing.UP,
					loadingcache, blockportal$size.getWidth(), blockportal$size.getHeight(), 1);
		}
	}

	public static class Size {
		private final World world;
		private final EnumFacing.Axis axis;
		private final EnumFacing rightDir;
		private final EnumFacing leftDir;
		private int portalBlockCount;
		private BlockPos bottomLeft;
		private int height;
		private int width;

		public Size(World worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
			this.world = worldIn;
			this.axis = p_i45694_3_;

			if (p_i45694_3_ == EnumFacing.Axis.X) {
				this.leftDir = EnumFacing.EAST;
				this.rightDir = EnumFacing.WEST;
			} else {
				this.leftDir = EnumFacing.NORTH;
				this.rightDir = EnumFacing.SOUTH;
			}

			for (BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0
					&& this.isEmptyBlock(
							worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
				;
			}

			int i = this.getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;
			Utils.getLogger().info("Distance until left edge: " + i);
			if (i >= 0) {
				this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
				this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
				Utils.getLogger().info("Width of portal: " + width);
				if (this.width < 2 || this.width > 21) {
					this.bottomLeft = null;
					this.width = 0;
				}
			}

			if (this.bottomLeft != null) {
				this.height = this.calculatePortalHeight();
			}
		}

		protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
			int i;

			for (i = 0; i < 22; ++i) {
				BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);

				if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock())
						|| this.world.getBlockState(blockpos.down()).getBlock() != ModBlocks.BLOOD_DIAMOND_CORE) {
					Utils.getLogger()
							.info("Iteration " + i + " found non empty block or non-bdc at " + blockpos.toString());
					break;
				}
			}

			Block block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
			return block == ModBlocks.BLOOD_DIAMOND_CORE ? i : 0;
		}

		public int getHeight() {
			return this.height;
		}

		public int getWidth() {
			return this.width;
		}

		protected int calculatePortalHeight() {
			label24:

			for (this.height = 0; this.height < 21; ++this.height) {
				for (int i = 0; i < this.width; ++i) {
					BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
					Block block = this.world.getBlockState(blockpos).getBlock();

					if (!this.isEmptyBlock(block)) {
						break label24;
					}

					if (block == ModBlocks.GLADIUS_PORTAL) {
						++this.portalBlockCount;
					}

					if (i == 0) {
						block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();

						if (block != ModBlocks.BLOOD_DIAMOND_CORE) {
							break label24;
						}
					} else if (i == this.width - 1) {
						block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();

						if (block != ModBlocks.BLOOD_DIAMOND_CORE) {
							break label24;
						}
					}
				}
			}

			for (int j = 0; j < this.width; ++j) {
				if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, j).up(this.height))
						.getBlock() != ModBlocks.BLOOD_DIAMOND_CORE) {
					this.height = 0;
					break;
				}
			}

			if (this.height <= 21 && this.height >= 3) {
				return this.height;
			} else {
				this.bottomLeft = null;
				this.width = 0;
				this.height = 0;
				return 0;
			}
		}

		protected boolean isEmptyBlock(Block blockIn) {
			return blockIn.getDefaultState().getMaterial() == Material.AIR || blockIn == ModBlocks.BLOOD_FIRE
					|| blockIn == ModBlocks.GLADIUS_PORTAL;
		}

		public boolean isValid() {
			Utils.getLogger().info(this.bottomLeft);
			return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3
					&& this.height <= 21;
		}

		public void placePortalBlocks() {
			for (int i = 0; i < this.width; ++i) {
				BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

				for (int j = 0; j < this.height; ++j) {
					this.world.setBlockState(blockpos.up(j),
							ModBlocks.GLADIUS_PORTAL.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
				}
			}
		}
	}
}
