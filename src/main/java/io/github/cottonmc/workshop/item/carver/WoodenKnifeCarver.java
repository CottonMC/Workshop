package io.github.cottonmc.workshop.item.carver;

import io.github.cottonmc.workshop.api.KnifeCarverItem;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;

public class WoodenKnifeCarver extends ToolItem implements KnifeCarverItem {
	public WoodenKnifeCarver(ToolMaterial material, Settings settings) {
		super(material, settings);
	}

	@Override
	public int getCarverLevel() {
		return 0;
	}
}
