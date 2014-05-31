package com.pwootage.fasterflux.items

import net.minecraftforge.event.ForgeSubscribe
import net.minecraftforge.event.entity.player.FillBucketEvent
import com.pwootage.fasterflux.FasterFlux
import net.minecraft.item.ItemStack
import net.minecraftforge.event.Event.Result

class LiquidHandler {

  @ForgeSubscribe
  def onBucketFill(event: FillBucketEvent) {
    val blockID = event.world.getBlockId(event.target.blockX, event.target.blockY, event.target.blockZ)
    if (blockID == FasterFlux.conf.liquidNetherStarId) {
      if (event.world.getBlockMetadata(event.target.blockX, event.target.blockY, event.target.blockZ) == 0) {
        event.world.setBlock(event.target.blockX, event.target.blockY, event.target.blockZ, 0)
        event.result = new ItemStack(FasterFlux.items.liquidNetherBucket)
        event.setResult(Result.ALLOW)
      }
    }
  }
}