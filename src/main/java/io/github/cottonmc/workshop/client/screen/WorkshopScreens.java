package io.github.cottonmc.workshop.client.screen;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.controller.MoldTableController;
import io.github.cottonmc.workshop.controller.ToolFurnaceController;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.PacketByteBuf;

@Environment(EnvType.CLIENT)
public class WorkshopScreens {
    public static void init() {
        ScreenProviderRegistry.INSTANCE.registerFactory(Workshop.id("tool_furnace"), WorkshopScreens::createToolFurnace);
        ScreenProviderRegistry.INSTANCE.registerFactory(Workshop.id("moldtable"), WorkshopScreens::createMoldTable);
    }

    private WorkshopScreens() {}

    private static MoldTableScreen createMoldTable(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
        return new MoldTableScreen(
                new MoldTableController(
                        syncId,
                        player.inventory,
                        BlockContext
                                .create(
                                        player.world,
                                        buf.readBlockPos()
                                )
                ),
                player);
    }

    private static ToolFurnaceScreen createToolFurnace(int syncId, Identifier identifier, PlayerEntity player, PacketByteBuf buf) {
        return new ToolFurnaceScreen(
                new ToolFurnaceController(
                        syncId,
                        player.inventory,
                        BlockContext
                                .create(
                                        player.world,
                                        buf.readBlockPos()
                                )
                ),
                player);
    }
}
