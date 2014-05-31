package com.pwootage.fasterflux.items

import net.minecraft.util.Icon
import net.minecraft.item.Item
import net.minecraft.creativetab.CreativeTabs
import com.pwootage.fasterflux.FasterFlux

class ItemBasicCraftable(id: Int, iconName: String, name: String) extends Item(id) {
  setMaxStackSize(64)
  setCreativeTab(FasterFlux.tab)
  setUnlocalizedName(name)
  iconString = iconName
}