package com.bioxx.tfc.Containers.Slots;

import com.bioxx.tfc.api.Enums.EnumSize;
import com.bioxx.tfc.api.Interfaces.ISize;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "Convert2Diamond"})
public class SlotSize extends Slot {
	protected EnumSize size = EnumSize.MEDIUM;
	private List<Item> excpetions = new ArrayList<Item>();
	private List<Item> inclusions = new ArrayList<Item>();

	public SlotSize(IInventory iinventory, int i, int j, int k) {
		super(iinventory, i, j, k);
	}

	@Override
	public boolean isItemValid(ItemStack itemstack) {
		boolean except = excpetions.contains(itemstack.getItem());
		boolean include = inclusions.contains(itemstack.getItem()) || inclusions.isEmpty();
		if (itemstack.getItem() instanceof ISize && ((ISize) itemstack.getItem()).getSize(itemstack).stackSize >= size.stackSize && !except && include)
			return true;
		else if (!(itemstack.getItem() instanceof ISize) && !except && include)
			return true;
		return false;
	}

	public SlotSize setSize(EnumSize s) {
		size = s;
		return this;
	}

	public SlotSize addItemException(Item... ex) {
		Collections.addAll(excpetions, ex);
		return this;
	}

	public SlotSize addItemInclusion(Item... ex) {
		Collections.addAll(inclusions, ex);
		return this;
	}
}
