package io.github.cottonmc.workshop;

import io.github.cottonmc.workshop.binding.BindingTypes;
import io.github.cottonmc.workshop.block.WorkshopBlocks;
import io.github.cottonmc.workshop.container.ContainerProviders;
import io.github.cottonmc.workshop.item.WorkshopItems;
import io.github.cottonmc.workshop.item.WorkshopMoldItems;
import io.github.cottonmc.workshop.item.mold.MoldTypes;
import io.github.cottonmc.workshop.recipe.WorkshopRecipeSerializers;
import io.github.cottonmc.workshop.recipe.WorkshopRecipeTypes;
import io.github.cottonmc.workshop.recipe.moldtable.MoldFurnaceRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.fabricmc.fabric.api.event.server.ServerTickCallback;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.AbstractMessageFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;

public class Workshop implements ModInitializer {
	public static final String MODID = "workshop";
	private static boolean lock;
	private static final Logger LOGGER = LogManager.getLogger(new AbstractMessageFactory() {
		@Override
		public Message newMessage(String message, Object... params) {
			return new SimpleMessage("[Workshop]" + message);
		}
	});

    public static boolean isLocked() {
    	return lock;
    }

	public static void info(String message) {
		LOGGER.info(message);
	}

	@Override
	public void onInitialize() {
		MoldTypes.init();
		BindingTypes.init();

		WorkshopItems.init();
		WorkshopMoldItems.init();
		WorkshopBlocks.init();
		ContainerProviders.init();

		WorkshopRecipeTypes.init();
		WorkshopRecipeSerializers.init();

		// We cannot let new MoldTypes or BindingTypes be registered once the Client or Server pushes it's first tick.
		ClientTickCallback.EVENT.register(s -> lock=true);
		ServerTickCallback.EVENT.register(s -> lock=true);

		MoldFurnaceRecipes.calculate();
	}

	public static Identifier id(String path) {
		return new Identifier(MODID, path);
	}
}
