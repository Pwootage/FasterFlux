package com.pwootage.fasterflux.blocks.te

import java.math.BigInteger
import com.pwootage.fasterflux.FasterFlux
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import scala.reflect.{ BeanProperty, BooleanBeanProperty }
import Reika.DragonAPI.Instantiable.StepTimer
import com.pwootage.fasterflux.blocks.FFMultiBlock
import net.minecraftforge.common.ForgeDirection
import com.pwootage.fasterflux.blocks.multiblockpatterns.BatteryMultiblock
import cofh.api.energy.IEnergyHandler

abstract class TileEntityBatterySlaveBase extends FFTileEntityBase {
  private var _parentTE: (Int, Int, Int) = (0, 0, 0)
  private var _hasParent = false

  protected override def writeSyncTag(nbt: NBTTagCompound) {
    super.writeSyncTag(nbt)
    val (x, y, z) = _parentTE
    nbt setBoolean ("hasParent", _hasParent)
    nbt setInteger ("parentX", x)
    nbt setInteger ("parentY", y)
    nbt setInteger ("parentZ", z)
  }

  protected override def readSyncTag(nbt: NBTTagCompound) {
    super.readSyncTag(nbt)
    _hasParent = nbt getBoolean "hasParent"
    val x = nbt getInteger "parentX"
    val y = nbt getInteger "parentY"
    val z = nbt getInteger "parentz"
    _parentTE = (x, y, z)
  }

  def setParent(x: Int, y: Int, z: Int) {
    _hasParent = true
    _parentTE = (x, y, z)
  }
  def getParent(): TileEntityBatteryController = {
    if (!_hasParent) return null
    val p = worldObj.getBlockTileEntity(_parentTE._1, _parentTE._2, _parentTE._3)
    if (p == null) {
      _hasParent = false
      return null
    } else {
      return p.asInstanceOf[TileEntityBatteryController]
    }
  }
}