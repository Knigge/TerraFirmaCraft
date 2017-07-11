package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Blocks.Terrain.BlockOre;
import com.bioxx.tfc.Blocks.Terrain.BlockOre2;
import com.bioxx.tfc.Core.TFC_Climate;
import com.bioxx.tfc.TileEntities.TEWorldItem;
import com.bioxx.tfc.WorldGen.DataLayer;
import com.bioxx.tfc.WorldGen.TFCBiome;
import com.bioxx.tfc.api.TFCBlocks;
import com.bioxx.tfc.api.TFCItems;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("Convert2Diamond")
public class WorldGenLooseRocks implements IWorldGenerator {
	public WorldGenLooseRocks() {
	}

	public static boolean rocksNearby(World world, int i, int j, int k) {
		return (world.getBlock(i + 1, j + 1, k) != TFCBlocks.worldItem) ||
				(world.getBlock(i + 1, j + 1, k + 1) != TFCBlocks.worldItem) ||
				(world.getBlock(i, j + 1, k + 1) != TFCBlocks.worldItem) ||
				(world.getBlock(i - 1, j + 1, k) != TFCBlocks.worldItem) ||
				(world.getBlock(i - 1, j + 1, k + 1) != TFCBlocks.worldItem) ||
				(world.getBlock(i - 1, j + 1, k - 1) != TFCBlocks.worldItem) ||
				(world.getBlock(i, j + 1, k - 1) != TFCBlocks.worldItem) ||
				(world.getBlock(i + 1, j + 1, k) != TFCBlocks.worldItem);

	}

	private boolean isNearTree(World world, int i, int j, int k) {
		return  world.getBlock(i, j + 3, k).getMaterial() == Material.leaves ||
				world.getBlock(i + 5, j + 3, k).getMaterial() == Material.leaves ||
				world.getBlock(i - 5, j + 3, k).getMaterial() == Material.leaves ||
				world.getBlock(i, j + 3, k + 5).getMaterial() == Material.leaves ||
				world.getBlock(i, j + 3, k - 5).getMaterial() == Material.leaves ||
				world.getBlock(i, j + 6, k).getMaterial() == Material.leaves ||
				world.getBlock(i + 5, j + 6, k).getMaterial() == Material.leaves ||
				world.getBlock(i - 5, j + 6, k).getMaterial() == Material.leaves ||
				world.getBlock(i, j + 6, k + 5).getMaterial() == Material.leaves ||
				world.getBlock(i, j + 6, k - 5).getMaterial() == Material.leaves;
	}

	public boolean canSpawnHere(World world, int i, int j, int k, boolean sticks) {
		Block u = world.getBlock(i, j + 1, k);
		if (u == Blocks.air || u == Blocks.snow || u == TFCBlocks.tallGrass) {
			Block b = world.getBlock(i, j, k);
			if (b.isOpaqueCube()) {
				if (b.getMaterial() == Material.grass || b.getMaterial() == Material.rock) {
					return true;
				} else if (sticks && (b.getMaterial() == Material.sand || b.getMaterial() == Material.ground)) {
					return true;
				}
			}
		}

		return false;
	}

	private ArrayList<ItemStack> getCoreSampleAll(World world, int xCoord, int yCoord, int zCoord) {
		ArrayList<Item> coreSample = new ArrayList<Item>();
		ArrayList<ItemStack> coreSampleStacks = new ArrayList<ItemStack>();
		for (int x = -15; x < 16; x++) {
			for (int z = -15; z < 16; z++) {
				for (int y = yCoord; y > yCoord - 35; y--) {
					if (world.blockExists(xCoord + x, y, zCoord + z) &&
							(world.getBlock(xCoord + x, y, zCoord + z) == TFCBlocks.ore)) {
						int m = world.getBlockMetadata(xCoord + x, y, zCoord + z);
						if (!coreSample.contains(BlockOre.getDroppedItem(m))) {
							coreSample.add(BlockOre.getDroppedItem(m));
							coreSampleStacks.add(new ItemStack(BlockOre.getDroppedItem(m), 1, m));
						}
					} else if (world.blockExists(xCoord + x, y, zCoord + z) &&
							(world.getBlock(xCoord + x, y, zCoord + z) == TFCBlocks.ore2)) {
						int m = world.getBlockMetadata(xCoord + x, y, zCoord + z);
						if (!coreSample.contains(BlockOre2.getDroppedItem(m))) {
							coreSample.add(BlockOre2.getDroppedItem(m));
							coreSampleStacks.add(new ItemStack(BlockOre2.getDroppedItem(m + 16), 1, m + 16));
						}
					}
				}
			}
		}

		return coreSampleStacks;
	}

