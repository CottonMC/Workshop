package io.github.cottonmc.workshop.inventory.slot;

import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import java.util.function.Consumer;

public class RecipeResultSlot<I extends Inventory> extends Slot {
	private final Consumer<I> onTakeListener;

	public RecipeResultSlot(I inventory, int invSlot, int xPosition, int yPosition, Consumer<I> onTakeListener) {
		super(inventory, invSlot, xPosition, yPosition);
		this.onTakeListener = onTakeListener;
	}

	public boolean canInsert(ItemStack stack) {
		return false;
	}

	public ItemStack onTakeItem(PlayerEntity player, ItemStack stack) {
		onTakeListener.accept((I) this.inventory);
		return stack;
	}
}
