package io.github.cottonmc.workshop.item.bound;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.binding.BindingPropertyGetter;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class BoundShovelItem extends ShovelItem {
	public BoundShovelItem(ToolMaterial material, Settings settings) {
		super(material, 1.5F, -3F, settings);
		this.addPropertyGetter(Workshop.id("binding"), BindingPropertyGetter.INSTANCE);
	}
}
