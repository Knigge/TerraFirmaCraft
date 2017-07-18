package com.bioxx.tfc.Blocks;

import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Reference;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockCeramicBricks extends BlockTerra {
	protected IIcon[] icons;

	public BlockCeramicBricks() {
		super(Material.rock);
		this.setCreativeTab(TFCTabs.TFC_BUILDING);
		icons = new IIcon[8];
	}

	@SideOnly(Side.CLIENT)
	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta > 7) meta = 0;
		return icons[meta];
	}

	@Override
	public void registerBlockIcons(IIconRegister registerer) {
		for (int i = 0; i < 8; i++)
			icons[i] = registerer.registerIcon(Reference.MOD_ID + ":" + "bricks/brick_" + Integer.toString(i));
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack is) {
		world.setBlockMetadataWithNotify(x, y, z, world.rand.nextInt(7), 3);
	}
}
