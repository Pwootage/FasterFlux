package com.pwootage.fasterflux.blocks.data;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;

public class IconData {
	public final String toLoad;
	private Icon loaded = null;

	public IconData(String toLoad) {
		this.toLoad = toLoad;
	}

	/**
	 * Register, if not already registered
	 * 
	 * @param reg
	 *            Icon register to register with
	 */
	public void register(IconRegister reg) {
		if (this.loaded == null) {
			this.loaded = reg.registerIcon(toLoad);
		}
	}

	public Icon get() {
		return loaded;
	}
}
