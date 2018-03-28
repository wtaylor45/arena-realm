package com.titosworld.gladius.client.model;

import java.util.HashSet;
import java.util.Set;

import com.titosworld.gladius.Reference;
import com.titosworld.gladius.item.ModItems;

import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Reference.MODID, value = Side.CLIENT)
public class ModModelManager {
	public static final ModModelManager INSTANCE = new ModModelManager();
	private final Set<Item> itemsRegistered = new HashSet<>();
	
	private ModModelManager() {
	}
	
	@SubscribeEvent
	public static void registerAllModels(final ModelRegistryEvent event) {
		INSTANCE.registerItemModels();
	}
	
	private void registerItemModels() {
		ModItems.RENDER_ITEMS.stream().filter(item -> !itemsRegistered.contains(item)).forEach(this::registerItemModel);
	}
	
	private void registerItemModel(final Item item) {
		registerItemModel(item, item.getRegistryName().toString());
	}
	
	private void registerItemModel(final Item item, String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
		registerItemModel(item, fullModelLocation);
	}
	
	private void registerItemModel(final Item item, final ModelResourceLocation fullModelLocation) {
		ModelBakery.registerItemVariants(item, fullModelLocation);
		ModelLoader.setCustomMeshDefinition(item, MeshDefinitionFix.create(stack -> fullModelLocation));
	}
}
