package com.pwootage.fasterflux

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import cpw.mods.fml.common.network.IGuiHandler
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import com.pwootage.fasterflux.gui.GuiBatteryController

class CommonProxy extends IGuiHandler {

  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    null
  }

  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): AnyRef = {
    println("What's this? DId this work? Idk even ")
    val te = world.getBlockTileEntity(x, y, z)
    if (te != null && te.isInstanceOf[TileEntityBatteryController]) {
      return new GuiBatteryController(te.asInstanceOf[TileEntityBatteryController])
    }
    null
  }
}