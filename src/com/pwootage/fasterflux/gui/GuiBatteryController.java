package com.pwootage.fasterflux.gui;

import java.util.Random;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController;

public class GuiBatteryController extends GuiScreen {
	private static int TEX_WIDTH = 248;
	private static int TEX_HEIGHT = 180;
	private TileEntityBatteryController batt;

	public GuiBatteryController(TileEntityBatteryController te) {
		this.batt = te;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
		mc.renderEngine.bindTexture(new ResourceLocation("fasterflux",
				"textures/gui/batterycontroller.png"));
		int time = (int) (mc.getSystemTime() % 65535);
		int x = (width - TEX_WIDTH) / 2;
		int y = (height - TEX_HEIGHT) / 2;
		drawTexturedModalRect(x, y, 0, 0, TEX_WIDTH, TEX_HEIGHT);
		
		mc.fontRenderer.drawString("Battery Controller", x + 10, y + 8, 0x404040, false);
		
		mc.fontRenderer.drawString("Total liquid: " + batt.getTotalLiquid(), x + 14, y + 26, 0xA0A0A0, false);
		mc.fontRenderer.drawString("Total power: " + batt.getTotalEnergy(), x + 14, y + 38, 0xA0A0A0, false);
		
	}
}
