package io.github.cottonmc.workshop.container;

import io.github.cottonmc.workshop.Workshop;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ContainerProviders {
    public static void init() {
        ContainerProviderRegistry.INSTANCE.registerFactory(Workshop.id("detached_moldtable"), ContainerProviders::createMoldTableContainerDetached);
        ContainerProviderRegistry.INSTANCE.registerFactory(Workshop.id("moldtable"), ContainerProviders::createMoldTableContainer);
        ContainerProviderRegistry.INSTANCE.registerFactory(Workshop.id("tool_furnace"), ContainerProviders::createToolFurnaceController);
    }

    private static Container createMoldTableContainer(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf buf) {
        return new MoldTableContainer(syncId, playerEntity.inventory, BlockContext.create(playerEntity.world, buf.readBlockPos()));
    }

    private static Container createMoldTableContainerDetached(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf buf) {
        return new MoldTableContainer(syncId, playerEntity.inventory, BlockContext.EMPTY);
    }

    private static Container createToolFurnaceController(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf buf) {
        return new ToolFurnaceController(syncId, playerEntity.inventory, BlockContext.create(playerEntity.world, buf.readBlockPos()));
    }

    private ContainerProviders() {}
}
