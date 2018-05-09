package com.titosworld.gladius.client.gui;

import com.titosworld.gladius.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiSoulsBaneHUD extends Gui {
	private static final Minecraft minecraft = Minecraft.getMinecraft();
	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/souls_bane_charge.png");
	private final int textureWidth = 90;
	private final int textureHeight = 10;
	
	public void drawHUD(int charge, ScaledResolution resolution) {
		minecraft.getTextureManager().bindTexture(texture);
		int width = Math.round(((float)charge/10)*90);
		int x = resolution.getScaledWidth()/2;
		int y = resolution.getScaledHeight()-textureHeight*5;
		drawModalRectWithCustomSizedTexture(x, y, 0, 0, 90, 5, textureWidth, textureHeight);
		drawModalRectWithCustomSizedTexture(x, y, 0, 5, width, 5, textureWidth, textureHeight);
	}
}
