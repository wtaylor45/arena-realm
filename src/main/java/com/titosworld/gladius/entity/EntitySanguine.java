package com.titosworld.gladius.entity;

import java.util.Arrays;

import javax.annotation.Nullable;

import com.titosworld.gladius.loot.ModLootTables;
import com.titosworld.gladius.potion.ModPotions;
import com.titosworld.gladius.potion.PotionEffectLifeVamp;
import com.titosworld.gladius.util.Utils;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

/**
 * Sanguine mini-boss. Will spawn in caves and break blocks to reach the player at a speed slightly slower than walking speed.
 * Sanguine can fly up to reach the player, and can inflict {@link PotionEffectLifeVamp} on the player.
 * @author willtaylor
 */
public class EntitySanguine extends EntityMob {
	private final ResourceLocation lootTable = ModLootTables.SANGUINE_LOOT;
	// List of blocks that sanguine cannot break
	protected final Block[] blacklist = {
			Blocks.BEDROCK,
			Blocks.OBSIDIAN,
			Blocks.WATER,
			Blocks.LAVA,
			Blocks.GLOWSTONE
	};
    
	public EntitySanguine(World worldIn) {
		super(worldIn);
		((PathNavigateGround) this.getNavigator()).setCanSwim(true);
	}

	@Override
	public void initEntityAI() {
		this.tasks.addTask(5, new EntityAIAttackMelee(this, 1.1D, true));
		this.tasks.addTask(6, new EntityAIWanderAvoidWater(this, 0.8D));
		this.targetTasks.addTask(1, new EntitySanguine.AIHuntDown(this));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
	}
	
	@Override
	@Nullable
	protected ResourceLocation getLootTable() {
		Utils.getLogger().info("GET LOOT TABLE: "+lootTable.toString());
		return lootTable;
	}
	
	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}
	
	@Override
	public boolean getCanSpawnHere()
    {
		// 1 percent chance that it will spawn this time
		if(this.rand.nextInt(50) != 1) return false;
		
        BlockPos blockpos = new BlockPos(this.posX, this.getEntityBoundingBox().minY, this.posZ);
        
        if (blockpos.getY() > 35)
        {
            return false;
        }
        else
        {
            int currentLightLevel = this.world.getLightFromNeighbors(blockpos);
            int maxLightLevel = 4;

            return currentLightLevel > this.rand.nextInt(maxLightLevel) ? false : super.getCanSpawnHere();
        }
    }

	@Override
	public void onLivingUpdate() {
		if(this.isEntityInsideOpaqueBlock()) {
			//this.world.createExplosion(this, this.posX, this.posY, this.posZ, 5.0F, true);
		}
				
		if (!this.world.isRemote && this.getAttackTarget() != null)
        {
            EntityLivingBase entity = this.getAttackTarget();

            if (entity != null)
            {
                if (this.posY < entity.posY)
                {
                    if (this.motionY < 0.0D)
                    {
                        this.motionY = 0.0D;
                    }

                    this.motionY += (0.5D - this.motionY) * 0.6000000238418579D;
                }

                double d0 = entity.posX - this.posX;
                double d1 = entity.posZ - this.posZ;
                double d3 = d0 * d0 + d1 * d1;

                if (d3 > 9.0D)
                {
                    double d5 = (double)MathHelper.sqrt(d3);
                    this.motionX += (d0 / d5 * 0.5D - this.motionX) * 0.6000000238418579D;
                    this.motionZ += (d1 / d5 * 0.5D - this.motionZ) * 0.6000000238418579D;
                }
                
                if(this.canEntityBeSeen(entity)) {
                	int chance = this.rand.nextInt(500);
                	if(chance == 250) {
						entity.addPotionEffect(new PotionEffectLifeVamp(ModPotions.LIFE_VAMP, 100, this));
                	}
                }
            }
        }

        if (this.motionX * this.motionX + this.motionZ * this.motionZ > 0.05000000074505806D)
        {
            this.rotationYaw = (float)MathHelper.atan2(this.motionZ, this.motionX) * (180F / (float)Math.PI) - 90.0F;
        }

		super.onLivingUpdate();
	}

	@Override
	protected void updateAITasks() {
		super.updateAITasks();
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}

	static class AIHuntDown extends EntityAINearestAttackableTarget<EntityPlayer> {		
		public AIHuntDown(final EntitySanguine sanguineIn) {
			super(sanguineIn, EntityPlayer.class, false);
		}

		@Override
		public void updateTask() {
			if(this.taskOwner.getAttackTarget() != null && this.taskOwner.getNavigator().noPath()) {
				EntityLivingBase target = this.taskOwner.getAttackTarget();
				EnumFacing dir = this.taskOwner.getHorizontalFacing();
				Block[] blacklist = ((EntitySanguine)this.taskOwner).blacklist;
				if(this.taskOwner.collidedVertically && target.posY != this.taskOwner.posY) {
					int offset = target.posY > this.taskOwner.posY ? 1 : -1;
					final BlockPos blockPos = new BlockPos(this.taskOwner.posX, this.taskOwner.posY+offset, this.taskOwner.posZ);
					final Block block = this.taskOwner.getEntityWorld().getBlockState(blockPos).getBlock();
					if(!Arrays.asList(blacklist).contains(block)) 
						this.taskOwner.getEntityWorld().destroyBlock(blockPos, true);
				}
				else if(this.taskOwner.collidedHorizontally) {
					int height = Math.round(this.taskOwner.height+0.4f);
					final BlockPos pos = new BlockPos(this.taskOwner.posX+dir.getFrontOffsetX(), this.taskOwner.posY, this.taskOwner.posZ+dir.getFrontOffsetZ());
					for(int i=0;i<height;i++) {
						final BlockPos newPos = pos.add(0,i,0);
						final Block block = this.taskOwner.getEntityWorld().getBlockState(newPos).getBlock();
						if(!Arrays.asList(blacklist).contains(block)) 
							this.taskOwner.getEntityWorld().destroyBlock(newPos, true);
					}
				}
			}
					
			super.updateTask();
		}
	}
}
