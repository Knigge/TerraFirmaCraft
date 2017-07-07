package com.bioxx.tfc.WorldGen.Generators;

import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

@SuppressWarnings("WeakerAccess")
public class WorldGenMinableTFC extends WorldGenerator {
	public static Block mPBlock;
	public static int mPBlockMeta;
	public static Block mPlayerBlock;
	public static int mPlayerMeta;
	//private int BiomeId;
	public static int mPPrevX;
	public static int mPPrevZ;
	public static Block mPPrevBlock;
	public static int mPPrevMeta;
	public static Block mPPrevLayerBlock;
	public static int mPPrevLayerMeta;
	private final Block minableBlock;
	private final int minableBlockMeta;
	private Block layerBlock;
	private int layerMeta;
	private int mPChunkX;
	private int mPChunkZ;
	private World worldObj;

	private int rarity = 2;
	private int veinSi = 2;
	private int veinAm = 2;
	private int height = 2;
	private int diameter = 2;
	private int vDens = 2;
	private int hDens = 2;

	//private String name;

	public WorldGenMinableTFC(Block block, int j) {
		//int emptyHolder = 0;
		minableBlock = block;
		minableBlockMeta = 0;
		//emptyHolder = j;
		//BiomeId = -1;
	}

	public WorldGenMinableTFC(Block block, int j, Block layerB, int layerMeta, int rarity, int veinSize,
	                          int veinAmount, int height, int diameter, int vDensity, int hDensity) {
		/*int emptyHolder = 0;
		emptyHolder = j;*/

		this.minableBlock = block;
		this.minableBlockMeta = j;
		this.layerBlock = layerB;
		this.layerMeta = layerMeta;
		this.rarity = rarity;
		this.veinSi = veinSize;
		this.veinAm = veinAmount;
		this.height = height;
		this.diameter = diameter;
		this.vDens = vDensity;
		this.hDens = hDensity;
	}

	public void betterOreDistribution(int xChunk, int zChunk, Block mPMinableBlock, int mPMinableBlockMeta, int min, int max, Random rand) {
		if (rand.nextInt(rarity) == 0) {
			for (int loopCount = 0; loopCount < veinAm; loopCount++) {
				int temp1 = mPCalculateDensity(rand, diameter, hDens);
				int temp2 = mPCalculateDensityVert(rand, height, vDens, min, max);
				int temp3 = mPCalculateDensity(rand, diameter, hDens);
				int l5 = xChunk * 16 + temp1;
				int k13 = zChunk * 16 + temp3;
				bODgenerate(worldObj, rand, l5, temp2, k13, veinSi);
			}
		}
	}

	public void betterOreDistributionVein(int xChunk, int zChunk, Block mPMinableBlock, int mPMinableBlockMeta, int min, int max, Random rand) {
		if (rand.nextInt(rarity) == 0) {
			for (int loopCount = 0; loopCount < veinAm; loopCount++) {
				int temp1 = mPCalculateDensity(rand, diameter, hDens);
				int temp2 = mPCalculateDensityVert(rand, height, vDens, min, max);
				int temp3 = mPCalculateDensity(rand, diameter, hDens);
				int l5 = xChunk * 16 + temp1;
				int k13 = zChunk * 16 + temp3;
				bODgenerateVein(worldObj, rand, l5, temp2, k13, veinSi);
			}
		}
	}

