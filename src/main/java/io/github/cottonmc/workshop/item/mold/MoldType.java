package io.github.cottonmc.workshop.item.mold;

import com.google.common.base.MoreObjects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import javax.annotation.Nullable;

public class MoldType {
    private final Identifier identifier;

    public MoldType(Identifier identifier) {
        this.identifier = identifier;
    }

    public Identifier getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof MoldType)) return false;
        return (this.identifier.equals(((MoldType) obj).identifier));
    }

    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("identifier", identifier.toString())
            .toString();
    }

    @Nullable
    public static MoldType getMoldType(Item item) {
        if (item instanceof ClayMoldItem) {
            return ((ClayMoldItem) item).getMoldType();
        }

        return null;
    }

    @Nullable
    public static MoldType getMoldType(ItemStack stack) {
        return getMoldType(stack.getItem());
    }
}
