package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.binding.BindingPropertyGetter;
import net.minecraft.item.Item;

public class TongsItem extends Item {
	public TongsItem(Settings settings) {
		super(settings);
		this.addPropertyGetter(Workshop.id("binding"), BindingPropertyGetter.INSTANCE);
	}
}
