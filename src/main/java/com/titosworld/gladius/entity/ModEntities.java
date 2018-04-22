package com.titosworld.gladius.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import com.titosworld.gladius.Reference;

@ObjectHolder(Reference.MODID)
public class ModEntities {
	private static int entityId = 0;
	
	public static EntitySanguine SANGUINE = null;

	@Mod.EventBusSubscriber(modid = Reference.MODID)
	public static class RegistrationHandler {
		@SubscribeEvent
		public static void registerEntities(final RegistryEvent.Register<EntityEntry> event) {
			final EntityEntry[] entries = {
					createBuilder("sanguine")
						.entity(EntitySanguine.class)
						.tracker(64, 10, true)
						.egg(0xad0000, 0x0)
						.build()
			};
			
			event.getRegistry().registerAll(entries);
			addSpawns();
		}
	}
	
	/**
	 * Create an {@link EntityEntryBuilder} with the specified unlocalised/registry name and an automatically-assigned network ID.
	 *
	 * @param name The name
	 * @param <E>  The entity type
	 * @return The builder
	 */
	private static <E extends Entity> EntityEntryBuilder<E> createBuilder(final String name) {
		final EntityEntryBuilder<E> builder = EntityEntryBuilder.create();
		final ResourceLocation registryName = new ResourceLocation(Reference.MODID, name);
		return builder.id(registryName, entityId++).name(registryName.toString());
	}
	
	private static void addSpawns() {
		EntityRegistry.addSpawn(EntitySanguine.class, 100, 0, 1, EnumCreatureType.MONSTER);
	}
	
	// Could be useful for biome specific mobs later...
	/*private static Biome[] getBiomes(final BiomeDictionary.Type type) {
		return BiomeDictionary.getBiomes(type).toArray(new Biome[0]);
	}*/
}
