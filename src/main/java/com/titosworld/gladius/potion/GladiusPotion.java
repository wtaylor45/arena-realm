package com.titosworld.gladius.potion;

import com.titosworld.gladius.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GladiusPotion extends Potion {
	
	/**
	 * The icon texture to use in the HUD and inventory GUI.
	 */
	private final ResourceLocation iconTexture;
	
	protected GladiusPotion(final String name, boolean isBadEffectIn, int liquidColorIn) {
		super(isBadEffectIn, liquidColorIn);
		setPotionName(this, name);
		iconTexture = new ResourceLocation(Reference.MODID, "textures/potions/"+name+".png");
	}
	
	/**
	 * Set the registry name of {@code potion} to {@code potionName} and the unlocalised name to the full registry name.
	 *
	 * @param potion     The potion
	 * @param potionName The potion's name
	 */
	public static void setPotionName(final Potion potion, final String potionName) {
		potion.setRegistryName(Reference.MODID, potionName);
		potion.setPotionName("effect." + potion.getRegistryName().toString());
	}
	
	/**
	 * Called to draw the this Potion onto the player's inventory when it's active.
	 * This can be used to e.g. render Potion icons from your own texture.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param effect the active PotionEffect
	 * @param mc     the Minecraft instance, for convenience
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void renderInventoryEffect(final int x, final int y, final PotionEffect effect, final Minecraft mc) {
		if (mc.currentScreen != null) {
			mc.getTextureManager().bindTexture(iconTexture);
			Gui.drawModalRectWithCustomSizedTexture(x + 6, y + 7, 0, 0, 18, 18, 18, 18);
		}
	}
	
	/**
	 * Called to draw the this Potion onto the player's ingame HUD when it's active.
	 * This can be used to e.g. render Potion icons from your own texture.
	 *
	 * @param x      the x coordinate
	 * @param y      the y coordinate
	 * @param effect the active PotionEffect
	 * @param mc     the Minecraft instance, for convenience
	 * @param alpha  the alpha value, blinks when the potion is about to run out
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public void renderHUDEffect(final int x, final int y, final PotionEffect effect, final Minecraft mc, final float alpha) {
		mc.getTextureManager().bindTexture(iconTexture);
		Gui.drawModalRectWithCustomSizedTexture(x + 3, y + 3, 0, 0, 18, 18, 18, 18);
	}
}
