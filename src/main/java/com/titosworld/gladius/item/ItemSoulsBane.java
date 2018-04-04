package com.titosworld.gladius.item;

import com.titosworld.gladius.Gladius;

import assets.gladius.potion.GladiusEffects;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemSoulsBane extends ItemSword {
	private final int MAX_CHARGE = 10;
	private int currentCharge = 5;

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
        return 1;
    }

	@Override
    /**
     * Current implementations of this method in child classes do not use the entry argument beside ev. They just raise
     * the damage on the stack.
     */
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker)
    {	
		boolean isHostile = target instanceof EntityMob;
		if(!isHostile) {
			if(target.getHealth() <= 0 && this.currentCharge < this.MAX_CHARGE) {
				this.currentCharge = Math.min(this.currentCharge+1,  this.MAX_CHARGE);
				attacker.sendMessage(new TextComponentTranslation("The blood of the innocent fuels your weapon (Charge: "
					+this.currentCharge+"/"+this.MAX_CHARGE+")."));
			}
		}else {
	    	target.addPotionEffect(GladiusEffects.LIFE_VAMP.createEffect(attacker, 100));
	    	this.currentCharge = Math.max(this.currentCharge-1,  0);
		}
        stack.damageItem(1, attacker);
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
