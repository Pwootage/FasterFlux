package com.pwootage.fasterflux.config

import java.io.File
import net.minecraftforge.common.Configuration
import FasterFluxConfig._

object FasterFluxConfig {

  val CATEGORY_OTHER = "other"
}

class FasterFluxConfig(private var confDir: File) {

  private var conf: Configuration = new Configuration(new File(confDir, "fasterflux.cfg"))

  conf.load()
  
  //Blocks
  val multiblockId1 = conf.getBlock("multiblockId1", 950, "Main multiblock ID").getInt
  val liquidNetherStarId = conf.getBlock("liquidNetherStar", 951, "Liquid Nether Star").getInt
  
  //Items
  private var baseItemID = 16700;
  private def getNextID():Int = {
    baseItemID += 1
    return baseItemID - 1
  }
  val scWireEmpty = conf.getItem("scWireEmpty", getNextID()).getInt
  val scWire = conf.getItem("scWire", getNextID()).getInt
  val basePlate = conf.getItem("basePlate", getNextID()).getInt
  val computerCore = conf.getItem("computerCore", getNextID()).getInt
  val liquidNetherBucket = conf.getItem("liquidNetherBucket", getNextID()).getInt
  val scNetherAssembly = conf.getItem("scNetherAssembly", getNextID()).getInt
  val scNetherUnstable = conf.getItem("scNetherUnstable", getNextID()).getInt
  val scNetherStable = conf.getItem("scNetherStable", getNextID()).getInt
  
  //Other
  val syncDelay = conf.get(CATEGORY_OTHER, "syncDelay", 1, "Delay between sync packets (ticks, can reduce network usage)").getInt
  val mbDelay = conf.get(CATEGORY_OTHER, "mbDelay", 20, "Delay between multiblock checks (ticks)").getInt

  conf.save()
}
