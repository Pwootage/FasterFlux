package com.pwootage.fasterflux.blocks;

import net.minecraft.block.material.Material;

import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController;
import com.pwootage.fasterflux.config.FasterFluxConfig;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class FasterFluxBlocks {
	public FFMultiBlock testBlock;

	public FasterFluxBlocks(FasterFluxConfig conf, FMLInitializationEvent event) {
		testBlock = new FFMultiBlock(conf.multiblockId1, Material.iron);
		
		GameRegistry.registerBlock(testBlock, FFMultiBlockPlacer.class, "ff.mb");
		GameRegistry.registerTileEntity(TileEntityBatteryController.class, "ff.battery.controller");
	}

}
