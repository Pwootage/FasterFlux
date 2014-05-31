package com.pwootage.fasterflux.blocks

import net.minecraftforge.fluids.BlockFluidFinite
import net.minecraftforge.fluids.Fluid
import net.minecraft.block.material.Material
import net.minecraftforge.fluids.BlockFluidClassic
import net.minecraft.util.Icon
import com.pwootage.fasterflux.blocks.data.BlockIcons
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import com.pwootage.fasterflux.FasterFlux

class BlockFluidNetherstar(id: Int, fluid: Fluid) extends BlockFluidClassic(id, fluid, Material.lava) {
  setCreativeTab(FasterFlux.tab)
  setUnlocalizedName("ff.liquid.star")
  
  override def getIcon(side: Int, meta: Int): Icon = {
    if (side == 0 || side == 1) {
      BlockIcons.LIQUID_NETHER.getIcon(BlockIcons.STILL)
    } else {
      BlockIcons.LIQUID_NETHER.getIcon(BlockIcons.FLOWING)
    }
  }
  
  override def canDisplace(world: IBlockAccess, x: Int, y: Int, z: Int):Boolean = {
    val mat = world.getBlockMaterial(x, y, z)
    if (mat.isLiquid()) return false
    return super.canDisplace(world, x, y, z)
  }
  
    override def displaceIfPossible(world: World, x: Int, y: Int, z: Int):Boolean = {
    val mat = world.getBlockMaterial(x, y, z)
    if (mat.isLiquid()) return false
    return super.canDisplace(world, x, y, z)
  }
}