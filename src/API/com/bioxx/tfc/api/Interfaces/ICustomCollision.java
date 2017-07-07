package com.bioxx.tfc.api.Interfaces;

import net.minecraft.world.World;

import java.util.List;

public interface ICustomCollision {
	void addCollisionBoxesToList(World world, int i, int j, int k, List boxlist);
}
