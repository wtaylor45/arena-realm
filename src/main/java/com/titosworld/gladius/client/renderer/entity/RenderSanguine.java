package com.titosworld.gladius.client.renderer.entity;

import com.titosworld.gladius.client.model.ModelSanguine;
import com.titosworld.gladius.entity.EntitySanguine;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSanguine extends RenderLiving<EntitySanguine> {
	private final ResourceLocation texture;
	
	public RenderSanguine(RenderManager renderManagerIn, final ResourceLocation texture) {
		super(renderManagerIn, new ModelSanguine(), 0f);
		this.texture = texture;
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySanguine entity) {
		return this.texture;
	}
	
}
