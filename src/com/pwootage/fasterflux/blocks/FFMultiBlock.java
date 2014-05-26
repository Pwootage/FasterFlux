package com.pwootage.fasterflux.blocks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pwootage.fasterflux.blocks.data.BlockIcons;
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType;
import com.pwootage.fasterflux.blocks.te.TileEntityBatteryController;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FFMultiBlock extends Block {

	public FFMultiBlock(int id, Material mat) {
		super(id, mat);
		setUnlocalizedName("ff.mb");
		setCreativeTab(CreativeTabs.tabBlock);
		setTextureName("fasterflux:unknown");
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List subItems) {
		for (int meta = 0; meta < 16; meta++) {
			subItems.add(new ItemStack(this, 1, meta));
		}
	}

	@Override
	public Icon getBlockTexture(IBlockAccess ba, int x, int y, int z, int side) {
		int meta = ba.getBlockMetadata(x, y, z);
		FFMultiBlockType type = FFMultiBlockType.getTypeFromMeta(meta);
		return getIconBasedOnFacing(type.icons);
	}

	private Icon getIconBasedOnFacing(BlockIcons icons) {
		return icons.getIcon(BlockIcons.FRONT);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister reg) {
		super.registerIcons(reg);
		BlockIcons.BATTERY_ANODE.register(reg);
		BlockIcons.BATTERY_CONTROLLER.register(reg);
		BlockIcons.UNKNOWN.register(reg);
	}

	@Override
	public boolean hasTileEntity(int meta) {
		return FFMultiBlockType.getTypeFromMeta(meta).hasTE;
	}
	
	@Override
	public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntityBatteryController();
	}
}
