package io.github.cottonmc.workshop.item.mold;

public class HardMoldItem extends ClayMoldItem {
    public HardMoldItem(MoldType type, Settings itemSettings) {
        super(type, itemSettings);
    }

    public boolean isSoft() {
        return false;
    }
}
