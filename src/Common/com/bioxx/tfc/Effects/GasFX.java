package com.bioxx.tfc.Effects;

import com.bioxx.tfc.Core.TFC_Textures;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.world.World;

public class GasFX extends EntityFX {

	public GasFX(World world, double x, double y, double z,
	             double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		this.setParticleIcon(TFC_Textures.gasFXIcon);
		this.setSize(1f, 1f);
		this.particleMaxAge = (int) (12.0F / (this.rand.nextFloat() * 0.9F + 0.1F));
		this.particleAlpha = 0.05f;
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

}
