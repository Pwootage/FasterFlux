package com.pwootage.fasterflux.blocks.data

import net.minecraft.client.renderer.texture.IconRegister
import net.minecraft.util.Icon

class IconData(val toLoad: String) {

  private var loaded: Icon = null

  def register(reg: IconRegister) {
    if (this.loaded == null) {
      this.loaded = reg.registerIcon(toLoad)
    }
  }

  def get(): Icon = loaded
}
