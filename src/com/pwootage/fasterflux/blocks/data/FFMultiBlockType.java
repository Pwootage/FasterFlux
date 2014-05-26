package com.pwootage.fasterflux.blocks.data;

import java.util.HashMap;
import java.util.Map;

/**
 * All of the types of multiblock that I have. Note that order matters!
 *
 */
public enum FFMultiBlockType {
	//Note how these are in increasing order - I use ordinal()
	BATTERY_CONTROLLER(0, BlockIcons.BATTERY_CONTROLLER, true), 
	BATTERY_ANODE(1, BlockIcons.BATTERY_ANODE, false),
	UNKNOWN(17, BlockIcons.UNKNOWN, false),
	;

	public final int meta;
	public final BlockIcons icons;
	public final boolean hasTE;

	private FFMultiBlockType(int meta, BlockIcons icons, boolean hasTE) {
		this.meta = meta;
		this.icons = icons;
		this.hasTE = hasTE;
	}
	
	public static FFMultiBlockType getTypeFromMeta(int meta) {
		if (meta >= values().length) {
			return UNKNOWN;
		}
		return values()[meta];
	}
}
