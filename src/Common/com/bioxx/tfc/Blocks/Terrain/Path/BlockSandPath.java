package com.bioxx.tfc.Blocks.Terrain.Path;

import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSandPath extends BaseBlockPath {
	public BlockSandPath(boolean shifted) {
		super(Material.sand);

		this.pathType = "sand";
		this.isShifted = shifted;
		this.baseBlock = (shifted) ? TFCBlocks.sand2 : TFCBlocks.sand;

		this.setHardness(1.5F);
		this.setBlockName("SandPath");
		this.setStepSound(Block.soundTypeSand);
	}
}
