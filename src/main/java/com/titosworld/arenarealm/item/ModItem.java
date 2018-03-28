package com.titosworld.arenarealm.item;

import com.titosworld.arenarealm.ArenaRealm;
import com.titosworld.arenarealm.Reference;

import net.minecraft.item.Item;

public class ModItem extends Item {
	public ModItem(final String name) {
		setItemName(this, name);
		setCreativeTab(ArenaRealm.creativeTab);
	}
	
	public static void setItemName(final Item item, final String itemName) {
		item.setRegistryName(Reference.MODID, itemName);
		item.setUnlocalizedName(item.getRegistryName().toString());
	}
}