	public void bODgenerateVein(World world, Random rand, int parX, int parY, int parZ, int xyz) {
		//boolean doOnce = true;

		//==========================================mp mod
		int posX = parX;
		int posY = parY;
		int posZ = parZ;
		/*int tempPosX =0;
		int tempPosY =0;
		int tempPosZ =0;*/
		int posX2 = 0;
		int posY2 = 0;
		int posZ2 = 0;
		int directionX;
		int directionY;
		int directionZ;
		int directionX2;
		int directionY2;
		int directionZ2;
		/*int directionX3 =0;
		int directionY3 =0;
		int directionZ3 =0;*/
		int directionChange;
		int directionChange2;
		int blocksToUse2;

		for (int blocksMade = 0; blocksMade <= xyz; ) // make veins
		{
			blocksToUse2 = 1 + (xyz / 30);
			directionChange = rand.nextInt(6);
			directionX = rand.nextInt(2);
			directionY = rand.nextInt(2);
			directionZ = rand.nextInt(2);

			for (int blocksMade1 = 0; blocksMade1 <= blocksToUse2; ) // make branch
			{
				if (directionX == 0 && directionChange != 1) {
					posX = posX + rand.nextInt(2);
				}
				if (directionX == 1 && directionChange != 1) {
					posX = posX - rand.nextInt(2);
				}
				if (directionY == 0 && directionChange != 2) {
					posY = posY + rand.nextInt(2);
				}
				if (directionY == 1 && directionChange != 2) {
					posY = posY - rand.nextInt(2);
				}
				if (directionZ == 0 && directionChange != 3) {
					posZ = posZ + rand.nextInt(2);
				}
				if (directionZ == 1 && directionChange != 3) {
					posZ = posZ - rand.nextInt(2);
				}
				if (rand.nextInt(4) == 0) {
					posX2 = posX2 + rand.nextInt(2);
					posY2 = posY2 + rand.nextInt(2);
					posZ2 = posZ2 + rand.nextInt(2);
					posX2 = posX2 - rand.nextInt(2);
					posY2 = posY2 - rand.nextInt(2);
					posZ2 = posZ2 - rand.nextInt(2);
				}
				if (rand.nextInt(3) == 0) // make sub-branch
				{
					posX2 = posX;
					posY2 = posY;
					posZ2 = posZ;
					directionX2 = rand.nextInt(2);
					directionY2 = rand.nextInt(2);
					directionZ2 = rand.nextInt(2);
					directionChange2 = rand.nextInt(6);
					if (directionX2 == 0 && directionChange2 != 0) {
						posX2 = posX2 + rand.nextInt(2);
					}
					if (directionY2 == 0 && directionChange2 != 1) {
						posY2 = posY2 + rand.nextInt(2);
					}
					if (directionZ2 == 0 && directionChange2 != 2) {
						posZ2 = posZ2 + rand.nextInt(2);
					}
					if (directionX2 == 1 && directionChange2 != 0) {
						posX2 = posX2 - rand.nextInt(2);
					}
					if (directionY2 == 1 && directionChange2 != 1) {
						posY2 = posY2 - rand.nextInt(2);
					}
					if (directionZ2 == 1 && directionChange2 != 2) {
						posZ2 = posZ2 - rand.nextInt(2);
					}

					for (int blocksMade2 = 0; blocksMade2 <= (1 + (blocksToUse2 / 5)); ) {
						if (directionX2 == 0 && directionChange2 != 0) {
							posX2 = posX2 + rand.nextInt(2);
						}
						if (directionY2 == 0 && directionChange2 != 1) {
							posY2 = posY2 + rand.nextInt(2);
						}
						if (directionZ2 == 0 && directionChange2 != 2) {
							posZ2 = posZ2 + rand.nextInt(2);
						}
						if (directionX2 == 1 && directionChange2 != 0) {
							posX2 = posX2 - rand.nextInt(2);
						}
						if (directionY2 == 1 && directionChange2 != 1) {
							posY2 = posY2 - rand.nextInt(2);
						}
						if (directionZ2 == 1 && directionChange2 != 2) {
							posZ2 = posZ2 - rand.nextInt(2);
						}
						int m = world.getBlockMetadata(posX, posY, posZ);
						boolean isCorrectRockType = world.getBlock(posX, posY, posZ) == layerBlock;
						boolean isCorrectMeta = m == layerMeta || layerMeta == -1;
						if (isCorrectRockType && isCorrectMeta)
							world.setBlock(posX, posY, posZ, mPBlock, mPBlockMeta, 0x2);
						blocksMade++;
						blocksMade1++;
						blocksMade2++;
					}
				}

				int m = world.getBlockMetadata(posX, posY, posZ);
				boolean isCorrectRockType = world.getBlock(posX, posY, posZ) == layerBlock;
				boolean isCorrectMeta = m == layerMeta || layerMeta == -1;
				if (isCorrectRockType && isCorrectMeta)
					world.setBlock(posX, posY, posZ, mPBlock, mPBlockMeta, 0x2);
				blocksMade++;
				blocksMade1++;
			}

			parX = parX + (rand.nextInt(3) - 1);
			parY = parY + (rand.nextInt(3) - 1);
			parZ = parZ + (rand.nextInt(3) - 1);
			posX = parX;
			posY = parY;
			posZ = parZ;
		}
	}

