package io.github.cottonmc.workshop.recipe;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.recipe.moldtable.MoldTableRecipe;
import io.github.cottonmc.workshop.recipe.toolfurnace.ToolFurnaceRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.registry.Registry;

public class WorkshopRecipes {
	public static RecipeType<ToolFurnaceRecipe> TOOL_FURNACE = register("tool_furnace");
	public static RecipeType<MoldTableRecipe> MOLDS = register("moldtable");

	public static void init() {
	}

	public static <T extends Recipe<?>> RecipeType<T> register(String id) {
		return Registry.register(Registry.RECIPE_TYPE, Workshop.id(id), new RecipeType<T>() {
			public String toString() {
				return id;
			}
		});
	}

	public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S register(String name, S serializer) {
		return Registry.register(Registry.RECIPE_SERIALIZER, Workshop.id(name), serializer);
	}

}
