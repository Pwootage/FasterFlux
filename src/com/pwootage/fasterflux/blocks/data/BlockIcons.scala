package com.pwootage.fasterflux.blocks.data

import java.util.HashMap
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.util.Icon
import net.minecraftforge.common.ForgeDirection
import BlockIcons._
//remove if not needed
import scala.collection.JavaConversions._

object BlockIcons {
  val FRONT: String = "front"
  val BACK: String = "front"
  val LEFT: String = "left"
  val RIGHT: String = "right"
  val TOP: String = "top"
  val BOTTOM: String = "bottom"
  val FLOWING: String = "flowing"
  val STILL: String = "still"

  def register(reg: IconRegister) = {
    for (icon <- ICONS) {
      icon.register(reg)
    }
  }

  private var ICONS = List[BlockIcons]()

  val BATTERY_CONTROLLER = new BlockIcons(Map(
    FRONT -> new IconData("fasterflux:batterycontroller")))

  val BATTERY_ANODE = new BlockIcons(Map(
    FRONT -> new IconData("fasterflux:anode")))

  val BATTERY_CASE = new BlockIcons(Map(
    FRONT -> new IconData("fasterflux:batterycase")))

  val BATTERY_OUT = new BlockIcons(Map(
    FRONT -> new IconData("fasterflux:batteryout")))

  val BATTERY_IN = new BlockIcons(Map(
    FRONT -> new IconData("fasterflux:batteryin")))

  val LIQUID_NETHER = new BlockIcons(Map(
    FLOWING -> new IconData("fasterflux:liquidnetherflow"),
    STILL -> new IconData("fasterflux:liquidnetherstill")))

  val UNKNOWN = new BlockIcons(Map(
    FRONT -> new IconData("fasterflux:unknown")))

  class BlockIcons(icons: Map[String, IconData]) {
    ICONS = ICONS :+ this

    def register(reg: IconRegister) {
      for ((id, icon) <- icons) {
        icon.register(reg)
      }
    }

    def getIcon(id: String): Icon = getIconData(id) match {
      case null => null
      case icon => icon get
    }

    def getIconData(id: String): IconData = icons get id match {
      case None => null
      case icon => icon getOrElse null
    }
  }
}

