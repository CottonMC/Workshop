package io.github.cottonmc.workshop.controller;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import net.minecraft.container.BlockContext;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.recipe.RecipeType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;

public class MoldTableController extends CottonScreenController {
    public static final RecipeType<?> MOLD_OPTIONS = RecipeType.register("moldtable");

    public MoldTableController(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(MOLD_OPTIONS, syncId, playerInventory, getBlockInventory(context), getBlockPropertyDelegate(context));

        //TODO: switch to a plain panel once we have the final gui design!
        WGridPanel rootPanel = new WGridPanel();
        this.setRootPanel(rootPanel);

        rootPanel.add(
                new WLabel(
                        new TranslatableText("block.workshop.tool_furnace"),
                        WLabel.DEFAULT_TEXT_COLOR
                ), 0, 0);
//		WItemSlot inputSlot = WItemSlot.of(blockInventory, ToolFurnaceBlockEntity.INPUT);
//		WItemSlot fuelSlot = WItemSlot.of(blockInventory, ToolFurnaceBlockEntity.FUEL);
//		WItemSlot outputSlot = WItemSlot.of(blockInventory, ToolFurnaceBlockEntity.OUTPUT);
//		WItemSlot moldSlot = WItemSlot.of(blockInventory, ToolFurnaceBlockEntity.MOLD);
//		rootPanel.add(inputSlot, 0, 0);
//		//rootPanel.add(burnTimeSprite, 0, 1);
//		rootPanel.add(fuelSlot, 0, 2);
//		rootPanel.add(outputSlot, 4, 1);
//		rootPanel.add(moldSlot, 2, 1);

        WLabel inProgress = new WLabel(new LiteralText("GUI under construction!"), WLabel.DEFAULT_TEXT_COLOR);

        rootPanel.add(inProgress, 0, 2);

        rootPanel.add(this.createPlayerInventoryPanel(), 0, 3);

        rootPanel.validate(this);
    }
}
