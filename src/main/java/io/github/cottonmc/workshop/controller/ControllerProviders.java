package io.github.cottonmc.workshop.controller;

import io.github.cottonmc.workshop.Workshop;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

public class ControllerProviders {
    public static void init() {
        ContainerProviderRegistry.INSTANCE.registerFactory(Workshop.id("tool_furnace"), ControllerProviders::createToolFurnaceController);
    }

    private static Container createToolFurnaceController(int syncId, Identifier identifier, PlayerEntity playerEntity, PacketByteBuf buf) {
        return new ToolFurnaceController(syncId, playerEntity.inventory, BlockContext.create(playerEntity.world, buf.readBlockPos()));
    }

    private ControllerProviders() {}
}
