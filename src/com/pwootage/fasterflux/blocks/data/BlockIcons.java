package com.pwootage.fasterflux.blocks.data;

import java.util.HashMap;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraftforge.common.ForgeDirection;

public abstract class BlockIcons {
	public static String FRONT = "front";
	public static String BACK = "front";
	public static String LEFT = "left";
	public static String RIGHT = "right";
	public static String TOP = "top";
	public static String BOTTOM = "bottom";
	
	private HashMap<String, IconData> icons = new HashMap<>();

	public BlockIcons() {
		init();
	}

	protected void addIcon(String id, IconData icon) {
		icons.put(id, icon);
	}

	/**
	 * Be sure to set all your icons in here!
	 */
	protected abstract void init();

	/**
	 * Register all icons, if not already registered
	 * @param reg IconRegister to register with
	 */
	public void register(IconRegister reg) {
		for (String id : icons.keySet()) {
			IconData icon = icons.get(id);
			icon.register(reg);
		}
	}

	public Icon getIcon(String id) {
		return icons.get(id).get();
	}

	public static final BlockIcons BATTERY_CONTROLLER = new BlockIcons() {
		@Override
		protected void init() {
			addIcon(FRONT, new IconData("fasterflux:batterycontroller"));
		}
	};
	public static final BlockIcons BATTERY_ANODE = new BlockIcons() {
		@Override
		protected void init() {
			addIcon(FRONT, new IconData("fasterflux:anode"));
		}
	};
	public static final BlockIcons BATTERY_CASE = new BlockIcons() {
		@Override
		protected void init() {
			addIcon(FRONT, new IconData("fasterflux:batterycase"));
		}
	};
	public static final BlockIcons UNKNOWN = new BlockIcons() {
		@Override
		protected void init() {
			addIcon(FRONT, new IconData("fasterflux:unknown"));
		}
	};
}
