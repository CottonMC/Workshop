package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.item.bound.*;
import io.github.cottonmc.workshop.item.carver.WoodenKnifeCarver;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.*;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.condition.InvertedLootCondition;
import net.minecraft.loot.condition.MatchToolLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WorkshopItems {

	//raw materials
	public static final Item GRASS_BLADES = register("grass_blades", new Item(defaultSettings()));
	public static final Item VINE_BUNDLE = register("vine_bundle", new Item(defaultSettings()));
	public static final Item ROCK_CHUNK = register("rock_chunk", new Item(defaultSettings()));
	public static final Item CLAY_BLOB = register("clay_blob", new Item(defaultSettings()));

	//semi-processed materials
	public static final Item GRASS_ROPE = register("grass_rope", new Item(defaultSettings()));
	public static final Item VINE_ROPE = register("vine_rope", new Item(defaultSettings()));

	//tool ingredients
	public static Item WOOD_HANDLE;
	public static Item WOOD_PICK_HEAD;
	public static Item WOOD_AXE_HEAD;
	public static Item WOOD_SHOVEL_HEAD;
	public static Item WOOD_HOE_HEAD;

	//tools
	public static final Item WOOD_PICK = register("wood_pickaxe", new BoundPickaxeItem(ToolMaterials.WOOD, defaultSettings()));
	public static final Item WOOD_AXE = register("wood_axe", new BoundAxeItem(ToolMaterials.WOOD, defaultSettings()));
	public static final Item WOOD_SHOVEL = register("wood_shovel", new BoundShovelItem(ToolMaterials.WOOD, defaultSettings()));
	public static final Item WOOD_HOE = register("wood_hoe", new BoundHoeItem(ToolMaterials.WOOD, defaultSettings()));

	public static final Item WOOD_KNIFE = register("wood_knife", new WoodenKnifeCarver(ToolMaterials.WOOD, defaultSettings().maxCount(1).maxDamage(20)));

	//misc
	public static final Item STONE_TONGS = register("stone_tongs", new TongsItem(defaultSettings().maxDamage(120)));

	public static final ItemGroup WORKSHOP_GROUP = FabricItemGroupBuilder.build(Workshop.id("main_group"), () -> new ItemStack(GRASS_ROPE));
	public static final ItemGroup WORKSHOP_MOLDS_GROUP = FabricItemGroupBuilder.build(Workshop.id("mold_group"), () -> new ItemStack(WorkshopMoldItems.WET_AXE_HEAD));

	public static void init() {
		//TODO: enchantability?
		WOOD_HANDLE = register("wood_handle", new BoundItem(defaultSettings()));
		WOOD_PICK_HEAD = register("wood_pickaxe_head", new BoundItem(defaultSettings()));
		WOOD_AXE_HEAD = register("wood_axe_head", new BoundItem(defaultSettings()));
		WOOD_SHOVEL_HEAD = register("wood_shovel_head", new BoundItem(defaultSettings()));
		WOOD_HOE_HEAD = register("wood_hoe_head", new BoundItem(defaultSettings()));

		modifyLootTable();
	}

	private static void modifyLootTable() {
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
