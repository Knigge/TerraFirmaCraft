package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.Items.ItemMeltedMetal;
import com.bioxx.tfc.api.TFCItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

@SuppressWarnings("WeakerAccess")
public class SlotMetal extends Slot {
	public SlotMetal(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		return itemstack.getItem() instanceof ItemMeltedMetal || itemstack.getItem() == TFCItems.ceramicMold;
	}
}
