package com.titosworld.gladius.item;

import com.titosworld.gladius.util.Utils;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MultiPartEntityPart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;

public class ItemBloodDiamond extends GladiusItem {
	public ItemBloodDiamond() {
		super("blood_diamond");
		Utils.getLogger().info("New Blood Diamond made: "+this.getRegistryName());
	}
	
	@Override
	public boolean onLeftClickEntity(final ItemStack stack, final EntityPlayer player, final Entity entity) {
		// Check we are on the server
		if(!player.world.isRemote) {
			final Entity target;
			// Apparently there can be entities with multiple parts O_O
			if(entity instanceof MultiPartEntityPart) {
				target = (Entity) ((MultiPartEntityPart) entity).parent;
			}else {
				target = entity;
			}
			
			target.onKillCommand();
			stack.setCount(stack.getCount()-1);
			player.sendMessage(new TextComponentTranslation("You have successfully summoned the power of the blood diamond."));
		}
		return false;
	}
}
