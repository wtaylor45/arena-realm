package com.titosworld.gladius.tileentity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.TextComponentTranslation;

public class TileEntityGladiusPortal extends TileEntity {
	
	private static final String S_PLAYER_X = "PlayerPrevX";
	private static final String S_PLAYER_Y = "PlayerPrevY";
	private static final String S_PLAYER_Z = "PlayerPrevZ";
	private static final String S_PLAYER_X2 = "PlayerPrevX2";
	private static final String S_PLAYER_Y2 = "PlayerPrevY2";
	private static final String S_PLAYER_Z2 = "PlayerPrevZ2";
	private static final String dme = "DimEnter";
	private static int offsetX;
	private static int offsetZ;
	public static double prevX;
	public static double prevY;
	public static double prevZ;
	public static double prevX2;
	public static double prevY2;
	public static double prevZ2;
	public static int dme2;

	public TileEntityGladiusPortal() {
		super();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.prevX = nbt.getDouble(S_PLAYER_X);
		this.prevY = nbt.getDouble(S_PLAYER_Y);
		this.prevZ = nbt.getDouble(S_PLAYER_Z);
		this.prevX2 = nbt.getDouble(S_PLAYER_X2);
		this.prevY2 = nbt.getDouble(S_PLAYER_Y2);
		this.prevZ2 = nbt.getDouble(S_PLAYER_Z2);
		this.dme2 = nbt.getInteger(dme);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setDouble(S_PLAYER_X, prevX);
		nbt.setDouble(S_PLAYER_Y, prevY);
		nbt.setDouble(S_PLAYER_Z, prevZ);
		nbt.setDouble(S_PLAYER_X2, prevX2);
		nbt.setDouble(S_PLAYER_Y2, prevY2);
		nbt.setDouble(S_PLAYER_Z2, prevZ2);
		nbt.setInteger(dme, 2);
		return nbt;
	}

	public static void setOverworldXYZ(double posX, double posY, double posZ) {
		prevX = posX;
		prevY = posY;
		prevZ = posZ;
	}

	public static void setTestXYZ(double posX2, double posY2, double posZ2) {
		prevX2 = posX2;
		prevY2 = posY2;
		prevZ2 = posZ2;
	}

	public static void setDme22() {

		dme2 = 2;

	}

	public static void setDme21() {

		dme2 = 0;

	}

	public boolean onPlayerActivate(EntityPlayer player) {

		return true;
	}

	public static void tele(Entity player) {
		player.sendMessage(new TextComponentTranslation("Test"));

//		if ((player.getRidingEntity() == null) && ((player instanceof EntityPlayer))) {
//
//			EntityPlayer player1 = (EntityPlayer) player;
//			MinecraftServer mcServer = player1.getServer();
//
//			if (player1.timeUntilPortal > 0) {
//
//				player1.timeUntilPortal = 10;
//
//			} else if (player1.dimension != ModDimensions.gladiusDimId) {
//
//				player1.timeUntilPortal = 10;
//
//				if (prevX2 == 0.0 && prevY2 == 0.0 && prevZ2 == 0.0) {
//					player1.timeUntilPortal = 10;
//					setDme21();
//					setOverworldXYZ(player1.posX, player1.posY, player1.posZ);
//					mcServer.getPlayerList().transferPlayerToDimension(player1, ModDimensions.gladiusDimId,
//							new TeleporterGladius(mcServer.getWorld(ModDimensions.gladiusDimId), dme2, 0, 0, 0));
//					setTestXYZ(player1.posX, player1.posY, player1.posZ);
//
//				} else if (prevX2 != 0.0 && prevY2 != 0.0 && prevZ2 != 0.0) {
//					player1.timeUntilPortal = 10;
//					setDme22();
//					setOverworldXYZ(player1.posX, player1.posY, player1.posZ);
//					mcServer.getPlayerList().transferPlayerToDimension(player1, ModDimensions.gladiusDimId,
//							new TeleporterGladius(mcServer.getWorld(ModDimensions.gladiusDimId), dme2, prevX2, prevY2,
//									prevZ2));
//
//				}
//
//			} else if (player1.dimension == ModDimensions.gladiusDimId) {
//
//				/*player1.timeUntilPortal = 10;
//				setDme22();
//				setTestXYZ(player1.posX, player1.posY, player1.posZ);
//				mcServer.getPlayerList().transferPlayerToDimension(player1, 0,
//						new TeleporterGladius(mcServer.getWorld(0), dme2, prevX, prevY, prevZ));*/
//
//			}
//
//		}
	}
}
