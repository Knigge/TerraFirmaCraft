package com.bioxx.tfc.Handlers;

import com.bioxx.tfc.WorldGen.BiomeDecoratorTFC;
import com.bioxx.tfc.WorldGen.TFCBiome;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.terraingen.BiomeEvent;

public class BiomeEventHandler {
	@SubscribeEvent
	public void onCreateDecorator(BiomeEvent.CreateDecorator event) {
		event.newBiomeDecorator = new BiomeDecoratorTFC((TFCBiome) event.biome);
	}
}
