package com.bioxx.tfc.api.Crafting;

import com.bioxx.tfc.TileEntities.TEBarrel;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"CanBeFinal", "Convert2Diamond"})
public class BarrelManager {
	private static final BarrelManager INSTANCE = new BarrelManager();
	private List<BarrelRecipe> recipes;
	private List<BarrelPreservativeRecipe> preservativeRecipes;
	private BarrelManager() {
		recipes = new ArrayList<BarrelRecipe>();
		preservativeRecipes = new ArrayList<BarrelPreservativeRecipe>();
	}

	public static BarrelManager getInstance() {
		return INSTANCE;
	}

	public void addRecipe(BarrelRecipe recipe) {
		recipes.add(recipe);
	}

	public void addPreservative(BarrelPreservativeRecipe recipe) {
		preservativeRecipes.add(recipe);
	}

	public BarrelRecipe findMatchingRecipe(ItemStack item, FluidStack fluid, boolean sealed, int techLevel) {
		for (Object recipe : recipes) {
			BarrelRecipe br = (BarrelRecipe) recipe;
			if (/*item != null && */fluid != null &&/*(br.inItemStack != null && item != null) && (br.inFluid != null && fluid != null) &&*/ br.matches(item, fluid))
				if (br.sealedRecipe == sealed && br.minTechLevel <= techLevel)
					return br;
		}
		return null;
	}

	public BarrelPreservativeRecipe findMatchingPreservativeRepice(TEBarrel barrel, ItemStack item, FluidStack fluid, boolean sealed) {
		for (BarrelPreservativeRecipe recipe : preservativeRecipes) {
			if (recipe.checkForPreservation(barrel, fluid, item, sealed))
				return recipe;
		}
		return null;
	}

	public List<BarrelRecipe> getRecipes() {
		return recipes;
	}

	public List<BarrelPreservativeRecipe> getPreservatives() {
		return preservativeRecipes;
	}


}
