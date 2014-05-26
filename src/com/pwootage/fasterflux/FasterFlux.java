package com.pwootage.fasterflux;

import java.util.logging.Logger;

import com.pwootage.fasterflux.blocks.FasterFluxBlocks;
import com.pwootage.fasterflux.config.FasterFluxConfig;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = FasterFlux.MODID, version = FasterFlux.VERSION, dependencies="required-after:DragonAPI")
@NetworkMod(clientSideRequired=true, serverSideRequired=true)
public class FasterFlux
{
	public static Logger logger;
    public static final String MODID = "fasterflux";
    public static final String VERSION = "1.0";
    
    public static FasterFluxConfig conf;
    public static FasterFluxBlocks blocks;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    	conf = new FasterFluxConfig(event.getModConfigurationDirectory());
    	logger = event.getModLog();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	blocks = new FasterFluxBlocks(conf, event);
    }
}