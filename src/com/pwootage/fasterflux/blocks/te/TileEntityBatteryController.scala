package com.pwootage.fasterflux.blocks.te

import java.math.BigInteger
import com.pwootage.fasterflux.FasterFlux
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.tileentity.TileEntity
import net.minecraft.world.World
import scala.reflect.{BeanProperty, BooleanBeanProperty}

class TileEntityBatteryController extends FFTileEntityBase {
  private var _totalLiquid: Int = 0
  private var _totalEnergy: BigInteger = BigInteger.ZERO
  
  def totalLiquid = _totalLiquid
  def totalEnergy = _totalEnergy

  protected override def writeSyncTag(nbt: NBTTagCompound) {
    super.writeSyncTag(nbt)
    nbt setInteger ("liquid", _totalLiquid)
    nbt setByteArray ("totalEnergy", _totalEnergy.toByteArray)
  }

  protected override def readSyncTag(nbt: NBTTagCompound) {
    super.readSyncTag(nbt)
    _totalLiquid = nbt getInteger "liquid"
    _totalEnergy = new BigInteger(nbt getByteArray "totalEnergy")
  }

  override def getTileEntityBlockID(): Int = FasterFlux.blocks.testBlock.blockID

  override def updateEntity(world: World, x: Int, y: Int, z: Int, meta: Int) {
    if (!worldObj.isRemote) {
      _totalEnergy = _totalEnergy.add(BigInteger.ONE)
    }
  }

  protected override def getTEName(): String = "fasterflux.battery.controller"
}