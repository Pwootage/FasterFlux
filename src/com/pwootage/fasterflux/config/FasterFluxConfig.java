package com.pwootage.fasterflux.config;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class FasterFluxConfig  {
	private File confDir;
	private Configuration conf;
	
	public final int multiblockId1;
	
	public FasterFluxConfig(File file) {
		confDir = file;
		conf = new Configuration(new File(confDir, "fasterflux.cfg"));
		
		conf.load();
		
		//Blocks
		multiblockId1 = conf.getBlock("multiblockId1", 950).getInt();
		
		//Done!
		conf.save();
	}
}
