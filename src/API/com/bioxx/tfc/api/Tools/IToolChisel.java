package com.bioxx.tfc.api.Tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public interface IToolChisel 
{
	/***
	 * Called from the block that is being chiseled
	 * @return true if the chisel has handled the event
	 */
	boolean onUsed(World world, EntityPlayer player, int x, int y, int z, Block block, int meta, int side, float hitX, float hitY, float hitZ);
	
	
	/***
	 * Called to make sure that the chisel can be used
	 * @param x coordinate of the block
	 * @param y	coordinate of the block
	 * @param z coordinate of the block
	 * @return true if chiseling is allowed
	 */
	@SuppressWarnings("SameReturnValue")
	boolean canChisel(EntityPlayer player, int x, int y, int z);
}