	public void bODgenerate(World world, Random rand, int x, int y, int z, int xyz) {
		//boolean doOnce = true;
		float f = rand.nextFloat() * (float) Math.PI;
		double d = x + 8 + MathHelper.sin(f) * xyz / 8F;
		double d1 = x + 8 - MathHelper.sin(f) * xyz / 8F;
		double d2 = z + 8 + MathHelper.cos(f) * xyz / 8F;
		double d3 = z + 8 - MathHelper.cos(f) * xyz / 8F;
		double d4 = y + rand.nextInt(3) - 2;
		double d5 = y + rand.nextInt(3) - 2;

		for (int l = 0; l <= xyz; l++) {
			double d6 = d + (d1 - d) * l / xyz;
			double d7 = d4 + (d5 - d4) * l / xyz;
			double d8 = d2 + (d3 - d2) * l / xyz;
			double d9 = rand.nextDouble() * xyz / 16D;
			double d10 = (MathHelper.sin(l * (float) Math.PI / xyz) + 1.0F) * d9 + 1.0D;
			double d11 = (MathHelper.sin(l * (float) Math.PI / xyz) + 1.0F) * d9 + 1.0D;
			int i1 = MathHelper.floor_double(d6 - d10 / 2D);
			int j1 = MathHelper.floor_double(d7 - d11 / 2D);
			int k1 = MathHelper.floor_double(d8 - d10 / 2D);
			int l1 = MathHelper.floor_double(d6 + d10 / 2D);
			int i2 = MathHelper.floor_double(d7 + d11 / 2D);
			int j2 = MathHelper.floor_double(d8 + d10 / 2D);

			for (int xCoord = i1; xCoord <= l1; xCoord++) {
				double d12 = (xCoord + 0.5D - d6) / (d10 / 2D);
				if (d12 * d12 >= 1.0D)
					continue;
				for (int yCoord = j1; yCoord <= i2; yCoord++) {
					double d13 = (yCoord + 0.5D - d7) / (d11 / 2D);
					if (d12 * d12 + d13 * d13 >= 1.0D)
						continue;
					for (int zCoord = k1; zCoord <= j2; zCoord++) {
						double d14 = (zCoord + 0.5D - d8) / (d10 / 2D);
						int m = world.getBlockMetadata(xCoord, yCoord, zCoord);
						boolean isCorrectRockType = world.getBlock(xCoord, yCoord, zCoord) == layerBlock;
						boolean isCorrectMeta = m == layerMeta || layerMeta == -1;
						if (d12 * d12 + d13 * d13 + d14 * d14 < 1.0D && isCorrectRockType && isCorrectMeta)
							world.setBlock(xCoord, yCoord, zCoord, mPBlock, mPBlockMeta, 0x2);
					}
				}
			}
		}
	}

