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

class TileEntityBatteryController extends FFTileEntityBase with IEnergyHandler {
  private var _totalLiquid: Int = 0
  private var _totalEnergy: BigInt = 0
  private var _validMB: Boolean = false
  private val mbCheckTimer: StepTimer = new StepTimer(FasterFlux.conf.mbDelay)

  def totalLiquid = _totalLiquid
  def totalEnergy = _totalEnergy
  def validMB = _validMB

  protected override def writeSyncTag(nbt: NBTTagCompound) {
    super.writeSyncTag(nbt)
    nbt setInteger ("liquid", _totalLiquid)
    nbt setByteArray ("totalEnergy", _totalEnergy.toByteArray)
    nbt setBoolean ("validMB", _validMB)
  }

  protected override def readSyncTag(nbt: NBTTagCompound) {
    super.readSyncTag(nbt)
    _totalLiquid = nbt getInteger "liquid"
    _totalEnergy = new BigInteger(nbt getByteArray "totalEnergy")
    _validMB = nbt getBoolean "validMB"
  }

  override def getTileEntityBlockID(): Int = FasterFlux.blocks.mb1.blockID

  override def updateEntity(world: World, x: Int, y: Int, z: Int, meta: Int) {
    if (!worldObj.isRemote) {
      val loss = calcLoss()
      _totalEnergy -= loss
      mbCheckTimer.update()
      if (mbCheckTimer.checkCap()) {
        _validMB = BatteryMultiblock.checkForMB(worldObj, x, y, z);
      }
    }
  }

  protected override def getTEName(): String = "fasterflux.battery.controller"

  override def receiveEnergy(dir: ForgeDirection, amt: Int, simulate: Boolean): Int = {
    if (!_validMB) return 0;
    if (!simulate) {
      _totalEnergy += amt;
    }
    return amt;
  }
  override def extractEnergy(dir: ForgeDirection, amt: Int, simulate: Boolean): Int = {
    if (!_validMB) return 0;
    val canSend: Int = if (amt > _totalEnergy) getEnergyStored(dir) else amt
    if (!simulate) {
      _totalEnergy -= canSend
    }
    return canSend
  }

  override def canInterface(dir: ForgeDirection): Boolean = {
    if (!_validMB) return false;
    return true
  }

  override def getEnergyStored(dir: ForgeDirection): Int = {
    if (!_validMB) return 0;
    if (_totalEnergy > FasterFlux.MAX_REPORTED_RF) FasterFlux.MAX_REPORTED_RF else _totalEnergy.toInt
  }

  override def getMaxEnergyStored(dir: ForgeDirection): Int = {
    if (!_validMB) return 0;
    return Int.MaxValue
  }
  
  def calcLoss():Int = {
    val log2 = _totalEnergy.bitLength
    val log2Squared = log2*log2
    return log2Squared
  }
}