	private ItemStack getCoreSample(World world, int xCoord, int yCoord, int zCoord) {
		ArrayList<ItemStack> coreSampleStacks = getCoreSampleAll(world, xCoord, yCoord, zCoord);
		if (!coreSampleStacks.isEmpty())
			return coreSampleStacks.get(world.rand.nextInt(coreSampleStacks.size()));

		return null;
	}

	public void generateRocks(World world, Random random, int i, int j, int k, ItemStack sample, boolean genSample) {
		if (canSpawnHere(world, i, j, k, false)) {
			if (world.setBlock(i, j + 1, k, TFCBlocks.worldItem, 0, 2)) {
				TEWorldItem te = (TEWorldItem) world.getTileEntity(i, j + 1, k);
				if (genSample)
					sample = getCoreSample(world, i, j, k);
				if (world.rand.nextInt(3) == 0 && sample != null) {
					te.storage[0] = sample;
				} else {
					DataLayer dl = TFC_Climate.getRockLayer(world, i, j, k, 0);
					te.storage[0] = new ItemStack(TFCItems.looseRock, 1, dl.data1);
				}
			}
		}
	}

	public void generateRocks(World world, Random random, int i, int j, int k, ItemStack sample) {
		generateRocks(world, random, i, j, k, sample, false);
	}

	public void generateRocks(World world, Random random, int i, int j, int k) {
		generateRocks(world, random, i, j, k, null, true);
	}

	public void generateSticks(World world, Random random, int i, int j, int k) {
		if (canSpawnHere(world, i, j, k, true)) {
			if (world.getBiomeGenForCoords(i, k) instanceof TFCBiome) // Fixes ClassCastException
			{
				TFCBiome biome = (TFCBiome) world.getBiomeGenForCoords(i, k);
				if ((biome == TFCBiome.BEACH || biome == TFCBiome.GRAVEL_BEACH
					 || isNearTree(world, i, j, k)) && world.setBlock(i, j + 1, k, TFCBlocks.worldItem, 0, 2))
				{
					TEWorldItem te = (TEWorldItem) world.getTileEntity(i, j + 1, k);
					te.storage[0] = new ItemStack(TFCItems.stick, 1);
					if (random.nextInt(8) == 0 && world.getBlock(i, j, k).getMaterial() == Material.ground) {
						te.storage[0] = new ItemStack(Items.flint, 1);
					}
				}
			}
		}
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
	                     IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		chunkX *= 16;
		chunkZ *= 16;

		if (world.getBiomeGenForCoords(chunkX, chunkZ) instanceof TFCBiome) // Fixes ClassCastException
		{
			TFCBiome biome = (TFCBiome) world.getBiomeGenForCoords(chunkX, chunkZ);
			if (biome == TFCBiome.OCEAN || biome == TFCBiome.DEEP_OCEAN)
				return;
		}

		//rocks/ore
		ArrayList<ItemStack> coreSampleStacks = getCoreSampleAll(world, chunkX + 8, world.getHeightValue(chunkX + 8, chunkZ + 8), chunkZ + 8);
		int samples = coreSampleStacks.size();

		for (int itemCount = 0; itemCount < 8; itemCount++) {
			int xCoord = chunkX + random.nextInt(16) + 8;
			int zCoord = chunkZ + random.nextInt(16) + 8;
			int yCoord = world.getTopSolidOrLiquidBlock(xCoord, zCoord) - 1;

			ItemStack sample = samples > 0 ? coreSampleStacks.get(random.nextInt(samples)) : null;
			generateRocks(world, random, xCoord, yCoord, zCoord, sample);
		}

		//sticks
		for (int stickCount = 0; stickCount < 8; stickCount++) {
			int xCoord = chunkX + random.nextInt(16) + 8;
			int zCoord = chunkZ + random.nextInt(16) + 8;
			int yCoord = world.getTopSolidOrLiquidBlock(xCoord, zCoord) - 1;
			generateSticks(world, random, xCoord, yCoord, zCoord);
		}
	}
}
