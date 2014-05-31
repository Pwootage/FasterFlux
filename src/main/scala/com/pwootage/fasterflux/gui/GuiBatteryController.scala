package com.pwootage.fasterflux.gui

import java.util.Random
import net.minecraft.client.gui.GuiScreen
import net.minecraft.util.ResourceLocation
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import GuiBatteryController._
import Reika.DragonAPI.Libraries.World.ReikaBlockHelper
import Reika.DragonAPI.Instantiable.Data.StructuredBlockArray

object GuiBatteryController {
  private val TEX_WIDTH: Int = 248
  private val TEX_HEIGHT: Int = 180
}

class GuiBatteryController(batt: TileEntityBatteryController) extends GuiScreen {
  override def drawScreen(par1: Int, par2: Int, par3: Float) {
    drawDefaultBackground
    super.drawScreen(par1, par2, par3)
    mc.renderEngine.bindTexture(new ResourceLocation("fasterflux", "textures/gui/batterycontroller.png"))
    val x = (width - TEX_WIDTH) / 2
    val y = (height - TEX_HEIGHT) / 2
    drawTexturedModalRect(x, y, 0, 0, TEX_WIDTH, TEX_HEIGHT)
    mc.fontRenderer.drawString("Battery Controller", x + 10, y + 8, 0x404040, false)
    def row(r: Int):Int = 26 + 12 * r
    //mc.fontRenderer.drawString("Total liquid: " + batt.totalLiquid, x + 14, y + 26, 0xA0A0A0, false)
    mc.fontRenderer.drawString("Valid Structure: " + batt.validMB, x + 14, y + row(0), 0xA0A0A0, false)
    val rawEnergy = batt.totalEnergy.formatted("%,d")
    val energyAsDecimal = BigDecimal(batt.totalEnergy)
    val kRF = (energyAsDecimal/BigDecimal(1000))
    val mRF = (energyAsDecimal/BigDecimal(1000000))
    val gRF = (energyAsDecimal/BigDecimal(1000000000))
    val tRF = (energyAsDecimal/BigDecimal(1000000000000L))
    val pRF = (energyAsDecimal/BigDecimal(1000000000000000L))
    mc.fontRenderer.drawString(" RF: " + rawEnergy, x + 14, y + row(1), 0xA0A0A0, false)
    mc.fontRenderer.drawString("Loss (RF/t): " + batt.calcLoss, x + 14, y + row(3), 0xA0A0A0, false)
    if (pRF > 0.75) {
      mc.fontRenderer.drawString("pRF: " + pRF.formatted("%,.2f"), x + 14, y + row(2), 0xA0A0A0, false)
    } else if (tRF > 0.75) {
      mc.fontRenderer.drawString("tRF: " + tRF.formatted("%,.2f"), x + 14, y + row(2), 0xA0A0A0, false)
    } else if (gRF > 0.75) {
    	mc.fontRenderer.drawString("gRF: " + gRF.formatted("%,.2f"), x + 14, y + row(2), 0xA0A0A0, false)
    } else if (mRF > 0.75) {
    	mc.fontRenderer.drawString("mRF: " + mRF.formatted("%,.2f"), x + 14, y + row(2), 0xA0A0A0, false)
    } else if (kRF > 0.75) {
    	mc.fontRenderer.drawString("kRF: " + kRF.formatted("%,.2f"), x + 14, y + row(2), 0xA0A0A0, false)
    }
  }
}