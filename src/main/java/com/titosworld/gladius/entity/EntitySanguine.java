package com.titosworld.gladius.entity;

import com.titosworld.gladius.potion.ModPotions;
import com.titosworld.gladius.potion.PotionEffectLifeVamp;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.BossInfo;
import net.minecraft.world.BossInfoServer;
import net.minecraft.world.World;

public class EntitySanguine extends EntityMob {
	
    private final BossInfoServer bossInfo = (BossInfoServer)(new BossInfoServer(this.getDisplayName(), BossInfo.Color.PURPLE, BossInfo.Overlay.PROGRESS)).setDarkenSky(true);
	
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
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(100.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
	}
	
	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}
	
	public void addTrackingPlayer(EntityPlayerMP player)
    {
        super.addTrackingPlayer(player);
        this.bossInfo.addPlayer(player);
    }
	
	/**
     * Removes the given player from the list of players tracking this entity. See {@link Entity#addTrackingPlayer} for
     * more information on tracking.
     */
    public void removeTrackingPlayer(EntityPlayerMP player)
    {
        super.removeTrackingPlayer(player);
        this.bossInfo.removePlayer(player);
    }

	@Override
	public void onLivingUpdate() {
		if(this.isEntityInsideOpaqueBlock()) {
			this.world.createExplosion(this, this.posX, this.posY, this.posZ, 5.0F, true);
		}
		
		if(this.rand.nextInt(100) == 1) this.world.playSound(this.posX, this.posY, this.posZ, SoundEvents.ENTITY_POLAR_BEAR_WARNING, this.getSoundCategory(), 5.0f, 0.8F + this.rand.nextFloat() * 0.3F, false);
		
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
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
		super.updateAITasks();
	}

	@Override
	public void fall(float distance, float damageMultiplier) {
	}
	
	@Override
	public boolean getCanSpawnHere() {
		return true;
	}

	static class AIHuntDown extends EntityAINearestAttackableTarget<EntityPlayer> {
		private int cooldown = 100;
		
		public AIHuntDown(final EntitySanguine sanguineIn) {
			super(sanguineIn, EntityPlayer.class, false);
		}

		@Override
		public void updateTask() {
			if(this.taskOwner.getAttackTarget() != null && this.taskOwner.getNavigator().noPath()) {
				EntityLivingBase target = this.taskOwner.getAttackTarget();
				EnumFacing dir = this.taskOwner.getHorizontalFacing();
				if(this.taskOwner.collidedVertically && target.posY != this.taskOwner.posY) {
					int offset = target.posY > this.taskOwner.posY ? 1 : -1;
					final BlockPos blockPos = new BlockPos(this.taskOwner.posX, this.taskOwner.posY+offset, this.taskOwner.posZ);
					this.taskOwner.getEntityWorld().destroyBlock(blockPos, true);
				}
				else if(this.taskOwner.collidedHorizontally) {
					int height = Math.round(this.taskOwner.height+0.4f);
					final BlockPos pos = new BlockPos(this.taskOwner.posX+dir.getFrontOffsetX(), this.taskOwner.posY, this.taskOwner.posZ+dir.getFrontOffsetZ());
					for(int i=0;i<height;i++) {
						this.taskOwner.getEntityWorld().destroyBlock(pos.add(0,i,0), true);
					}
				}
				else {
					if(!this.taskOwner.canEntityBeSeen(target) && this.cooldown <= 0) {
						final World world = this.taskOwner.getEntityWorld();
						world.createExplosion(this.taskOwner, this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ, 3.0f, true);
						this.cooldown = 100;
					}
				}
			}
			
			--this.cooldown;
			
			super.updateTask();
		}
	}
}
