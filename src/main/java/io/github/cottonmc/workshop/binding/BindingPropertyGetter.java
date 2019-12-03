package io.github.cottonmc.workshop.binding;

import net.fabricmc.fabric.api.util.NbtType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BindingPropertyGetter implements ItemPropertyGetter {
    public static BindingPropertyGetter INSTANCE = new BindingPropertyGetter();

    private BindingPropertyGetter() {}

    @Override
    public float call(ItemStack stack, @Nullable World world, @Nullable LivingEntity livingEntity) {
        if (!stack.hasTag()) return 0F;
        CompoundTag tag = stack.getTag();
        if (!tag.contains("Binding", NbtType.STRING)) return 0F;
        String binding = tag.getString("Binding");
        switch(binding) {
            case "vine":
                return 1F;
            case "silk":
                return 2F;
            default:
                return 0F;
        }
    }
}
