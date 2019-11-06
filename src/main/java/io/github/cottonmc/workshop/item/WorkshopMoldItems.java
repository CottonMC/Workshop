package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.item.mold.MoldTypes;
import net.minecraft.item.Item;

import static io.github.cottonmc.workshop.item.WorkshopItems.defaultSettings;
import static io.github.cottonmc.workshop.item.WorkshopItems.register;

public class WorkshopMoldItems {
    public static final Item SOFT_HANDLE = register("soft_handle_mold", new SoftMoldItem(MoldTypes.HANDLE, defaultSettings()));
    public static final Item SOFT_HILT = register("soft_hilt_mold", new SoftMoldItem(MoldTypes.HILT, defaultSettings()));
    public static final Item SOFT_BOLTS = register("soft_bolts_mold", new SoftMoldItem(MoldTypes.BOLTS, defaultSettings()));

    public static void init() {
        // NO-OP
    }
}
