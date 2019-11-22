package io.github.cottonmc.workshop.client;

import io.github.cottonmc.workshop.client.screen.WorkshopScreens;
import io.github.cottonmc.workshop.item.WorkshopItems;
import io.github.cottonmc.workshop.util.Binding;
import io.github.cottonmc.workshop.util.ColorPalettes;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class WorkshopClient implements ClientModInitializer{

	@Override
	public void onInitializeClient() {

		WorkshopScreens.init();
		ColorProviderRegistry.ITEM.register((stack, layer) -> getBoundToolColor(stack, layer, 1), WorkshopItems.WOOD_HANDLE);
		ColorProviderRegistry.ITEM.register((stack, layer) -> getBoundToolColor(stack, layer, 1), WorkshopItems.WOOD_PICK_HEAD);
		ColorProviderRegistry.ITEM.register((stack, layer) -> getBoundToolColor(stack, layer, 2), WorkshopItems.WOOD_PICK);
	}

	public int getBoundToolColor(ItemStack stack, int layer, int firstTint) {
		CompoundTag tag = stack.getOrCreateTag();
		if (layer == firstTint) { //main binding
			if (tag.containsKey("Binding", NbtType.STRING)) {
				return Binding.forName(tag.getString("Binding")).getColor();
			} else return ColorPalettes.GRASS_ROPE;
		} else if (layer == firstTint+1) { //head binding
			if (tag.containsKey("HeadBinding", NbtType.STRING)) {
				return Binding.forName(tag.getString("HeadBinding")).getColor();
			} else return ColorPalettes.GRASS_ROPE;
		} else if (layer == firstTint+2) { //handle binding
			if (tag.containsKey("HandleBinding")) {
				return Binding.forName(tag.getString("HandleBinding")).getColor();
			} else return ColorPalettes.GRASS_ROPE;
		}
		return 0xFFFFFF; //non-tinted parts
	}



}
