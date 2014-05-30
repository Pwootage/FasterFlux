package com.pwootage.fasterflux.blocks;

import java.util.HashMap;
import java.util.Map;

import com.pwootage.fasterflux.blocks.data.BlockIcons;
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FFMultiBlockPlacer extends ItemBlock {

	public FFMultiBlockPlacer(int id) {
		super(id);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int damage) {
		return damage;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack is) {
		return "mb.placer." + is.getItemDamage();
	}
	
//	@Override
//	public Icon getIconFromDamage(int dmg) {
//		FFMultiBlockType type = FFMultiBlockType.getTypeFromMeta(dmg);
//		return type.icons.getIcon(BlockIcons.FRONT);
//	}
	
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(IconRegister reg) {
//		super.registerIcons(reg);
//	}
	
	
}
