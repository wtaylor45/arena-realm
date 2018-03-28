package com.titosworld.gladius;

import com.titosworld.gladius.item.ModItems;
import com.titosworld.gladius.proxy.CommonProxy;
import com.titosworld.gladius.tab.CreativeTabTutorial;
import com.titosworld.gladius.util.Utils;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Gladius {
	
	@SidedProxy(clientSide=Reference.CLIENT_PROXY_CLASS, serverSide=Reference.SERVER_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Mod.Instance(Reference.MODID)
	public static Gladius instance;
	
	public static CreativeTabTutorial creativeTab;
	
	@EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		Utils.getLogger().info(Reference.NAME+" version "+Reference.NAME+" is loading...");
		creativeTab = new CreativeTabTutorial(CreativeTabs.getNextID(), "realm_tab");
		ModItems.init();
		proxy.preInit(event);
	}

	@EventHandler
	public static void init(FMLInitializationEvent event) {
		Utils.getLogger().info(Reference.NAME+" version "+Reference.NAME+" has been loaded successfully!");
		proxy.init(event);
	}

	@EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		Utils.getLogger().info("What now??");
		proxy.postInit(event);
	}
}