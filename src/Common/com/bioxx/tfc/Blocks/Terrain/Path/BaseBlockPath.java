package com.bioxx.tfc.Blocks.Terrain.Path;

import com.bioxx.tfc.Blocks.BlockTerra;
import com.bioxx.tfc.Blocks.Terrain.BlockCollapsible;
import com.bioxx.tfc.Core.TFCTabs;
import com.bioxx.tfc.Reference;
import com.bioxx.tfc.api.Constant.Global;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.List;
import java.util.Random;

abstract public class BaseBlockPath extends BlockTerra {
	protected String pathType = "";
	protected Block baseBlock = null;
	protected boolean isShifted = false;

	protected IIcon[] icons;
	private float speedMulti = 2F;
	private float speedMax = 2F;

	protected BaseBlockPath(Material material) {
		super(material);

		this.setCreativeTab(TFCTabs.TFC_BUILDING);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1F, 15F / 16F, 1F);
		this.setLightOpacity(255);
		this.useNeighborBrightness = true;
		this.setHarvestLevel("shovel", 0);

		icons = new IIcon[(this.isShifted) ? Global.STONE_ALL.length - 16 : 16];
	}

	@SuppressWarnings("unchecked")
	@SideOnly(Side.CLIENT)
	@Override
	public void getSubBlocks(Item item, CreativeTabs tabs, List list) {
		int count = (this.isShifted) ? Global.STONE_ALL.length - 16 : 16;

		for (int i = 0; i < count; i++)
			list.add(new ItemStack(item, 1, i));
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		return AxisAlignedBB.getBoundingBox(x, y, z, x + 1, y + (15F / 16F), z + 1);
	}

	@Override
	public boolean canBeReplacedByLeaves(IBlockAccess w, int x, int y, int z) {
		return false;
	}

	@Override
	public boolean isSideSolid(IBlockAccess world, int x, int y, int z, ForgeDirection side) {
		return side == ForgeDirection.DOWN;
	}

	@Override
	public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
		if (Math.abs(entity.motionX) < speedMax)
			entity.motionX *= speedMulti;
		if (Math.abs(entity.motionY) < speedMax)
			entity.motionZ *= speedMulti;
	}

	@Override
	public Item getItemDropped(int metadata, Random rand, int fortune) {
		return this.getBaseBlockAsItem(metadata, rand, fortune);
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block) {
		Block above = world.getBlock(x, y + 1, z);

		if ((above != Blocks.air && above.isOpaqueCube()) || above instanceof BaseBlockPath)
			convertToBaseBlock(world, x, y, z);

		BlockCollapsible.tryToFall(world, x, y, z, this);
	}

	@Override
	public void registerBlockIcons(IIconRegister registerer) {
		if (this.pathType.isEmpty())
			throw new IllegalStateException("pathType is not set. Check your code!");

		int count = (this.isShifted) ? Global.STONE_ALL.length - 16 : 16;
		for (int i = 0; i < count; i++) {
			int index = (this.isShifted) ? i + 16 : i;
			icons[i] = registerer.registerIcon(Reference.MOD_ID + ":" + "path/" + this.pathType + "/" + Global.STONE_ALL[index]);
		}
	}

	@Override
	public IIcon getIcon(int side, int meta) {
		if (meta >= icons.length)
			return icons[icons.length - 1];

		return icons[meta];
	}

	@Override
	public IIcon getIcon(IBlockAccess bAccess, int x, int y, int z, int side) {
		int meta = bAccess.getBlockMetadata(x, y, z);

		return getIcon(side, meta);
	}

	protected void convertToBaseBlock(World world, int x, int y, int z) {
		if (this.baseBlock == null)
			throw new IllegalStateException("baseBlock is not set. Check your code!");

		int metadata = world.getBlockMetadata(x, y, z);
		world.setBlock(x, y, z, this.baseBlock, metadata, 0x2);
	}

	public Item getBaseBlockAsItem(int metadata, Random rand, int fortune) {
		return this.baseBlock.getItemDropped(metadata, rand, fortune);
	}
}
