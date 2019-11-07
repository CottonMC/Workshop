package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.Workshop;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.*;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.loot.UniformLootTableRange;
import net.minecraft.world.loot.condition.InvertedLootCondition;
import net.minecraft.world.loot.condition.MatchToolLootCondition;
import net.minecraft.world.loot.entry.ItemEntry;

public class WorkshopItems {

	//raw materials
	public static Item GRASS_BLADES;
	public static Item VINE_BUNDLE;
	public static Item ROCK_CHUNK;
	public static Item CLAY_BLOB;

	//semi-processed materials
	public static Item GRASS_ROPE;
	public static Item VINE_ROPE;

	//tool ingredients
	public static Item WOOD_HANDLE;
	public static Item WOOD_PICK_HEAD;
	public static Item WOOD_AXE_HEAD;
	public static Item WOOD_SHOVEL_HEAD;
	public static Item WOOD_HOE_HEAD;

	//tools
	public static Item WOOD_PICK;
	public static Item WOOD_AXE;
	public static Item WOOD_SHOVEL;
	public static Item WOOD_HOE;

	//misc
	public static Item STONE_TONGS;

	public static final ItemGroup WORKSHOP_GROUP = FabricItemGroupBuilder.build(Workshop.id("main_group"), () -> new ItemStack(GRASS_ROPE));
	public static final ItemGroup WORKSHOP_MOLDS_GROUP = FabricItemGroupBuilder.build(Workshop.id("mold_group"), () -> new ItemStack(WorkshopMoldItems.WET_AXE_HEAD));

	public static void init() {
		GRASS_BLADES = register("grass_blades", new Item(defaultSettings()));
		VINE_BUNDLE = register("vine_bundle", new Item(defaultSettings()));
		ROCK_CHUNK = register("rock_chunk", new Item(defaultSettings()));
		CLAY_BLOB = register("clay_blob", new Item(defaultSettings()));

		GRASS_ROPE = register("grass_rope", new Item(defaultSettings()));
		VINE_ROPE = register("vine_rope", new Item(defaultSettings()));

		//TODO: enchantability?
		WOOD_HANDLE = register("wood_handle", new BoundItem(defaultSettings()));
		WOOD_PICK_HEAD = register("wood_pickaxe_head", new BoundItem(defaultSettings()));
		WOOD_AXE_HEAD = register("wood_axe_head", new BoundItem(defaultSettings()));
		WOOD_SHOVEL_HEAD = register("wood_shovel_head", new BoundItem(defaultSettings()));
		WOOD_HOE_HEAD = register("wood_hoe_head", new BoundItem(defaultSettings()));

		WOOD_PICK = register("wood_pickaxe", new BoundPickaxeItem(ToolMaterials.WOOD, defaultSettings()));
		WOOD_AXE = register("wood_axe", new BoundAxeItem(ToolMaterials.WOOD, defaultSettings()));
		WOOD_SHOVEL = register("wood_shovel", new BoundShovelItem(ToolMaterials.WOOD, defaultSettings()));
		WOOD_HOE = register("wood_hoe", new BoundHoeItem(ToolMaterials.WOOD, defaultSettings()));

		STONE_TONGS = register("stone_tongs", new TongsItem(defaultSettings().maxDamage(120)));

		LootTableLoadingCallback.EVENT.register(((resourceManager, lootManager, id, supplier, lootTableSetter) -> {
			if (id.equals(new Identifier("minecraft", "blocks/grass"))) {
				FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0, 2))
						.withEntry(ItemEntry.builder(GRASS_BLADES))
						.withCondition(InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder.create().item(Items.SHEARS))));

				supplier.withPool(builder);
			} else if (id.equals(new Identifier("minecraft", "blocks/vine"))) {
				FabricLootPoolBuilder builder = FabricLootPoolBuilder.builder()
						.withRolls(UniformLootTableRange.between(0, 2))
						.withEntry(ItemEntry.builder(VINE_BUNDLE))
						.withCondition(InvertedLootCondition.builder(MatchToolLootCondition.builder(ItemPredicate.Builder.create().item(Items.SHEARS))));

				supplier.withPool(builder);
			}
		}));
	}

	public static Item.Settings defaultSettings() {
		return new Item.Settings().group(WORKSHOP_GROUP);
	}

	public static Item register(String path, Item item) {
		return Registry.register(Registry.ITEM, Workshop.id(path), item);
	}

	public static Item.Settings moldSettings() {
		return new Item.Settings().group(WORKSHOP_MOLDS_GROUP);
	}
}
