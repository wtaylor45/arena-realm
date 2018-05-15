package com.titosworld.gladius.block;

import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.titosworld.gladius.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModBlocks {
	public static final Set<ItemBlock> RENDER_BLOCKS = new HashSet<ItemBlock>();
	
	public static final BlockBloodDiamond BLOOD_DIAMOND = new BlockBloodDiamond(); 
	public static final BlockBloodDiamondCore BLOOD_DIAMOND_CORE = new BlockBloodDiamondCore();
	public static final BlockGladiusPortal GLADIUS_PORTAL = new BlockGladiusPortal();
	public static final BlockBloodFire BLOOD_FIRE = new BlockBloodFire();
	
	private static final Block blocks[] = {
			BLOOD_FIRE,
			BLOOD_DIAMOND_CORE,
			GLADIUS_PORTAL
	};
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		final IForgeRegistry<Block> registry = event.getRegistry();
		
		registry.registerAll(blocks);
	}
	
	@SubscribeEvent
	public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		final ItemBlock items[] = {
				//new ItemBlock(BLOOD_DIAMOND)
				new ItemBlock(BLOOD_DIAMOND_CORE)
		};
		
		for(final ItemBlock item : items) {
			final Block block = item.getBlock();
			final ResourceLocation registryName = Preconditions.checkNotNull(block.getRegistryName(), "Block %s has null registry name", block);
			registry.register(item.setRegistryName(registryName));
			RENDER_BLOCKS.add(item);
		}
	}
}
