package com.bioxx.tfc.Blocks.Terrain.Path;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockDryGrassPath extends BaseBlockPath {
	public BlockDryGrassPath(boolean shifted) {
		super(Material.grass);

		this.pathType = "drygrass";
		this.isShifted = shifted;
		this.baseBlock = (shifted) ? TFCBlocks.dirt2 : TFCBlocks.dirt;

		this.setHardness(4F);
		this.setBlockName("DryGrassPath");
		this.setStepSound(Block.soundTypeGrass);
	}
}
