package io.github.cottonmc.workshop.item.bound;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.binding.BindingPropertyGetter;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ToolMaterial;

public class BoundHoeItem extends HoeItem {
	public BoundHoeItem(ToolMaterial material, Settings settings) {
		super(material, material.getMiningLevel()-3F, settings);
		this.addPropertyGetter(Workshop.id("binding"), BindingPropertyGetter.INSTANCE);
	}
}
