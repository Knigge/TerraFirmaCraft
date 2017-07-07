package com.bioxx.tfc.api.Crafting;

import net.minecraft.util.IIcon;

import com.bioxx.tfc.api.Enums.RuleEnum;

@SuppressWarnings({"WeakerAccess", "CanBeFinal"})
public class PlanRecipe
{
	public RuleEnum[] rules;
	public IIcon icon;

	public PlanRecipe(RuleEnum[] r, IIcon i)
	{
		rules = r.clone();
		icon = i;
	}

	public PlanRecipe(RuleEnum[] r)
	{
		rules = r.clone();
	}
}
