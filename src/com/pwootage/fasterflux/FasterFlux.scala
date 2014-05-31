package com.pwootage.fasterflux

import java.util.logging.Logger
import com.pwootage.fasterflux.config.FasterFluxConfig
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.SidedProxy
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.network.NetworkMod
import cpw.mods.fml.common.network.NetworkRegistry
import com.pwootage.fasterflux.blocks.FasterFluxBlocks
import com.pwootage.fasterflux.items.FasterFluxItems
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

@Mod(modid = "fasterflux", version = "1.0.0", dependencies = "required-after:DragonAPI;required-after:ThermalExpansion", modLanguage = "scala")
@NetworkMod(clientSideRequired = true, serverSideRequired = true)
object FasterFlux {
  val MAX_REPORTED_RF = Int.MaxValue / 2

  var logger: Logger = _
  var conf: FasterFluxConfig = _
  var blocks: FasterFluxBlocks = _
  var items: FasterFluxItems = _
  var tab: CreativeTabs = new CreativeTabs("tabFasterFlux") {
    override def getTabIconItem(): Item = items.superconductingWire
  }

  @SidedProxy(clientSide = "com.pwootage.fasterflux.ClientProxy", serverSide = "com.pwootage.fasterflux.CommonProxy")
  var commonProxy: CommonProxy = _

  @EventHandler
  def preinit(event: FMLPreInitializationEvent) {
    conf = new FasterFluxConfig(event.getModConfigurationDirectory)
    logger = event.getModLog
  }

  @EventHandler
  def init(event: FMLInitializationEvent) {
    blocks = new FasterFluxBlocks(conf, event)
    items = new FasterFluxItems(conf, event, blocks)
    NetworkRegistry.instance().registerGuiHandler(this, commonProxy)
  }
}