package io.github.cottonmc.workshop;

import io.github.cottonmc.workshop.binding.BindingTypes;
import io.github.cottonmc.workshop.block.WorkshopBlocks;
import io.github.cottonmc.workshop.controller.ControllerProviders;
import io.github.cottonmc.workshop.item.WorkshopItems;
import io.github.cottonmc.workshop.item.WorkshopMoldItems;
import io.github.cottonmc.workshop.item.mold.MoldTypes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.util.Identifier;

public class Workshop implements ModInitializer {
	public static final String MODID = "workshop";
	private static boolean lock;

    public static boolean isLocked() {
    	return lock;
    }

    @Override
	public void onInitialize() {
		MoldTypes.init();
		BindingTypes.init();

		WorkshopItems.init();
		WorkshopMoldItems.init();
		WorkshopBlocks.init();
		ControllerProviders.init();

		// We cannot let new MoldTypes or BindingTypes be registered once the Client or Server pushes it's first tick.
		ClientTickCallback.EVENT.register(s -> lock=true);
		ServerTickCallback.EVENT.register(s -> lock=true);
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
