package com.bioxx.tfc.Blocks.Vanilla;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockButtonWood;
import net.minecraft.util.IIcon;

public class BlockCustomButtonWood extends BlockButtonWood {
	public BlockCustomButtonWood() {
		super();
		this.setCreativeTab(TFCTabs.TFC_DEVICES);
	}

	/**
	 * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
	 */
	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int par1, int par2) {
		return TFCBlocks.planks.getBlockTextureFromSide(0);
	}
}
