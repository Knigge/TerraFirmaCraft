package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.Blocks.Flora.BlockFlower;
import com.bioxx.tfc.api.TFCBlocks;
import net.minecraft.world.World;

import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class WorldGenFlowers {
	public static void generate(World world, Random random, int chunkX, int chunkZ, int flowersPerChunk) {
		int flowerType = new Random(world.getSeed() + ((chunkX >> 7) - (chunkZ >> 7)) * (chunkZ >> 7)).nextInt(14);
		BlockFlower plantBlock = (BlockFlower) TFCBlocks.flowers;
		if (flowerType > 5) {
			plantBlock = (BlockFlower) TFCBlocks.flowers2;
			flowerType -= 5;
		}
		if (random.nextInt(flowersPerChunk) != 0)
			return;

		int xCoord = chunkX + random.nextInt(16);
		int zCoord = chunkZ + random.nextInt(16);
		int yCoord = world.getTopSolidOrLiquidBlock(xCoord, zCoord);
		for (int i = 0; i < flowersPerChunk; ++i) {
			int xx = xCoord - 4 + random.nextInt(8);
			int zz = zCoord - 4 + random.nextInt(8);

			if (world.isAirBlock(xx, yCoord, zz) && plantBlock.canBlockStay(world, xx, yCoord, zz)) {
				if (plantBlock.canGrowConditions(world, xx, yCoord, zz, flowerType))
					world.setBlock(xx, yCoord, zz, plantBlock, flowerType, 0x2);
			}
		}
	}

}
