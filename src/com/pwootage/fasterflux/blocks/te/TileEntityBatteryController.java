package com.pwootage.fasterflux.blocks.te;

import java.math.BigInteger;

import com.pwootage.fasterflux.FasterFlux;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityBatteryController extends FFTileEntityBase {
	private int totalLiquid;
	private BigInteger totalEnergy;

	public TileEntityBatteryController() {
		totalLiquid = 0;
		totalEnergy = BigInteger.ZERO;
	}

	@Override
	protected void writeSyncTag(NBTTagCompound nbt) {
		super.writeSyncTag(nbt);
		nbt.setInteger("liquid", totalLiquid);
		nbt.setByteArray("totalEnergy", totalEnergy.toByteArray());
	}

	@Override
	protected void readSyncTag(NBTTagCompound nbt) {
		super.readSyncTag(nbt);
		totalLiquid = nbt.getInteger("liquid");
		totalEnergy = new BigInteger(nbt.getByteArray("totalEnergy"));
	}

	public int getTotalLiquid() {
		return totalLiquid;
	}

	public BigInteger getTotalEnergy() {
		return totalEnergy;
	}

	@Override
	public int getTileEntityBlockID() {
		return FasterFlux.blocks.testBlock.blockID;
	}

	@Override
	public void updateEntity(World world, int x, int y, int z, int meta) {
		if (!worldObj.isRemote) {
			totalEnergy = totalEnergy.add(BigInteger.ONE);
		}
	}

	@Override
	protected String getTEName() {
		return "fasterflux.battery.controller";
	}
}
