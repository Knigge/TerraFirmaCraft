package com.bioxx.tfc.api.Interfaces;

import net.minecraft.item.ItemStack;

public interface IEquipable 
{
	EquipType getEquipType(ItemStack is);

	enum EquipType
	{
		BACK, NULL
	}

	void onEquippedRender();

	boolean getTooHeavyToCarry(ItemStack is);
}
