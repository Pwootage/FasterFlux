package com.pwootage.fasterflux;

import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController;
import com.pwootage.fasterflux.gui.GuiBatteryController;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class CommonProxy implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		System.out.println("What's this? DId this work? Idk even ");
		//Because I'm too cool for gui-ids and instead infer what to open based on what I'm clicking.
		//At least for now?
		TileEntity te = world.getBlockTileEntity(x, y, z);
		if (te != null && te instanceof TileEntityBatteryController) {
			return new GuiBatteryController((TileEntityBatteryController) te);
		}
		return null;
	}

}
