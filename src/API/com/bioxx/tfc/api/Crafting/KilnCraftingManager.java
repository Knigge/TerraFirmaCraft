package com.bioxx.tfc.api.Crafting;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.ItemStack;

@SuppressWarnings({"CanBeFinal", "Convert2Diamond"})
public class KilnCraftingManager
{
	private static final KilnCraftingManager INSTANCE = new KilnCraftingManager();
	public static KilnCraftingManager getInstance()
	{
		return INSTANCE;
	}

	private List<KilnRecipe> recipes;

	private KilnCraftingManager()
	{
		recipes = new ArrayList<KilnRecipe>();
	}

	public void addRecipe(KilnRecipe recipe)
	{
		recipes.add(recipe);
	}

	public KilnRecipe findMatchingRecipe(KilnRecipe recipe)
	{
		for (KilnRecipe irecipe : recipes) {
			if (irecipe.matches(recipe)) {
				return irecipe;
			}
		}

		return null;
	}

	public ItemStack findCompleteRecipe(KilnRecipe recipe)
	{
		for (KilnRecipe irecipe : recipes) {
			if (irecipe.isComplete(recipe)) {
				ItemStack out = irecipe.getCraftingResult();
				if (irecipe.getInheritsTag())
					out.setTagCompound(recipe.input1.getTagCompound());
				return out;
			}
		}

		return recipe.input1;
	}

	public List<KilnRecipe> getRecipeList()
	{
		return recipes;
	}
}
