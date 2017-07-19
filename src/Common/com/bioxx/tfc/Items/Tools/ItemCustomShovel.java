package com.bioxx.tfc.Items.Tools;

import com.bioxx.tfc.Blocks.Terrain.Path.BaseBlockPath;
import com.bioxx.tfc.Blocks.Terrain.Path.BlockGravelPath;
import com.bioxx.tfc.Blocks.Terrain.Path.BlockSandPath;
import com.bioxx.tfc.Reference;
import com.bioxx.tfc.api.Enums.EnumItemReach;
import com.bioxx.tfc.api.TFCBlocks;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.Set;

public class ItemCustomShovel extends ItemTerraTool {
	/**
	 * an array of the blocks this spade is effective against
	 */
	private static final Set<Block> BLOCKS_EFFECTIVE_AGAINST = Sets.newHashSet(
			Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, Blocks.snow, Blocks.snow_layer,
			Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium,
			TFCBlocks.dirt, TFCBlocks.dirt2, TFCBlocks.grass, TFCBlocks.grass2, TFCBlocks.clayGrass,
			TFCBlocks.clayGrass2, TFCBlocks.peatGrass, TFCBlocks.peat, TFCBlocks.clay, TFCBlocks.clay2);

	public ItemCustomShovel(ToolMaterial par2EnumToolMaterial) {
		super(1.0F, par2EnumToolMaterial, BLOCKS_EFFECTIVE_AGAINST);
	}

	/**
	 * Returns if the item (tool) can harvest results from the block type.
	 */
	@Override
	public boolean func_150897_b/*canHarvestBlock*/(Block par1Block) {
		return par1Block == Blocks.snow_layer || par1Block == Blocks.snow;
	}

	@Override
	public void registerIcons(IIconRegister registerer) {
		String name = this.getUnlocalizedName().replace("item.", "");
		name = name.replace("IgIn ", "");
		name = name.replace("IgEx ", "");
		name = name.replace("Sed ", "");
		name = name.replace("MM ", "");
		this.itemIcon = registerer.registerIcon(Reference.MOD_ID + ":" + "tools/" + name);
	}

	@Override
	public EnumItemReach getReach(ItemStack is) {
		return EnumItemReach.FAR;
	}

	private boolean tryMakeRoadPath(World world, int x, int y, int z, int side, Block id2, int meta) {
		Block update = null;

		if (id2 == TFCBlocks.gravel) {
			update = TFCBlocks.pathGravel;
		} else if (id2 == TFCBlocks.gravel2) {
			update = TFCBlocks.pathGravel2;
		} else if (id2 == TFCBlocks.sand) {
			update = TFCBlocks.pathSand;
		} else if (id2 == TFCBlocks.sand2) {
			update = TFCBlocks.pathSand2;
		}

		if (update != null) {
			world.setBlock(x, y, z, update, meta, 0x2);
			return true;
		}

		return false;
	}

	//From Tinkers Construct Harvest tool class. Thanks you geniuses. Allows place item/block from next slot in hotbar.
	@Override
	public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		Block id2 = player.worldObj.getBlock(x, y, z);
		int meta2 = player.worldObj.getBlockMetadata(x, y, z);

		if (side == 1 && tryMakeRoadPath(world, x, y, z, side, id2, meta2)) {
			world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, id2.stepSound.getStepResourcePath(), 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
			stack.damageItem(5, player);
			return true;
		}

		// to avoid missplacement of blocks on roads
		if (id2 instanceof BaseBlockPath)
			return true;

		boolean placed = false;
		int toolSlot = player.inventory.currentItem;
		int nextSlot = toolSlot == 0 ? 8 : toolSlot + 1;

		if (toolSlot < 8) {
			ItemStack nextSlotStack = player.inventory.getStackInSlot(nextSlot);
			if (nextSlotStack != null) {
				Item item = nextSlotStack.getItem();

				if (item instanceof ItemBlock) {
					int posX = x;
					int posY = y;
					int posZ = z;

					switch (side) {
						case 0:
							--posY;
							break;
						case 1:
							++posY;
							break;
						case 2:
							--posZ;
							break;
						case 3:
							++posZ;
							break;
						case 4:
							--posX;
							break;
						case 5:
							++posX;
							break;
					}

					AxisAlignedBB blockBounds = AxisAlignedBB.getBoundingBox(posX, posY, posZ, posX + 1, posY + 1, posZ + 1);
					AxisAlignedBB playerBounds = player.boundingBox;

					Block blockToPlace = ((ItemBlock) item).field_150939_a;
					if (blockToPlace.getMaterial().blocksMovement()) {
						if (playerBounds.intersectsWith(blockBounds))
							return false;
					}

					int dmg = nextSlotStack.getItemDamage();
					int count = nextSlotStack.stackSize;

					placed = item.onItemUse(nextSlotStack, player, world, x, y, z, side, hitX, hitY, hitZ);

					if (player.capabilities.isCreativeMode) {

						nextSlotStack.setItemDamage(dmg);
						nextSlotStack.stackSize = count;
					}
					if (nextSlotStack.stackSize < 1) {
						player.inventory.setInventorySlotContents(nextSlot, null);
					}
				}
			}
		}
		return placed;
	}
}
