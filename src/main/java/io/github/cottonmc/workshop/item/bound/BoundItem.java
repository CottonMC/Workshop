package io.github.cottonmc.workshop.item.bound;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.binding.BindingPropertyGetter;
import net.minecraft.item.Item;

public class BoundItem extends Item {
	public BoundItem(Settings settings) {
		super(settings);
		this.addPropertyGetter(Workshop.id("binding"), BindingPropertyGetter.INSTANCE);
	}
}
