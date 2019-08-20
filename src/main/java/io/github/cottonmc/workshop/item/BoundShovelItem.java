package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.Workshop;
import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

public class BoundShovelItem extends ShovelItem {
	public BoundShovelItem(ToolMaterial material, Settings settings) {
		super(material, 1.5F, -3F, settings);
		this.addPropertyGetter(new Identifier(Workshop.MODID, "binding"), (stack, world, entity) -> {
			if (!stack.hasTag()) return 0F;
			CompoundTag tag = stack.getTag();
			if (!tag.containsKey("Binding", NbtType.STRING)) return 0F;
			String binding = tag.getString("Binding");
			switch(binding) {
				case "vine":
					return 1F;
				case "silk":
					return 2F;
				default:
					return 0F;
			}
		});
	}
}