	@SuppressWarnings("SameReturnValue")
	public boolean generate(World world, Random random, int x, int z, int min, int max, String n)//absorb default system
	{
		mPChunkX = x >> 4;// set output chunk x
		mPChunkZ = z >> 4;// set output chunk z
		mPBlock = minableBlock;// set output block ID
		mPBlockMeta = minableBlockMeta;
		worldObj = world;
		//rand = random;
		if (mPChunkX != mPPrevX || mPChunkZ != mPPrevZ || mPBlock != mPPrevBlock ||
				(mPBlock == mPPrevBlock && mPBlockMeta != mPPrevMeta) || // mPBlock == mPPrevBlock is always true, i add () to the statement
				mPlayerBlock != mPPrevLayerBlock || mPlayerMeta != mPPrevLayerMeta)// if it is a new x or y chunk or is a new ore, then generate
		{
			mPPrevX = mPChunkX;
			mPPrevZ = mPChunkZ;
			mPPrevBlock = mPBlock;
			mPPrevMeta = mPBlockMeta;
			//this.name = n;
			betterOreDistribution(mPChunkX, mPChunkZ, mPBlock, mPBlockMeta, min, max, random);
		}
		return true;
	}

	@SuppressWarnings("SameReturnValue")
	public boolean generateVein(World world, Random random, int x, int z, int min, int max, String n)//absorb default system
	{
		mPChunkX = x >> 4;// set output chunk x
		mPChunkZ = z >> 4;// set output chunk z
		mPBlock = minableBlock;// set output block ID
		mPBlockMeta = minableBlockMeta;
		worldObj = world;
		//rand = random;
		if (mPChunkX != mPPrevX || mPChunkZ != mPPrevZ || mPBlock != mPPrevBlock ||
				//!TODO: check
				mPBlock == mPPrevBlock && mPBlockMeta != mPPrevMeta ||
				mPlayerBlock != mPPrevLayerBlock || mPlayerMeta != mPPrevLayerMeta)// if it is a new x or y chunk or is a new ore, then generate
		{
			mPPrevX = mPChunkX;
			mPPrevZ = mPChunkZ;
			mPPrevBlock = mPBlock;
			mPPrevMeta = mPBlockMeta;
			//this.name = n;
			betterOreDistributionVein(mPChunkX, mPChunkZ, mPBlock, mPBlockMeta, min, max, random);
		}
		return true;
	}

	@Override
	public boolean generate(World world, Random random, int i, int j, int k) {
		return true;
	}

	/*private boolean isRock(Block i)
	{
		if(i == TFCBlocks.StoneIgIn || i == TFCBlocks.StoneIgEx || 
				i == TFCBlocks.StoneMM || i == TFCBlocks.StoneSed ||
				i == TFCBlocks.Ore || i == TFCBlocks.Ore2 || 
				i == TFCBlocks.Ore3)
		{
			return true;
		}
		return false;
	}*/

	//======================================================================================
	public int mPCalculateDensity(Random rand, int oreDist, float oreDens) // returns the density value
	{
		oreDens = oreDens * .01F;
		oreDens = oreDens * (oreDist / 2) + 1F;// establishes number of times to loop
		int lpCnt = (int) oreDens; //stores number of times to loop
		int dValPassInr = (int) (oreDist / oreDens + .5F); // distance devided by number of times it will loop, establishes the number for randomization
		int dValPass = 0;
		while (lpCnt > 0) // loops to acumulate random values
		{
			dValPass = dValPass + rand.nextInt(dValPassInr); // acumulate randoms
			lpCnt--;// decriment loop
		}
		if (dValPass < 5) {
			dValPass = 5;
		}
		if (dValPass > 128) {
			dValPass = 128;
		}
		return dValPass; // return proccesed random value
	}

	//======================================================================================
	public int mPCalculateDensityVert(Random rand, int oreDist, float oreDens, int min, int max) // returns the density value
	{
		oreDens = oreDens * .01F;
		oreDens = oreDens * (oreDist / 2) + 1F;// establishes number of times to loop
		int lpCnt = (int) oreDens; //stores number of times to loop
		int dValPassInr = (int) (oreDist / oreDens + .5F); // distance devided by number of times it will loop, establishes the number for randomization
		int dValPass = min;
		while (lpCnt > 0) // loops to acumulate random values
		{
			dValPass = dValPass + rand.nextInt(dValPassInr); // acumulate randoms
			lpCnt--;// decriment loop
		}
		if (dValPass < min) {
			dValPass = min;
		}
		if (dValPass > max) {
			dValPass = max;
		}
		return dValPass; // return proccesed random value
	}
}
