package com.pwootage.fasterflux.blocks

import net.minecraft.block.material.Material
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController
import com.pwootage.fasterflux.config.FasterFluxConfig
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry

class FasterFluxBlocks(conf: FasterFluxConfig, event: FMLInitializationEvent) {

  val testBlock: FFMultiBlock = new FFMultiBlock(conf.multiblockId1, Material.iron)

  GameRegistry.registerBlock(testBlock, classOf[FFMultiBlockPlacer], "ff.mb")
  GameRegistry.registerTileEntity(classOf[TileEntityBatteryController], "ff.battery.controller")
}