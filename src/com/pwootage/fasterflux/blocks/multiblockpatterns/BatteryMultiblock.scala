package com.pwootage.fasterflux.blocks.multiblockpatterns

import Reika.DragonAPI.Instantiable.MultiBlockBlueprint
import com.pwootage.fasterflux.FasterFlux
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType
import net.minecraft.world.IWorldAccess
import net.minecraft.world.IBlockAccess
import net.minecraftforge.common.ForgeDirection
import net.minecraft.world.World
import Reika.DragonAPI.Instantiable.Data.StructuredBlockArray
import Reika.DragonAPI.Libraries.World.ReikaWorldHelper
import com.pwootage.fasterflux.blocks.te.TileEntityBatterySlaveBase

object BatteryMultiblock {
  /**
   * Check for valid MB for the controller specified
   */
  def checkForMB(world: World, x: Int, y: Int, z: Int): Boolean = {
    val id = world.getBlockId(x, y, z)
    val meta = world.getBlockMetadata(x, y, z)
    val ba = new StructuredBlockArray(world)
    var bX = x - findLastCasingMultiBlockInDir(world, x, y, z, ForgeDirection.WEST, 6)
    var bY = y - findLastCasingMultiBlockInDir(world, bX, y, z, ForgeDirection.DOWN, 4)
    var bZ = z - findLastCasingMultiBlockInDir(world, bX, bY, z, ForgeDirection.NORTH, 6)
    bX = bX - findLastCasingMultiBlockInDir(world, bX, bY, bZ, ForgeDirection.WEST, 6)
    bY = bY - findLastCasingMultiBlockInDir(world, bX, bY, bZ, ForgeDirection.DOWN, 4)
    bZ = bZ - findLastCasingMultiBlockInDir(world, bX, bY, bZ, ForgeDirection.NORTH, 6)

    //Verify every block is what it should be
    for (x1 <- 0 to 6) for (y1 <- 0 to 4) for (z1 <- 0 to 6) {
      val x2 = x1 + bX
      val y2 = y1 + bY
      val z2 = z1 + bZ
      val id = world.getBlockId(x2, y2, z2)
      val meta = world.getBlockMetadata(x2, y2, z2)
      if (x1 == 0 || y1 == 0 || z1 == 0 || x1 == 6 || y1 == 4 || z1 == 6) {
        if (x2 == x && y2 == y && z2 == z) {
          //This is me. I am ok. :D
        } else {
          if (!validCasingID(id) || !validCasingMeta(meta)) return false
        }
      } else if (x1 == 3 && (y1 == 2 || y1 == 3) && z1 == 3) {
    	  if (!validCasingID(id) && meta != FFMultiBlockType.BATTERY_ANODE.meta) return false
      } else {
        if (id != 0) return false
      }
      val te = world.getBlockTileEntity(x2, y2, z2)
      if (te != null && te.isInstanceOf[TileEntityBatterySlaveBase]) {
        te.asInstanceOf[TileEntityBatterySlaveBase].setParent(x, y, z)
      }
    }
    return true
  }

  def findLastCasingMultiBlockInDir(world: World, x: Int, y: Int, z: Int, dir: ForgeDirection, max: Int): Int = {
    for (off <- 1 to max) {
      val x1 = x + off * dir.offsetX
      val y1 = y + off * dir.offsetY
      val z1 = z + off * dir.offsetZ
      val id = world.getBlockId(x1, y1, z1)
      val meta = world.getBlockMetadata(x1, y1, z1)
      if (id != FasterFlux.conf.multiblockId1 || !validCasingMeta(meta)) {
        return off - 1
      }
    }
    return max
  }

  def validCasingMeta(meta: Int): Boolean = meta match {
    case FFMultiBlockType.BATTERY_CASE.meta => true
    case FFMultiBlockType.BATTERY_OUTPUT.meta => true
    case FFMultiBlockType.BATTERY_INPUT.meta => true
    case _ => false
  }

  def validCasingID(id: Int): Boolean = {
    id == FasterFlux.conf.multiblockId1
  }
}