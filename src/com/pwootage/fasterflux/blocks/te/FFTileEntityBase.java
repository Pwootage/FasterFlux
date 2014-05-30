package com.pwootage.fasterflux.blocks.te;

import com.pwootage.fasterflux.FasterFlux;

import net.minecraft.world.World;
import Reika.DragonAPI.DragonAPICore;
import Reika.DragonAPI.Base.TileEntityBase;

public abstract class FFTileEntityBase extends TileEntityBase {

	public FFTileEntityBase() {
		super();
	}

	@Override
	protected void animateWithTick(World world, int x, int y, int z) {
	}

	@Override
	public int getRedstoneOverride() {
		return 0;
	}

	@Override
	public boolean shouldRenderInPass(int pass) {
		if (pass == 0) {
			return true;
		}
		return false;
	}
	
	@Override
	public int getPacketDelay() {
		if (DragonAPICore.isSinglePlayer()) {
			return 1; //No need to slow syncs here...
		}
		return Math.max(1, FasterFlux.conf.syncDelay);
	}
}