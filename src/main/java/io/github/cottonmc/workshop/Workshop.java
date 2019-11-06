package io.github.cottonmc.workshop;

import io.github.cottonmc.workshop.block.WorkshopBlocks;
import io.github.cottonmc.workshop.item.WorkshopItems;
import io.github.cottonmc.workshop.item.WorkshopMoldItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public class Workshop implements ModInitializer {
	public static final String MODID = "workshop";

	@Override
	public void onInitialize() {
		WorkshopItems.init();
		WorkshopBlocks.init();
		WorkshopMoldItems.init();
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
