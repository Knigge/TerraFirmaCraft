package com.bioxx.tfc.WorldGen.GenLayers;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerDebug extends GenLayerTFC
{
	public GenLayerDebug(long par1, GenLayer par3GenLayer)
	{
		super(par1);
		super.parent = (GenLayerTFC) par3GenLayer;
	}

	/**
	 * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
	 * amounts, or biomeList[] indices based on the particular GenLayer subclass.
	 */
	@Override
	public int[] getInts(int par1, int par2, int par3, int par4)
	{
		int[] var9 = this.parent.getInts(par1, par2, par3, par4);
		validateIntArray(var9, par3, par4);
		return var9;
	}
}
