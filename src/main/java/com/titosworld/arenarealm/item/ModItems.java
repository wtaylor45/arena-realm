package com.titosworld.arenarealm.item;

import java.util.HashSet;
import java.util.Set;

import com.titosworld.arenarealm.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModItems {
	public static Item BLOOD_DIAMOND = new BloodDiamond();
	
	public final static Item[] items = {
			BLOOD_DIAMOND
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
