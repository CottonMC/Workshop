package io.github.cottonmc.workshop.client.widget;

import io.github.cottonmc.cotton.gui.CottonScreenController;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WWidget;
import io.github.cottonmc.workshop.Workshop;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class WRecipeSelectorWidget extends WWidget {
	private final CottonScreenController controller;
	private List<Item> choiceList = new ArrayList<>();

	public WRecipeSelectorWidget(CottonScreenController controller) {
		this.controller = controller;
	}

	public void clear() {
		this.choiceList.clear();
	}

	public void onClick(int x, int y, int button) {

	}

	@Environment(EnvType.CLIENT)
	protected void renderTooltip(int tX, int tY) {
		super.renderTooltip(tX, tY);
		// TODO Figure out.
	}

	// this.minecraft.getItemRenderer().renderGuiItem(((StonecuttingRecipe) list.get(l)).getOutput(), n, p);

}
