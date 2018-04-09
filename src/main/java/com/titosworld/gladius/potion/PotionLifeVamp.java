package com.titosworld.gladius.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionLifeVamp extends GladiusPotion {
	
	protected PotionLifeVamp() {
		super("life_vamp", true, 1);
	}
	
	public void performEffect(EntityLivingBase attacker, EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0f);
        attacker.heal(1.0f);
    }
	
	public PotionEffectLifeVamp createEffect(final EntityLivingBase attacker, int duration){
		return new PotionEffectLifeVamp(this, duration, attacker);
	}
	
	@Override
	public boolean isReady(int duration, int amplifier) {
		int j = 20 >> amplifier;
		if(j>0) {
			return duration % 25 == 0;
		} else {
			return true;
		}
	}
}

