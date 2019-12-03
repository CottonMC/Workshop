package io.github.cottonmc.workshop.recipe;

import com.google.gson.JsonObject;
import io.github.cottonmc.workshop.recipe.moldtable.MoldTableRecipe;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.util.PacketByteBuf;
import net.minecraft.util.registry.Registry;

public class MoldTableRecipeSerializer implements RecipeSerializer<MoldTableRecipe> {
	@Override
	public MoldTableRecipe read(Identifier id, JsonObject json) {
		String result = JsonHelper.getString(json, "result");
		int requiredCarverLevel = JsonHelper.getInt(json, "carverLevel");

		Identifier resultIdentifier = new Identifier(result);
		ItemStack resultStack = new ItemStack(Registry.ITEM.getOrEmpty(resultIdentifier).orElseThrow(() -> new IllegalStateException("Item: " + result + " does not exist")));
		return new MoldTableRecipe(id, requiredCarverLevel, resultStack);
	}

	@Override
	public MoldTableRecipe read(Identifier id, PacketByteBuf buf) {
		ItemStack result = buf.readItemStack();
		int requiredCarverLevel = buf.readInt();
		return new MoldTableRecipe(id, requiredCarverLevel, result);
	}

	@Override
	public void write(PacketByteBuf buf, MoldTableRecipe recipe) {
		buf.writeInt(recipe.getRequiredCarverLevel());
		buf.writeItemStack(recipe.getOutput());
	}
}
