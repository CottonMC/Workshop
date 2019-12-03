package io.github.cottonmc.workshop.block;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.item.WorkshopItems;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.util.registry.Registry;

public class WorkshopBlocks {
	//prevents blocks from being placed in the blockspace above a tool furnace or the line
	public static Block BLOCKER = Registry.register(Registry.BLOCK, Workshop.id("blocker"), new BlockerBlock(FabricBlockSettings.of(Material.STONE).noCollision().dropsNothing().strength(3.5f, 3.5f).build()));

    /** This block is for making molds. */
    public static final Block MOLD_TABLE = register("moldtable", new MoldTableBlock(FabricBlockSettings.of(Material.WOOD, MaterialColor.BROWN).nonOpaque().build()));
    
    /** These blocks are for crafting tool parts and materials. */
    //public static final Block WOOD_CUTTING_TABLE = new Block(Block.Settings.copy(MOLD_TABLE));
    public static final Block TOOL_FURNACE = register("tool_furnace", new ToolFurnaceBlock(FabricBlockSettings.of(Material.STONE).breakByTool(FabricToolTags.PICKAXES).strength(3.5f, 3.5f).build()));

	/** These blocks are for enchanted smelting and smithing, respectively. */
	//public static final Block ENCHANTED_FURNACE = new Block(Block.Settings.copy(Blocks.BLAST_FURNACE));
	//public static final Block ENCHANTED_ANVIL = new Block(Block.Settings.copy(METAL_ANVIL));

    //public static final Block TANNING_CAULDRON = new Block(Block.Settings.copy(Blocks.CAULDRON));
    //public static final Block METAL_ANVIL = new Block(Block.Settings.copy(Blocks.ANVIL));

	public static void init() {
    }
    
    public static Block register(String name, Block block) {
    	Registry.register(Registry.BLOCK, Workshop.id(name), block);
    	WorkshopItems.register(name, new BlockItem(block, WorkshopItems.defaultSettings()));
		return block;
    }
}
