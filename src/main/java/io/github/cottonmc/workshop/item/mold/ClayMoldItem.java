package io.github.cottonmc.workshop.item.mold;

import io.github.cottonmc.workshop.item.mold.MoldType;
import net.minecraft.item.Item;

public class ClayMoldItem extends Item {
    private final MoldType type;

    public ClayMoldItem(MoldType type, Item.Settings itemSettings) {
        super(itemSettings);
        this.type = type;
    }

    public MoldType getMoldType() {
        return this.type;
    }

    public boolean isSoft() {
        return true;
    }
}
