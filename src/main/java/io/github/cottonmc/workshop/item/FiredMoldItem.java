package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.item.mold.MoldType;

public class FiredMoldItem extends WetMoldItem {
    public FiredMoldItem(MoldType type, Settings itemSettings) {
        super(type, itemSettings);
    }

    public boolean isSoft() {
        return false;
    }
}
