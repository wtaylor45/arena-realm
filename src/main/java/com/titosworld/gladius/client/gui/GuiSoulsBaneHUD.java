package com.titosworld.gladius.client.gui;

import com.titosworld.gladius.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;

public class GuiSoulsBaneHUD extends Gui {
	private static final Minecraft minecraft = Minecraft.getMinecraft();
	private static final ResourceLocation texture = new ResourceLocation(Reference.MODID, "textures/gui/souls_bane_charge.png");
	
	public void drawHUD(int charge, ScaledResolution resolution) {
		minecraft.getTextureManager().bindTexture(texture);
		int width = Math.round(((float)charge/10)*90);
		int x = resolution.getScaledWidth()/2+6;
		int y = resolution.getScaledHeight()-47;
		drawModalRectWithCustomSizedTexture(x, y, 0, 0, 90, 5, 90, 10);
		drawModalRectWithCustomSizedTexture(x, y, 0, 5, width, 5, 90, 10);
	}
}
