package io.github.cottonmc.workshop.client.screen;

import io.github.cottonmc.cotton.gui.client.CottonScreen;
import io.github.cottonmc.workshop.controller.MoldTableController;
import net.minecraft.entity.player.PlayerEntity;

public class MoldTableScreen extends CottonScreen<MoldTableController> {
    public MoldTableScreen(MoldTableController container, PlayerEntity player) {
        super(container, player);
    }
}
