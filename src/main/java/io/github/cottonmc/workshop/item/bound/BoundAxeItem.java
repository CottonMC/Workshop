package io.github.cottonmc.workshop.item.bound;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.binding.BindingPropertyGetter;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ToolMaterial;

public class BoundAxeItem extends AxeItem {
	public BoundAxeItem(ToolMaterial material, Settings settings) {
		super(material, 6f, -3.1F, settings);
		this.addPropertyGetter(Workshop.id("binding"), BindingPropertyGetter.INSTANCE);
	}
}
