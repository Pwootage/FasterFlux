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
  
  val multiblockId1 = conf.getBlock("multiblockId1", 950, "Main multiblock ID").getInt

  val syncDelay = conf.get(CATEGORY_OTHER, "syncDelay", 1, "Delay between sync packets (ticks, can reduce network usage)").getInt

  conf.save()
}
