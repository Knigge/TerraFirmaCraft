package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.api.TFCFluids;
import net.minecraft.block.material.Material;

public class BlockLava extends BlockCustomLiquid {
	public BlockLava() {
		super(TFCFluids.LAVA, Material.lava);
	}
}
