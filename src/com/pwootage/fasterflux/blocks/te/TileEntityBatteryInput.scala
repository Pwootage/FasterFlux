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

class TileEntityBatteryInput extends TileEntityBatterySlaveBase with IEnergyHandler {

  override def getTileEntityBlockID(): Int = FasterFlux.blocks.mb1.blockID

  override def updateEntity(world: World, x: Int, y: Int, z: Int, meta: Int) {
  }

  protected override def getTEName(): String = "fasterflux.battery.output"

  override def receiveEnergy(dir: ForgeDirection, amt: Int, simulate: Boolean): Int = {
    val parent = getParent()
    if (parent != null) {
      return parent.receiveEnergy(dir, amt, simulate)
    } else {
      return 0;
    }
  }

  override def extractEnergy(dir: ForgeDirection, amt: Int, simulate: Boolean): Int = 0

  override def canInterface(dir: ForgeDirection): Boolean = true

  override def getEnergyStored(dir: ForgeDirection): Int = {
    val parent = getParent()
    if (parent == null) {
      return 0;
    } else {
      return parent.getEnergyStored(dir)
    }
  }

  override def getMaxEnergyStored(dir: ForgeDirection): Int = Int.MaxValue
}