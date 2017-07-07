package com.bioxx.tfc.Render.Models;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import com.bioxx.tfc.Entities.Mobs.EntitySkeletonTFC;

@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
@SideOnly(Side.CLIENT)
public class ModelSkeletonTFC extends ModelZombie
{
	public ModelSkeletonTFC()
	{
		this(0.0F);
	}

	public ModelSkeletonTFC(float par1)
	{
		super(par1, 0.0F, 64, 32);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, par1);
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
		this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, par1);
		this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
	}

	/**
	 * Used for easily adding entity-dependent animations. The second and third float params here are the same second
	 * and third as in the setRotationAngles method.
	 */
	@Override
	public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4)
	{
		this.aimedBow = ((EntitySkeletonTFC)par1EntityLivingBase).getSkeletonType() == 1;
		super.setLivingAnimations(par1EntityLivingBase, par2, par3, par4);
	}

}
