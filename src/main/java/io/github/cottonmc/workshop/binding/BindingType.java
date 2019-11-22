package io.github.cottonmc.workshop.binding;

import com.google.common.base.MoreObjects;
import io.github.cottonmc.workshop.item.mold.MoldType;
import net.minecraft.util.Identifier;

public class BindingType {
    private final Identifier identifier;
    private final int colorPalette;

    public BindingType(Identifier identifier, int colorPalette) {
        this.identifier = identifier;
        this.colorPalette = colorPalette;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public int getColorPalette() {
        return colorPalette;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof BindingType)) return false;
        return (this.identifier.equals(((BindingType) obj).identifier));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("identifier", identifier.toString())
                .add("colorPalette", colorPalette)
        .toString();
    }
}
