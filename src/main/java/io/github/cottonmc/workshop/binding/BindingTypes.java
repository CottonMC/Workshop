package io.github.cottonmc.workshop.binding;

import com.google.common.collect.ImmutableMap;
import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.util.ColorPalettes;
import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

public final class BindingTypes {
    private static final Map<Identifier, BindingType> bindingTypes = new HashMap<>();

    public static final BindingType GRASS_ROPE = BindingTypes.register(new BindingType(Workshop.id("grass_rope"), ColorPalettes.GRASS_ROPE));
    public static final BindingType VINE_ROPE = BindingTypes.register(new BindingType(Workshop.id("vine_rope"), ColorPalettes.VINE_ROPE));
    public static final BindingType SILK = BindingTypes.register(new BindingType(Workshop.id("silk"), ColorPalettes.IRON));

    public static BindingType register(BindingType type) {
        if(Workshop.isLocked()) {
            throw new UnsupportedOperationException("Cannot register new BindingTypes after game is loaded.");
        }

        return bindingTypes.put(type.getIdentifier(), type);
    }

    public static void init() {}

    public static Map<Identifier, BindingType> getRegistered() {
        return ImmutableMap.copyOf(bindingTypes);
    }
}
