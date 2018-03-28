package com.titosworld.gladius.item;

import com.titosworld.gladius.Gladius;
import com.titosworld.gladius.Reference;

import net.minecraft.item.Item;

public class GladiusItem extends Item {
	public GladiusItem(final String name) {
		setItemName(this, name);
		setCreativeTab(Gladius.creativeTab);
	}
	
	public static void setItemName(final Item item, final String itemName) {
		item.setRegistryName(Reference.MODID, itemName);
		item.setUnlocalizedName(item.getRegistryName().toString());
	}
}
