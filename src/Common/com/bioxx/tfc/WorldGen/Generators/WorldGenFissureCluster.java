package com.bioxx.tfc.WorldGen.Generators;

import com.bioxx.tfc.api.TFCBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;

import java.util.Random;

public class WorldGenFissureCluster implements IWorldGenerator {
	@SuppressWarnings("FieldCanBeLocal")
	private final int waterRarity = 225;

	private final WorldGenFissure fissureGenWater = new WorldGenFissure(TFCBlocks.freshWater);
	private final WorldGenFissure fissureGenLava = new WorldGenFissure(TFCBlocks.lava);
	private final WorldGenFissure fissureGenAir = new WorldGenFissure(null);

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
	                     IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		chunkX *= 16;
		chunkZ *= 16;

		int startX = chunkX + random.nextInt(16) + 8;
		int startZ = chunkZ + random.nextInt(16) + 8;

		if (random.nextInt(waterRarity) == 0) {
			int num = 3 + random.nextInt(10);
			for (int i = 0; i < num; i++) {
				int x = startX - 30 + random.nextInt(60);
				int z = startZ - 30 + random.nextInt(60);
				int y = world.getTopSolidOrLiquidBlock(x, z) - 1;
				if (random.nextInt(10) == 0)
					fissureGenAir.generate(world, random, x, y, z);
				else
					fissureGenWater.generate(world, random, x, y, z);
			}
		} else if (random.nextInt(400) == 0) {
			int num = 3 + random.nextInt(10);
			for (int i = 0; i < num; i++) {
				int x = startX - 30 + random.nextInt(60);
				int z = startZ - 30 + random.nextInt(60);
				int y = world.getTopSolidOrLiquidBlock(x, z) - 1;

				if (random.nextInt(10) == 0)
					fissureGenAir.generate(world, random, x, y, z);
				else
					fissureGenLava.generate(world, random, x, y, z);
			}
		}
	}

}
