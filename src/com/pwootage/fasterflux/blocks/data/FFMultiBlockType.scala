package com.pwootage.fasterflux.blocks.data

object FFMultiBlockType {
  private var types = Map[Int, Type]()
  
  val BATTERY_CONTROLLER = new Type(0, BlockIcons.BATTERY_CONTROLLER, true)
  val BATTERY_ANODE = new Type(1, BlockIcons.BATTERY_ANODE, false)
  val BATTERY_CASE = new Type(2, BlockIcons.BATTERY_CASE, false)
  val UNKNOWN = new Type(17, BlockIcons.UNKNOWN, false)

  class Type(val meta: Int, val icons: BlockIcons.BlockIcons, val hasTE: Boolean) {
    types += (meta -> this)
  }

  def getTypeFromMeta(meta: Int): Type = {
    types getOrElse (meta, UNKNOWN)
  }
}