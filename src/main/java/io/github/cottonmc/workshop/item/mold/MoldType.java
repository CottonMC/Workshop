package io.github.cottonmc.workshop.item.mold;

import net.minecraft.util.Identifier;

public class MoldType {
    private final Identifier identifier;

    public MoldType(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }
}
