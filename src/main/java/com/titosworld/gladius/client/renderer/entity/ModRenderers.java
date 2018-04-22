package com.titosworld.gladius.client.renderer.entity;

import com.titosworld.gladius.Reference;
import com.titosworld.gladius.entity.EntitySanguine;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ModRenderers {
	public static void register() {
		RenderingRegistry.registerEntityRenderingHandler(EntitySanguine.class, 
				renderManager -> new RenderSanguine(renderManager, new ResourceLocation(Reference.MODID, "textures/entity/sanguine.png")));
	}
}
