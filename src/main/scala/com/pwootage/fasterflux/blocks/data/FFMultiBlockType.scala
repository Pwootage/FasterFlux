package com.pwootage.fasterflux.blocks.data

import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryOutput
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryInput

object FFMultiBlockType {
  private var types = Map[Int, Type]()
  
  val BATTERY_CONTROLLER = new Type(0, BlockIcons.BATTERY_CONTROLLER, (world) => new TileEntityBatteryController())
  val BATTERY_ANODE = new Type(1, BlockIcons.BATTERY_ANODE, null)
  val BATTERY_CASE = new Type(2, BlockIcons.BATTERY_CASE, null)
  val BATTERY_OUTPUT = new Type(3, BlockIcons.BATTERY_OUT, (world) => new TileEntityBatteryOutput())
  val BATTERY_INPUT = new Type(4, BlockIcons.BATTERY_IN, (world) => new TileEntityBatteryInput())
  val UNKNOWN = new Type(17, BlockIcons.UNKNOWN, null)

  class Type(val meta: Int, val icons: BlockIcons.BlockIcons, val getTE: (World) => TileEntity) {
    types += (meta -> this)
  }

  def getTypeFromMeta(meta: Int): Type = {
    types getOrElse (meta, UNKNOWN)
  }
  
  def validTypes(): Map[Int, Type] = types - UNKNOWN.meta
}