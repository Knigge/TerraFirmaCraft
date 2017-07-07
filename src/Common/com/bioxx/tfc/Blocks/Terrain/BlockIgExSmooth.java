package com.bioxx.tfc.Blocks.Terrain;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.Constant.Global;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;

public class BlockIgExSmooth extends BlockSmooth {
	public BlockIgExSmooth() {
		super(Material.rock);
		this.setCreativeTab(TFCTabs.TFC_BUILDING);
		names = Global.STONE_IGEX;
		icons = new IIcon[names.length];
	}
}
