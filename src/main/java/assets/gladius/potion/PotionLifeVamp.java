package assets.gladius.potion;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;

public class PotionLifeVamp extends GladiusPotion {
	
	protected PotionLifeVamp() {
		super("life_vamp", true, 1);
	}
	
	@Override
	public void performEffect(EntityLivingBase entityLivingBaseIn, int amplifier)
    {
        entityLivingBaseIn.attackEntityFrom(DamageSource.MAGIC, 1.0f);
    }
	
	public PotionEffectLifeVamp createEffect(final EntityLivingBase attacker, int duration){
		return new PotionEffectLifeVamp(this, duration, attacker);
	}
}

