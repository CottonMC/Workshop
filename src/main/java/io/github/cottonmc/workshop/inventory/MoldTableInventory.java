package io.github.cottonmc.workshop.inventory;

import io.github.cottonmc.workshop.container.MoldTableContainer;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.util.DefaultedList;

import java.util.Iterator;

/**
 * Represents the inventory of an item.
 * Slots:
 * 0, Clay blob/Clay mold slot.
 * 1, Knife/Carver slot
 * 2, Output: Clay mold output.
 */
public class MoldTableInventory implements Inventory, RecipeInputProvider {
	/**
	 * Represents the input slot for input slot.
	 */
	public static final int INPUT = 0;
	/**
	 * Represents the ID for the carver slot.
	 */
	public static final int CARVER = 1;
	/**
	 * Represents the ID for the output slot.
	 */
	public static final int OUTPUT = 2;

	private final MoldTableContainer container;
	private DefaultedList<ItemStack> stacks;

	public MoldTableInventory(MoldTableContainer container) {
		this.container = container;
		this.stacks = DefaultedList.ofSize(3, ItemStack.EMPTY);
	}

	@Override
	public int getInvSize() {
		return 3;
	}

	@Override
	public boolean isInvEmpty() {
		Iterator var1 = this.stacks.iterator();

		ItemStack itemStack;
		do {
			if (!var1.hasNext()) {
				return true;
			}

			itemStack = (ItemStack)var1.next();
		} while(itemStack.isEmpty());

		return false;
	}

	@Override
	public ItemStack getInvStack(int slot) {
		return this.stacks.get(slot);
	}

	@Override
	public ItemStack takeInvStack(int slot, int amount) {
		ItemStack itemStack = Inventories.splitStack(this.stacks, slot, amount);
		if (!itemStack.isEmpty()) {
			this.container.onContentChanged(this);
		}

		return itemStack;
	}

	@Override
	public ItemStack removeInvStack(int slot) {
		return Inventories.removeStack(this.stacks, slot);
	}

	@Override
	public void setInvStack(int slot, ItemStack stack) {
		this.stacks.set(slot, stack);
		this.container.onContentChanged(this);
	}

	@Override
	public void markDirty() {
		this.container.onContentChanged(this);
		this.container.contentsChangedListener.run();
	}

	@Override
	public boolean canPlayerUseInv(PlayerEntity player) {
		return true;
	}

	@Override
	public void provideRecipeInputs(RecipeFinder recipeFinder) {
		Iterator<ItemStack> stacks = this.stacks.iterator();

		while(stacks.hasNext()) {
			ItemStack itemStack = stacks.next();
			recipeFinder.addNormalItem(itemStack);
		}
	}

	@Override
	public void clear() {
		this.stacks.clear();
	}
}
