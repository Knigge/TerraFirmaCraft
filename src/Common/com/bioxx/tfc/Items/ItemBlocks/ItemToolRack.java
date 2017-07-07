package com.bioxx.tfc.Items.ItemBlocks;

import com.bioxx.tfc.api.Constant.Global;
import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Enums.EnumWeight;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemToolRack extends ItemTerraBlock {
	public ItemToolRack(Block par1) {
		super(par1);
		metaNames = Global.WOOD_ALL;
	}

	@Override
	public EnumSize getSize(ItemStack is) {
		return EnumSize.HUGE;
	}

	@Override
	public EnumWeight getWeight(ItemStack is) {
		return EnumWeight.LIGHT;
	}
}
