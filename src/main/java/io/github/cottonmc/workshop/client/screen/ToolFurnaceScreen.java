package io.github.cottonmc.workshop.client.screen;

import io.github.cottonmc.cotton.gui.client.CottonScreen;
import io.github.cottonmc.workshop.controller.ToolFurnaceController;
import net.minecraft.entity.player.PlayerEntity;

public class ToolFurnaceScreen extends CottonScreen<ToolFurnaceController> {
	public ToolFurnaceScreen(ToolFurnaceController container, PlayerEntity player) {
		super(container, player);
	}
}
