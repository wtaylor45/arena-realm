package com.titosworld.gladius.tab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class CreativeTabTutorial extends CreativeTabs {

	public CreativeTabTutorial(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(Items.DIAMOND);
	}

}
