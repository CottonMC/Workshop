package io.github.cottonmc.workshop.inventory.slot;

import net.minecraft.container.Slot;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.function.Predicate;

public class TagPredicatedSlot extends Slot {
	private Predicate<ItemStack> predicate;

	public TagPredicatedSlot(Inventory inventory, int invSlot, int xPosition, int yPosition, Predicate<ItemStack> predicate) {
		super(inventory, invSlot, xPosition, yPosition);
		this.predicate = predicate;
	}

	public boolean canInsert(ItemStack stack) {
		return this.predicate.test(stack);
	}
}
