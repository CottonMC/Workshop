package io.github.cottonmc.workshop.recipe.moldtable;

import com.mojang.datafixers.util.Pair;
import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.item.mold.ClayMoldItem;
import io.github.cottonmc.workshop.item.mold.HardMoldItem;
import io.github.cottonmc.workshop.item.mold.MoldType;
import io.github.cottonmc.workshop.item.mold.MoldTypes;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.SmeltingRecipe;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class MoldFurnaceRecipes implements SimpleSynchronousResourceReloadListener {
    public static List<SmeltingRecipe> calculate() {
        List<SmeltingRecipe> smeltingRecipes = new ArrayList<>();
        MoldTypes.getTypeToHardenedMoldMap().forEach((a,b) -> System.out.println(a.toString() + ": " + b));
        return smeltingRecipes;
    }

    @Override
    public Identifier getFabricId() {
        return Workshop.id("mold_recipe_resource_reload_listener");
    }

    @Override
    public void apply(ResourceManager manager) {

    }
}
