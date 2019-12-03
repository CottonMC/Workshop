package io.github.cottonmc.workshop.util;

import io.github.cottonmc.workshop.Workshop;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.ItemTags;

public class WorkshopPredicates {
	public static boolean isKnifeItem(ItemStack stack) {
		return ItemTags.getContainer().get(Workshop.id("mold_carver_item")).contains(stack.getItem());
	}
}
