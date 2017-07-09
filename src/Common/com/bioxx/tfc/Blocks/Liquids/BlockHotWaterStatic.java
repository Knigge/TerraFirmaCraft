package com.bioxx.tfc.Blocks.Liquids;

import com.bioxx.tfc.Effects.GasFXFactory;
import com.bioxx.tfc.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;

import java.util.Random;

@SuppressWarnings("SameParameterValue")
public class BlockHotWaterStatic extends BlockLiquidStatic {
	public BlockHotWaterStatic(Fluid fluid, Material m, Block f) {
		super(fluid, m, f);
	}

	@Override
	public int colorMultiplier(IBlockAccess access, int x, int y, int z) {
		return this.fluidType.getColor();
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void randomDisplayTick(World world, int i, int j, int k, Random random) {
		if (world.isAirBlock(i - 1, j, k) || world.isAirBlock(i + 1, j, k) ||
				world.isAirBlock(i, j, k - 1) || world.isAirBlock(i, j, k + 1) ||
				world.isAirBlock(i, j + 1, k))
		{
			GasFXFactory fx = new GasFXFactory(world, i + 0.5F, j + 1F, k + 0.5F);

			fx.createEffect(random, -0.1F);
			fx.createEffect(random, -0.1F);
			fx.createEffect(random, -0.1F);
			fx.createEffect(random, -0.1F);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister registerer) {
		icons = new IIcon[]{registerer.registerIcon(Reference.MOD_ID + ":" + "water_still"), registerer.registerIcon(Reference.MOD_ID + ":" + "water_flow")};
		this.getFluid().setIcons(icons[0], icons[1]);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side != 0 && side != 1 ? this.icons[1] : this.icons[0];
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
		if (entity instanceof EntityLivingBase) {
			EntityLivingBase e = ((EntityLivingBase) entity);
			if (world.rand.nextInt(25) == 0 && e.getHealth() < e.getMaxHealth()) {
				if (!e.isPotionActive(Potion.regeneration) || !e.isPotionActive(Potion.heal)) {
					float diff = e.getMaxHealth() - e.getHealth();
					e.heal(Math.max(diff * 0.001f, 0.0001f));
				}
			}
		}
	}
}
