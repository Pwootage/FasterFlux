package com.pwootage.fasterflux.items

import com.pwootage.fasterflux.blocks.FasterFluxBlocks
import com.pwootage.fasterflux.blocks.data.FFMultiBlockType
import com.pwootage.fasterflux.config.FasterFluxConfig
import Reika.DragonAPI.ModInteract.ThermalRecipeHelper
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.item.Item
import net.minecraft.item.ItemBucket
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fluids.FluidContainerRegistry
import net.minecraftforge.fluids.FluidRegistry
import net.minecraftforge.fluids.FluidStack
import net.minecraftforge.oredict.ShapedOreRecipe
import com.pwootage.fasterflux.FasterFlux

class FasterFluxItems(conf: FasterFluxConfig, event: FMLInitializationEvent, blocks: FasterFluxBlocks) {
  val superconductingWire = new ItemBasicCraftable(conf.scWire, "fasterflux:scwire", "ff.scwire")
  val superconductingWireUnfilled = new ItemBasicCraftable(conf.scWireEmpty, "fasterflux:scwireempty", "ff.scwire.empty")
  val basePlate = new ItemBasicCraftable(conf.basePlate, "fasterflux:baseplate", "ff.baseplate")
  val computerCore = new ItemBasicCraftable(conf.computerCore, "fasterflux:computercore", "ff.computercore")
  val scNetherAssembly = new ItemBasicCraftable(conf.scNetherAssembly, "fasterflux:scnetherassembly", "ff.scnetherassembly")
  val scNetherUnstable = new ItemBasicCraftable(conf.scNetherUnstable, "fasterflux:scnetherunstable", "ff.scnetherunstable")
  val scNetherStable = new ItemBasicCraftable(conf.scNetherStable, "fasterflux:scnetherstable", "ff.scnetherstable")

  val liquidNetherBucket = new ItemBucket(conf.liquidNetherBucket, blocks.liquidNetherBlock.blockID) {
    setUnlocalizedName("ff.liquid.star.bucket")
    setTextureName("fasterflux:liquidnetherbucket")
    setCreativeTab(FasterFlux.tab)
  }
  val liquidHandler = new LiquidHandler();
  MinecraftForge.EVENT_BUS.register(liquidHandler)

  //Need to allow it with the whole bucket handler thing I think

  GameRegistry.registerItem(superconductingWire, superconductingWire.getUnlocalizedName())
  GameRegistry.registerItem(superconductingWireUnfilled, superconductingWireUnfilled.getUnlocalizedName())
  GameRegistry.registerItem(liquidNetherBucket, liquidNetherBucket.getUnlocalizedName())
  FluidContainerRegistry.registerFluidContainer(
    blocks.liquidNether,
    new ItemStack(liquidNetherBucket),
    new ItemStack(Item.bucketEmpty))

  val redstone = FluidRegistry.getFluid("redstone")
  val cryotheum = FluidRegistry.getFluid("cryotheum")
  val ender = FluidRegistry.getFluid("ender")

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(superconductingWireUnfilled, 6),
    "eee",
    "ddd",
    "eee",
    Character.valueOf('e'), "ingotEnderium",
    Character.valueOf('c'), "dustCryotheum",
    Character.valueOf('d'), Item.diamond))

  ThermalRecipeHelper.addCrucibleRecipe(
    new ItemStack(Item.netherStar, 1),
    new FluidStack(blocks.liquidNether, 500),
    240000)

  ThermalRecipeHelper.addFluidTransposerFill(
    new ItemStack(superconductingWireUnfilled, 1),
    new ItemStack(superconductingWire, 1),
    1000,
    new FluidStack(blocks.liquidNether, 100),
    false)

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(scNetherAssembly, 1),
    "ese",
    "sts",
    "ese",
    Character.valueOf('e'), "ingotEnderium",
    Character.valueOf('s'), new ItemStack(superconductingWire, 1),
    Character.valueOf('t'), new ItemStack(Item.netherStar, 1)))

  GameRegistry.addSmelting(scNetherAssembly.itemID, new ItemStack(scNetherUnstable, 1), 20)

  ThermalRecipeHelper.addFluidTransposerFill(
    new ItemStack(scNetherUnstable, 1),
    new ItemStack(scNetherStable, 1),
    5000,
    new FluidStack(blocks.liquidNether, 2000),
    false)

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(basePlate, 4),
    "ses",
    "ede",
    "ses",
    Character.valueOf('e'), "ingotEnderium",
    Character.valueOf('s'), new ItemStack(superconductingWire, 1)))

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(blocks.mb1, 2, FFMultiBlockType.BATTERY_CASE.meta),
    " p ",
    "pep",
    " p ",
    Character.valueOf('p'), new ItemStack(basePlate, 1),
    Character.valueOf('e'), "ingotEnderium"))

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_OUTPUT.meta),
    " p ",
    "psp",
    " p ",
    Character.valueOf('p'), new ItemStack(basePlate, 1),
    Character.valueOf('s'), new ItemStack(superconductingWire, 1)))
  GameRegistry.addShapelessRecipe(
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_OUTPUT.meta),
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_INPUT.meta))
  GameRegistry.addShapelessRecipe(
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_INPUT.meta),
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_OUTPUT.meta))

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_ANODE.meta),
    "sps",
    "ptp",
    "sps",
    Character.valueOf('p'), new ItemStack(basePlate, 1),
    Character.valueOf('t'), new ItemStack(scNetherStable, 1),
    Character.valueOf('s'), new ItemStack(superconductingWire, 1)))

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(computerCore, 1),
    "sss",
    "sts",
    "sps",
    Character.valueOf('s'), new ItemStack(superconductingWire, 1),
    Character.valueOf('t'), new ItemStack(scNetherStable, 1),
    Character.valueOf('p'), new ItemStack(basePlate, 1)))

  GameRegistry.addRecipe(new ShapedOreRecipe(
    new ItemStack(blocks.mb1, 1, FFMultiBlockType.BATTERY_CONTROLLER.meta),
    " p ",
    "pcp",
    " p ",
    Character.valueOf('p'), new ItemStack(basePlate, 1),
    Character.valueOf('c'), new ItemStack(computerCore, 1)))
}