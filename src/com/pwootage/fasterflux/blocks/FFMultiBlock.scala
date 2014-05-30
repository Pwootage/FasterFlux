package com.pwootage.fasterflux.blocks

import java.util.HashMap
import java.util.List
import java.util.Map
import com.pwootage.fasterflux.FasterFlux
import com.pwootage.fasterflux.utils.MCUtils._
import com.pwootage.fasterflux.blocks.data.BlockIcons
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.Icon
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly
import com.pwootage.fasterflux.blocks.data.BlockIcons

class FFMultiBlock(id: Int, mat: Material) extends Block(id, mat) {
  setUnlocalizedName("ff.mb")
  setCreativeTab(CreativeTabs.tabBlock)
  setTextureName("fasterflux:unknown")

  override def damageDropped(meta: Int): Int = meta

  @SideOnly(Side.CLIENT)
  override def getSubBlocks(id: Int, tab: CreativeTabs, subItems: List[_]) {
    for (meta <- 0 until 16) {
      untypedListAdd(subItems, new ItemStack(this, 1, meta))
    }
  }

  override def getIcon(side: Int, meta: Int): Icon = {
    val `type` = FFMultiBlockType.getTypeFromMeta(meta)
    getIconBasedOnFacing(`type`.icons)
  }

  private def getIconBasedOnFacing(icons: BlockIcons.BlockIcons): Icon = icons.getIcon(BlockIcons.FRONT)

  override def onBlockActivated(world: World, x: Int, y: Int, z: Int, player: EntityPlayer, meta: Int, par7: Float, par8: Float, par9: Float): Boolean = {
    val te = world.getBlockTileEntity(x, y, z)
    if (te == null || player.isSneaking) {
      return false
    } else {
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
    FFMultiBlockType.getTypeFromMeta(meta).hasTE
  }

  override def createTileEntity(world: World, metadata: Int): TileEntity = new TileEntityBatteryController()
}
