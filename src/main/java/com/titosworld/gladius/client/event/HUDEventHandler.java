package com.titosworld.gladius.client.event;

import com.titosworld.gladius.Reference;
import com.titosworld.gladius.client.gui.GuiSoulsBaneHUD;
import com.titosworld.gladius.item.ModItems;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Reference.MODID)
public class HUDEventHandler {
	private static final Minecraft minecraft = Minecraft.getMinecraft();
	private static final GuiSoulsBaneHUD soulsBaneHUD = new GuiSoulsBaneHUD();

	@SubscribeEvent
	public static void renderSoulsBaneHUD(final RenderGameOverlayEvent.Post event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) return;

		final EntityPlayer player = minecraft.player;
		if (player.getHeldItemMainhand().getItem() != ModItems.SOULS_BANE && player.getHeldItemOffhand().getItem() != ModItems.SOULS_BANE)
			return;
		final NBTTagCompound nbt = player.getHeldItemMainhand().getTagCompound();
		int charge = nbt == null ? 0 : nbt.getInteger("charge");
		soulsBaneHUD.drawHUD(charge, event.getResolution());
	}
}