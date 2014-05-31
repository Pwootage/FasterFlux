package com.pwootage.fasterflux.blocks

import com.pwootage.fasterflux.blocks.data.BlockIcons
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryInput
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryOutput
import com.pwootage.fasterflux.config.FasterFluxConfig

import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.material.Material
import net.minecraft.item.EnumRarity
import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.ForgeSubscribe
import net.minecraftforge.fluids.Fluid
import net.minecraftforge.fluids.FluidContainerRegistry
import net.minecraftforge.fluids.FluidRegistry

class FasterFluxBlocks(conf: FasterFluxConfig, event: FMLInitializationEvent) {
  val liquidNether = new Fluid("netherStar")
  liquidNether setLuminosity 15
  liquidNether setDensity 3000
  liquidNether setViscosity 6000
  liquidNether setTemperature 2000
  liquidNether setUnlocalizedName "ff.liquid.star"
  liquidNether setRarity EnumRarity.epic
  
  FluidRegistry.registerFluid(liquidNether)
  
  val mb1 = new FFMultiBlock(conf.multiblockId1, Material.iron)
  val liquidNetherBlock = new BlockFluidNetherstar(conf.liquidNetherStarId, liquidNether)

  GameRegistry.registerBlock(mb1, classOf[FFMultiBlockPlacer], "ff.mb")
  MinecraftForge.setBlockHarvestLevel(mb1, "pickaxe", 3)
  GameRegistry.registerBlock(liquidNetherBlock, "ff.liquid.star")
  
  GameRegistry.registerTileEntity(classOf[TileEntityBatteryController], "ff.battery.controller")
  GameRegistry.registerTileEntity(classOf[TileEntityBatteryOutput], "ff.battery.output")
  GameRegistry.registerTileEntity(classOf[TileEntityBatteryInput], "ff.battery.input")
  
  @ForgeSubscribe
  def postStich(event: TextureStitchEvent.Post) {
    liquidNether.setIcons(BlockIcons.LIQUID_NETHER.getIcon(BlockIcons.STILL),
        BlockIcons.LIQUID_NETHER.getIcon(BlockIcons.FLOWING))
  }
  
  MinecraftForge.EVENT_BUS.register(this)
}