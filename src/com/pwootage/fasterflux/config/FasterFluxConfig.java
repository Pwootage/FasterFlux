package com.pwootage.fasterflux.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class FasterFluxConfig  {
	public static final String CATEGORY_OTHER = "other";
	private File confDir;
	private Configuration conf;
	
	public final int multiblockId1;
	public final int syncDelay;
	
	public FasterFluxConfig(File file) {
		confDir = file;
		conf = new Configuration(new File(confDir, "fasterflux.cfg"));
		
		conf.load();
		
		//Blocks
		multiblockId1 = conf.getBlock("multiblockId1", 950, "Main multiblock ID").getInt();
		
		//Other configs
		syncDelay = conf.get(CATEGORY_OTHER, "syncDelay", 1, "Delay between sync packets (ticks, can reduce network usage)").getInt();
		
		//Done!
		conf.save();
	}
}
