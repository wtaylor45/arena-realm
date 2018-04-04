package assets.gladius.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectLifeVamp extends PotionEffect {
	private EntityLivingBase attacker;
	
	public PotionEffectLifeVamp(final Potion potion, final int duration, final EntityLivingBase attacker) {
		super(potion,duration);
	}
//	
//	@Override
//	public boolean onUpdate(EntityLivingBase entityIn)
//    {
//        if (this.duration > 0)
//        {
//            if (this.potion.isReady(this.duration, this.amplifier))
//            {
//                this.performEffect(entityIn);
//            }
//
//            this.deincrementDuration();
//        }
//
//        return this.duration > 0;
//    }
	
}
