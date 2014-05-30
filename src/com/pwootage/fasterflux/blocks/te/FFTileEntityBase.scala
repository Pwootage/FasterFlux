package com.pwootage.fasterflux.blocks.te

import com.pwootage.fasterflux.FasterFlux
import net.minecraft.world.World
import Reika.DragonAPI.DragonAPICore
import Reika.DragonAPI.Base.TileEntityBase

abstract class FFTileEntityBase extends TileEntityBase() {

  protected override def animateWithTick(world: World, x: Int, y: Int, z: Int) {
  }

  override def getRedstoneOverride(): Int = 0

  override def shouldRenderInPass(pass: Int): Boolean = pass == 0

  override def getPacketDelay(): Int = DragonAPICore.isSinglePlayer match {
    case true => 1 //Don't need to throttle ever here, makes SP better
    case false => Math.max(1, FasterFlux.conf.syncDelay)
  } 
}