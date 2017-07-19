package com.bioxx.tfc.Blocks.Terrain.Path;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGravelPath extends BaseBlockPath {
	public BlockGravelPath(boolean shifted) {
		super(Material.ground);

		this.pathType = "gravel";
		this.isShifted = shifted;
		this.baseBlock = (shifted) ? TFCBlocks.gravel2 : TFCBlocks.gravel;

		this.setHardness(2F);
		this.setBlockName("GravelPath");
		this.setStepSound(Block.soundTypeGravel);
	}
}
