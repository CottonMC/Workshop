package io.github.cottonmc.workshop.block.entity;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.block.WorkshopBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class WorkshopBlockEntities {
	public static BlockEntityType<ToolFurnaceBlockEntity> TOOL_FURNACE_BE = register("tool_furnace", ToolFurnaceBlockEntity::new, WorkshopBlocks.TOOL_FURNACE);

    protected static <T extends BlockEntity> BlockEntityType<T> register(String name, Supplier<T> tConstructor, Block... blocks){
        BlockEntityType.Builder<T> builder = BlockEntityType.Builder.create(tConstructor, blocks);
        BlockEntityType<T> type = builder.build(null);

        Registry.register(Registry.BLOCK_ENTITY, Workshop.id(name), type);

        return type;
    }
}
