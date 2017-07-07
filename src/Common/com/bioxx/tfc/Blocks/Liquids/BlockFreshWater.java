package com.bioxx.tfc.Blocks.Liquids;

import net.minecraft.block.material.Material;

import net.minecraftforge.fluids.Fluid;

@SuppressWarnings("SameParameterValue")
public class BlockFreshWater extends BlockCustomLiquid
{
	public BlockFreshWater(Fluid fluid)
	{
		super(fluid, Material.water);
	}
}
