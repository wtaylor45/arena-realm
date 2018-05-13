package com.titosworld.gladius.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionEffect;

public class PotionEffectLifeVamp extends PotionEffect {
    private final PotionLifeVamp potion;
    private final EntityLivingBase attacker;
    /** The amplifier of the potion effect */
    private int amplifier;
    private int duration;

    public PotionEffectLifeVamp(PotionLifeVamp potion, int duration, EntityLivingBase attacker)
    {	
    	this(potion, duration, attacker, 3);
    }
    
    public PotionEffectLifeVamp(PotionLifeVamp potion, int duration, EntityLivingBase attacker, int amplifier) {
    	super(potion, duration);
        this.duration = duration;
        this.potion = potion;
        this.attacker = attacker;
        this.amplifier = amplifier;
    }

    /**
     * Gets whether this potion effect will show ambient particles or not.
     */
    @Override
    public boolean doesShowParticles()
    {
        return true;
    }
    
    @Override
    public boolean onUpdate(EntityLivingBase entityIn)
    {
    	if (this.duration > 0)
        {
            if (this.potion.isReady(this.duration, this.amplifier))
            {
                this.performEffect(entityIn);
            }

            this.duration--;
        }

        return this.duration > 0;
    }
    
    @Override
    public void performEffect(EntityLivingBase entityIn)
    {
        if (this.duration > 0)
        {
            this.potion.performEffect(attacker, entityIn, this.amplifier);
        }
    }
}
