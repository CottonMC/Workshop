package io.github.cottonmc.workshop.item.bound;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.binding.BindingPropertyGetter;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class BoundPickaxeItem extends PickaxeItem {
	public BoundPickaxeItem(ToolMaterial material, Settings settings) {
		super(material, 1, -2.8F, settings);
		this.addPropertyGetter(Workshop.id("binding"), BindingPropertyGetter.INSTANCE);
	}
}
