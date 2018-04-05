package assets.gladius.potion;

import com.titosworld.gladius.Reference;
import com.titosworld.gladius.util.Utils;

import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MODID)
public class ModPotions {
	public static PotionLifeVamp LIFE_VAMP = new PotionLifeVamp();
	
	private static final Potion[] potions = {
			LIFE_VAMP
	};
	
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Potion> event) {
		final IForgeRegistry<Potion> registry = event.getRegistry();
		Utils.getLogger().info("TEST POTION");
		registry.registerAll(potions);
	}
}
