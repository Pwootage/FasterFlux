package com.pwootage.fasterflux

import net.minecraftforge.client.event.TextureStitchEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.ForgeSubscribe
import cpw.mods.fml.relauncher.SideOnly
import cpw.mods.fml.relauncher.Side
import com.pwootage.fasterflux.blocks.data.BlockIcons

class ClientProxy extends CommonProxy {
  MinecraftForge.EVENT_BUS.register(this)

  @ForgeSubscribe
  @SideOnly(Side.CLIENT)
  def postStich(event: TextureStitchEvent.Post) {
    if (FasterFlux.blocks != null) {
      FasterFlux.blocks.liquidNether.setIcons(BlockIcons.LIQUID_NETHER.getIcon(BlockIcons.STILL),
        BlockIcons.LIQUID_NETHER.getIcon(BlockIcons.FLOWING))
    }
  }
}