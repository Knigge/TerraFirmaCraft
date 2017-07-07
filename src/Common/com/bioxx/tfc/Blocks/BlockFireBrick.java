package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockFireBrick extends BlockTerra {
	public BlockFireBrick() {
		super(Material.rock);
		this.setCreativeTab(TFCTabs.TFC_BUILDING);
	}

	@Override
	public void registerBlockIcons(IIconRegister iconRegisterer) {
		this.blockIcon = iconRegisterer.registerIcon(Reference.MOD_ID + ":" + "rocks/Fire Brick");
	}
}
