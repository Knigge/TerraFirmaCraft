package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Enums.EnumFoodGroup;
import com.bioxx.tfc.api.Interfaces.IFood;
import com.bioxx.tfc.api.Interfaces.ISize;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"SameParameterValue", "CanBeFinal", "Convert2Diamond"})
public class SlotFoodOnly extends SlotSize {
	private List<EnumFoodGroup> excpetionsFG = new ArrayList<EnumFoodGroup>();
	private List<EnumFoodGroup> inclusionsFG = new ArrayList<EnumFoodGroup>();

	public SlotFoodOnly(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		Item item = itemstack.getItem();
		if (item instanceof IFood && ((IFood) item).isUsable(itemstack)) {
			EnumFoodGroup efg = ((IFood) item).getFoodGroup();
			if (efg == null)
				return false;
			boolean except = excpetionsFG.contains(efg);
			boolean include = inclusionsFG.contains(efg) || inclusionsFG.isEmpty();
			if (except || !include)
				return false;
			if (item instanceof ISize && ((ISize) item).getSize(itemstack).stackSize >= size.stackSize)
				return super.isItemValid(itemstack);
		}
		return false;
	}

	public SlotFoodOnly addFGException(EnumFoodGroup... ex) {
		Collections.addAll(excpetionsFG, ex);
		return this;
	}

	public SlotFoodOnly addFGInclusion(EnumFoodGroup... ex) {
		Collections.addAll(inclusionsFG, ex);
		return this;
	}
}
