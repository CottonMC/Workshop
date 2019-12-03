package io.github.cottonmc.workshop.recipe.moldtable;

import io.github.cottonmc.workshop.inventory.MoldTableInventory;
import io.github.cottonmc.workshop.api.KnifeCarverItem;
import io.github.cottonmc.workshop.item.WorkshopItems;
import io.github.cottonmc.workshop.item.mold.ClayMoldItem;
import io.github.cottonmc.workshop.recipe.WorkshopRecipeSerializers;
import io.github.cottonmc.workshop.recipe.WorkshopRecipeTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class MoldTableRecipe implements Recipe<MoldTableInventory> {
    private final int requiredCarverLevel;
    private final ItemStack output;
    private final Identifier id;

    public MoldTableRecipe(Identifier id, int requiredCarverLevel, ItemStack output) {
        this.requiredCarverLevel = requiredCarverLevel;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(MoldTableInventory inventory, World world) {
        System.out.println("y");
        if (inventory.getInvStack(MoldTableInventory.INPUT).isEmpty()) {
            System.out.println("f");
            return false;
        }

        System.out.println("x");
        if(inventory.getInvStack(MoldTableInventory.INPUT).getItem() != WorkshopItems.CLAY_BLOB) {
            if (!(inventory.getInvStack(MoldTableInventory.INPUT).getItem() instanceof ClayMoldItem)) {
                return false;
            }

            if (!((ClayMoldItem) inventory.getInvStack(MoldTableInventory.INPUT).getItem()).isSoft()) {
                System.out.println("fff");
            }
        }

        ItemStack stack = inventory.getInvStack(MoldTableInventory.CARVER);
        boolean isKnife = stack.getItem() instanceof KnifeCarverItem;

        boolean isRightCarveLevel = false;

        if (isKnife) {
            isRightCarveLevel = this.requiredCarverLevel <= ((KnifeCarverItem) stack.getItem()).getCarverLevel();
        }

        return isKnife && isRightCarveLevel;
    }

    @Override
    public ItemStack craft(MoldTableInventory inventory) {
        return this.output.copy();
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return this.id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WorkshopRecipeSerializers.MOLD_TABLE;
    }

    @Override
    public RecipeType<?> getType() {
        return WorkshopRecipeTypes.MOLDS;
    }

	public int getRequiredCarverLevel() {
        return this.requiredCarverLevel;
	}
}
