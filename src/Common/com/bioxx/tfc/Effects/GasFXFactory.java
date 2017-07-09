package com.bioxx.tfc.Effects;

import com.bioxx.tfc.api.TFCOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import java.util.Random;

public class GasFXFactory {
	private World world;
	private double x;
	private double y;
	private double z;

	public GasFXFactory(World _world, double _x, double _y, double _z) {
		this.world = _world;
		this.x = _x;
		this.y = _y;
		this.z = _z;
	}

	public void createEffect(double motionX, double motionY, double motionZ) {
		if (TFCOptions.gasFXEnabled)
		Minecraft.getMinecraft().effectRenderer.addEffect(
				new GasFX(
						this.world,
						this.x, this.y, this.z,
						motionX, motionY, motionZ
				)
		);
	}

	public void createEffect(Random random, float multi) {
		this.createEffect(
				random.nextFloat() * multi,
				random.nextFloat() * multi,
				random.nextFloat() * multi
		);
	}

	public void createEffect(Random random) {
		createEffect(random, -0.1F);
	}
}
