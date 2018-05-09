package com.titosworld.gladius.item;

import com.titosworld.gladius.Gladius;
import com.titosworld.gladius.potion.ModPotions;
import com.titosworld.gladius.potion.PotionEffectLifeVamp;
import com.titosworld.gladius.util.Utils;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemSoulsBane extends ItemSword {
	private final int MAX_CHARGE = 10;

	public ItemSoulsBane() {
		super(Item.ToolMaterial.DIAMOND);
		GladiusItem.setItemName(this, "souls_bane");
		this.setCreativeTab(Gladius.creativeTab);
	}
	
	@Override
	/**
     * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
     */
    public float getAttackDamage()
    {
		/*
		 * The sword itself doesn't do much damage at all. The benefit comes from the effect.
		 */
        return 0.5f;
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
				attacker.sendMessage(new TextComponentTranslation(charge+""));
			}
		}
		else if(charge > 0) {
	    	target.addPotionEffect(new PotionEffectLifeVamp(ModPotions.LIFE_VAMP, 100, attacker));
	    	nbt.setInteger("charge", charge-1);
		}
        stack.damageItem(1, attacker);
        stack.setTagCompound(nbt);
        return true;
    }
	
	@Override
    /**
     * Return whether this item is repairable in an anvil.
     *  
     * @param toRepair the {@code ItemStack} being repaired
     * @param repair the {@code ItemStack} being used to perform the repair
     */
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        return false;
    }
}
