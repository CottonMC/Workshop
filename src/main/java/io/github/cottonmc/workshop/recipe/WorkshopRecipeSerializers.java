package io.github.cottonmc.workshop.recipe;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.recipe.moldtable.MoldTableRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.registry.Registry;

public class WorkshopRecipeSerializers {
	public static final RecipeSerializer<MoldTableRecipe> MOLD_TABLE = registerSerializer("moldtable", new MoldTableRecipeSerializer());

	public static <S extends RecipeSerializer<T>, T extends Recipe<?>> S registerSerializer(String name, S serializer) {
		return Registry.register(Registry.RECIPE_SERIALIZER, Workshop.id(name), serializer);
	}

	public static void init() {
	}
}
