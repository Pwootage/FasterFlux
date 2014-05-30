package com.pwootage.fasterflux.blocks

import java.util.HashMap
import java.util.Map
import com.pwootage.fasterflux.blocks.data.BlockIcons
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType
import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.item.ItemBlock
import net.minecraft.item.ItemStack
import net.minecraft.util.Icon
import cpw.mods.fml.relauncher.Side
import cpw.mods.fml.relauncher.SideOnly

class FFMultiBlockPlacer(id: Int) extends ItemBlock(id) {
  setHasSubtypes(true)

  override def getMetadata(damage: Int): Int = damage

  override def getUnlocalizedName(is: ItemStack): String = "mb.placer." + is.getItemDamage
}