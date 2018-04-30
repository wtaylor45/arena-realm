package com.titosworld.gladius.loot;

import java.util.HashSet;
import java.util.Set;

import com.titosworld.gladius.Reference;
import com.titosworld.gladius.util.Utils;

import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTableList;

public class ModLootTables {
	public static final ResourceLocation SANGUINE_LOOT = RegistrationHandler.create("sanguine_loot");


	/**
	 * Register this mod's {@link LootTable}s.
	 */
	public static void registerLootTables() {
		RegistrationHandler.LOOT_TABLES.forEach(LootTableList::register);
	}

	public static class RegistrationHandler {

		/**
		 * Stores the IDs of this mod's {@link LootTable}s.
		 */
		private static final Set<ResourceLocation> LOOT_TABLES = new HashSet<>();

		/**
		 * Create a {@link LootTable} ID.
		 *
		 * @param id The ID of the LootTable without the testmod3: prefix
		 * @return The ID of the LootTable
		 */
		protected static ResourceLocation create(final String id) {
			final ResourceLocation lootTable = new ResourceLocation(Reference.MODID, id);
			RegistrationHandler.LOOT_TABLES.add(lootTable);
			Utils.getLogger().info("ADDING LOOT TABLE: "+lootTable.toString());
			return lootTable;
		}
	}
}