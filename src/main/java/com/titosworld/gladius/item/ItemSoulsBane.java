package com.titosworld.gladius.item;

import com.titosworld.gladius.Gladius;
import com.titosworld.gladius.potion.ModPotions;
import com.titosworld.gladius.potion.PotionEffectLifeVamp;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;

public class ItemSoulsBane extends ItemSword {
	private final int MAX_CHARGE = 10;

	public ItemSoulsBane() {
		super(ModItems.ToolMaterials.BLOOD_DIAMOND_MATERIAL);
		GladiusItem.setItemName(this, "souls_bane");
		this.setCreativeTab(Gladius.creativeTab);
	}

	@Override
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {	
		boolean isHostile = target instanceof EntityMob;
		NBTTagCompound nbt = stack.getTagCompound();
		if(nbt == null) nbt = stack.serializeNBT();
		int charge = nbt.getInteger("charge");
		if(!isHostile) {
			if(target.getHealth() <= 0 && charge < this.MAX_CHARGE) {
				nbt.setInteger("charge", charge+1);
			}
		}		
		else if(charge > 0) {
			target.attackEntityFrom(DamageSource.GENERIC, (float) charge-0.5f);
			boolean hasEffect = target.getActivePotionEffects().stream().anyMatch(e -> e instanceof PotionEffectLifeVamp);
	    	if(!hasEffect) {
	    		target.addPotionEffect(new PotionEffectLifeVamp(ModPotions.LIFE_VAMP, 200, attacker));
		    	nbt.setInteger("charge", charge-1);
		    	attacker.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0f, 2.0f);
	    	}
		}
        stack.damageItem(1, attacker);
        stack.setTagCompound(nbt);
        return true;
    }

	@Override
	/**
	 * Return whether this item is repairable in an anvil.
	 * 
	 * @param toRepair
	 *            the {@code ItemStack} being repaired
	 * @param repair
	 *            the {@code ItemStack} being used to perform the repair
	 */
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
		return false;
	}
}
