package com.pwootage.fasterflux.gui

import java.util.Random
import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.ResourceLocation
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import GuiBatteryController._

object GuiBatteryController {

  private var TEX_WIDTH: Int = 248

  private var TEX_HEIGHT: Int = 180
}

class GuiBatteryController(te: TileEntityBatteryController) extends GuiScreen {

  private var batt: TileEntityBatteryController = te

  override def drawScreen(par1: Int, par2: Int, par3: Float) {
    this.drawDefaultBackground()
    super.drawScreen(par1, par2, par3)
    mc.renderEngine.bindTexture(new ResourceLocation("fasterflux", "textures/gui/batterycontroller.png"))
    val x = (width - TEX_WIDTH) / 2
    val y = (height - TEX_HEIGHT) / 2
    drawTexturedModalRect(x, y, 0, 0, TEX_WIDTH, TEX_HEIGHT)
    mc.fontRenderer.drawString("Battery Controller", x + 10, y + 8, 0x404040, false)
    mc.fontRenderer.drawString("Total liquid: " + batt.totalLiquid, x + 14, y + 26, 0xA0A0A0, false)
    mc.fontRenderer.drawString("Total power: " + batt.totalEnergy, x + 14, y + 38, 0xA0A0A0, false)
  }
}