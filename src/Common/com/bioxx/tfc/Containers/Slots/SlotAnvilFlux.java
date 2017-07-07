package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.TFCItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@SuppressWarnings("SameParameterValue")
public class SlotAnvilFlux extends Slot {
	public SlotAnvilFlux(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() == TFCItems.powder && itemstack.getItemDamage() == 0;
	}
}
