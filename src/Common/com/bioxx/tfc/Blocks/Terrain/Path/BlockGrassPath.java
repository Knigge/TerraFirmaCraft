package com.bioxx.tfc.Blocks.Terrain.Path;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockGrassPath extends BaseBlockPath {
	public BlockGrassPath(boolean shifted) {
		super(Material.grass);

		this.pathType = "grass";
		this.isShifted = shifted;
		this.baseBlock = (shifted) ? TFCBlocks.dirt2 : TFCBlocks.dirt;

		this.setHardness(4F);
		this.setBlockName("GrassPath");
		this.setStepSound(Block.soundTypeGrass);
	}
}
