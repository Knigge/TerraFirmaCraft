package com.bioxx.tfc.Blocks.Terrain.Path;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDirtPath extends BaseBlockPath {
	public BlockDirtPath(boolean shifted) {
		super(Material.ground);

		this.pathType = "dirt";
		this.isShifted = shifted;
		this.baseBlock = (shifted) ? TFCBlocks.dirt2 : TFCBlocks.dirt;

		this.setHardness(3F);
		this.setBlockName("DirtPath");
		this.setStepSound(Block.soundTypeGravel);
	}
}
