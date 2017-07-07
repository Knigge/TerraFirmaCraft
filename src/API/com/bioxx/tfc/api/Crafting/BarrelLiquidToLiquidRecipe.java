package com.bioxx.tfc.api.Crafting;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

import java.util.Stack;

@SuppressWarnings({"WeakerAccess", "CanBeFinal", "Convert2Diamond"})
public class BarrelLiquidToLiquidRecipe extends BarrelRecipe {
	public FluidStack inputfluid;

	public BarrelLiquidToLiquidRecipe(FluidStack fluidInBarrel, FluidStack inputfluid, FluidStack outputFluid) {
		super(null, fluidInBarrel, null, outputFluid);
		this.inputfluid = inputfluid;
	}

	@Override
	public Boolean matches(ItemStack item, FluidStack fluid) {
		FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(item);
		//Make sure that when we combine the liquids that there is enough room in the barrel for the new liquid to fit
		return recipeFluid != null
				&& recipeFluid.isFluidEqual(fluid)
				&& itemLiquid != null
				&& itemLiquid.isFluidEqual(inputfluid)
				&& 10000 - fluid.amount >= itemLiquid.amount;
	}

	@Override
	public Stack<ItemStack> getResult(ItemStack inIS, FluidStack inFS, int sealedTime) {
		Stack<ItemStack> result = new Stack<ItemStack>();
		if (inIS != null)
			result.push(inIS.getItem().getContainerItem(inIS));
		else
			result.push(null);

		return result;
	}

	@Override
	public FluidStack getResultFluid(ItemStack inIS, FluidStack inFS, int sealedTime) {
		if (recipeOutFluid != null) {
			FluidStack fs = recipeOutFluid.copy();
			FluidStack itemLiquid = FluidContainerRegistry.getFluidForFilledItem(inIS);
			if (!removesLiquid) {
				fs.amount = inFS.amount + itemLiquid.amount;
			} else {
				fs.amount = (fs.amount * inFS.amount) / recipeFluid.amount;
			}
			return fs;
		}
		return null;
	}

	public FluidStack getInputfluid() {
		return inputfluid;
	}
}
