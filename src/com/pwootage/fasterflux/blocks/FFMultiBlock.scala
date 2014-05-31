package com.pwootage.fasterflux.blocks

import java.util.List
import com.pwootage.fasterflux.FasterFlux
import com.pwootage.fasterflux.blocks.data.BlockIcons
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import com.pwootage.fasterflux.utils.MCUtils.untypedListAdd
import cpw.mods.fml.relauncher.SideOnly
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Icon
import net.minecraft.world.World
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryOutput
import cpw.mods.fml.relauncher.Side
import com.pwootage.fasterflux.blocks.te.TileEntityBatterySlaveBase
import cofh.api.energy.IEnergyHandler
import net.minecraftforge.common.ForgeDirection
import net.minecraft.entity.Entity
import net.minecraft.entity.EnumCreatureType

class FFMultiBlock(id: Int, mat: Material) extends Block(id, mat) {
  setUnlocalizedName("ff.mb")
  setCreativeTab(FasterFlux.tab)
  setTextureName("fasterflux:unknown")
  setHardness(2)
  setStepSound(Block.soundMetalFootstep)
  setResistance(50000)

  override def damageDropped(meta: Int): Int = meta

  @SideOnly(Side.CLIENT)
  override def getSubBlocks(id: Int, tab: CreativeTabs, subItems: List[_]) {
    for ((meta, t) <- FFMultiBlockType.validTypes) {
      untypedListAdd(subItems, new ItemStack(this, 1, meta))
    }
  }

  override def getIcon(side: Int, meta: Int): Icon = {
    val t = FFMultiBlockType.getTypeFromMeta(meta)
    getIconBasedOnFacing(t.icons)
  }

  private def getIconBasedOnFacing(icons: BlockIcons.BlockIcons): Icon = icons.getIcon(BlockIcons.FRONT)

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, meta: Int, par7: Float, par8: Float, par9: Float): Boolean = {
    System.out.println(x + ", " + y + ", " + z)
    val te = world.getBlockTileEntity(x, y, z)
    if (te == null || player.isSneaking) {
      return false
    } else {
      if (te.isInstanceOf[TileEntityBatterySlaveBase]) {
        System.out.println(te.asInstanceOf[TileEntityBatterySlaveBase].getParent())
      }
      if (te.isInstanceOf[IEnergyHandler]) {
        System.out.println(te.asInstanceOf[IEnergyHandler].getEnergyStored(ForgeDirection.UNKNOWN))
      }
      player.openGui(FasterFlux, 0, world, x, y, z)
      return true
    }
  }

  @SideOnly(Side.CLIENT)
  override def registerIcons(reg: IconRegister) {
    super.registerIcons(reg)
    BlockIcons.register(reg)
  }

  override def hasTileEntity(meta: Int): Boolean = {
    FFMultiBlockType.getTypeFromMeta(meta).getTE != null
  }

  override def createTileEntity(world: World, metadata: Int): TileEntity = FFMultiBlockType.getTypeFromMeta(metadata).getTE(world)

  override def canCreatureSpawn(ent: EnumCreatureType, world: World, x: Int, y: Int, z: Int): Boolean = false
}
