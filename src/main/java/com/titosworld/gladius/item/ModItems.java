package com.titosworld.gladius.item;

import java.util.HashSet;
import java.util.Set;

import com.titosworld.gladius.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.common.util.EnumHelper;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModItems {
	public static Item BLOOD_DIAMOND = new ItemBloodDiamond();
	public static Item SOULS_BANE = new ItemSoulsBane();
		
	public static class ToolMaterials {
		public static final ToolMaterial BLOOD_DIAMOND_MATERIAL = EnumHelper.addToolMaterial("blood_diamond", 0, ToolMaterial.DIAMOND.getMaxUses(), ToolMaterial.DIAMOND.getEfficiency(), 0.5f, ToolMaterial.GOLD.getEnchantability());
	}
	
	public final static Item[] items = {
			BLOOD_DIAMOND,
			SOULS_BANE
	};
	
	public static final Set<Item> RENDER_ITEMS = new HashSet<>();
	
	public static void init() {
	}
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		for(Item item : items) {
			registry.register(item);
			RENDER_ITEMS.add(item);
		}
	}
}
