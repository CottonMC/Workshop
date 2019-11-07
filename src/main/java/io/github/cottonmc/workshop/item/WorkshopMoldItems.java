package io.github.cottonmc.workshop.item;

import io.github.cottonmc.workshop.item.mold.MoldTypes;
import net.minecraft.item.Item;

import static io.github.cottonmc.workshop.item.WorkshopItems.moldSettings;
import static io.github.cottonmc.workshop.item.WorkshopItems.register;

public class WorkshopMoldItems {
    public static void init() {
        // NO-OP
    }
    /*
    * Soft Mold Items
    */
    public static final Item WET_HANDLE = register("wet_handle_mold", new WetMoldItem(MoldTypes.HANDLE, moldSettings()));
    public static final Item WET_HILT = register("wet_hilt_mold", new WetMoldItem(MoldTypes.HILT, moldSettings()));
    public static final Item WET_BOLTS = register("wet_bolts_mold", new WetMoldItem(MoldTypes.BOLTS, moldSettings()));

    public static final Item WET_PICK_HEAD = register("wet_pick_head_mold", new WetMoldItem(MoldTypes.PICK_HEAD, moldSettings()));
    public static final Item WET_SHOVEL_HEAD = register("wet_shovel_head_mold", new WetMoldItem(MoldTypes.SHOVEL_HEAD, moldSettings()));
    public static final Item WET_AXE_HEAD = register("wet_axe_head_mold", new WetMoldItem(MoldTypes.AXE_HEAD, moldSettings()));
    public static final Item WET_HOE_HEAD = register("wet_hoe_head_mold", new WetMoldItem(MoldTypes.HOE_HEAD, moldSettings()));

    public static final Item WET_SCYTHE_HEAD = register("wet_scythe_head_mold", new WetMoldItem(MoldTypes.SCYTHE_HEAD, moldSettings()));
    public static final Item WET_PITCHFORK_HEAD = register("wet_pitchfork_head_mold", new WetMoldItem(MoldTypes.PITCHFORK_HEAD, moldSettings()));
    public static final Item WET_BILL_HEAD = register("wet_bill_head_mold", new WetMoldItem(MoldTypes.BILL_HEAD, moldSettings()));

    public static final Item WET_MATTOCK_HEAD = register("wet_mattock_head_mold", new WetMoldItem(MoldTypes.MATTOCK_HEAD, moldSettings()));
    public static final Item WET_SHOVELBLADE_HEAD = register("wet_shovelblade_head_mold", new WetMoldItem(MoldTypes.SHOVELBLADE_HEAD, moldSettings()));
    public static final Item WET_BREAKERBLADE_HEAD = register("wet_breakerblade_head_mold", new WetMoldItem(MoldTypes.BREAKERBLADE_HEAD, moldSettings()));

    public static final Item WET_SHORTSWORD_HEAD = register("wet_shortsword_head_mold", new WetMoldItem(MoldTypes.SHORTSWORD_HEAD, moldSettings()));
    public static final Item WET_LONGSWORD_HEAD = register("wet_longsword_head_mold", new WetMoldItem(MoldTypes.LONGSWORD_HEAD, moldSettings()));
    public static final Item WET_GREATSWORD_HEAD = register("wet_greatsword_head_mold", new WetMoldItem(MoldTypes.GREATSWORD_HEAD, moldSettings()));
    public static final Item WET_CLADHEMOR_HEAD = register("wet_cladhemor_head_mold", new WetMoldItem(MoldTypes.CLADHEMOR_HEAD, moldSettings()));

    public static final Item WET_KNIFE_HEAD = register("wet_knifehead_head_mold", new WetMoldItem(MoldTypes.KNIFE_HEAD, moldSettings()));
    public static final Item WET_MACE_HEAD = register("wet_mace_head_mold", new WetMoldItem(MoldTypes.MACE_HEAD, moldSettings()));
    public static final Item WET_BATTLEAXE_HEAD = register("wet_battleaxe_head_mold", new WetMoldItem(MoldTypes.BATTLEAXE_HEAD, moldSettings()));
    public static final Item WET_HALBRED_HEAD = register("wet_halbred_head_mold", new WetMoldItem(MoldTypes.HALBRED_HEAD, moldSettings()));
    public static final Item WET_WARHAMMER_HEAD = register("wet_warhammer_head_mold", new WetMoldItem(MoldTypes.WARHAMMER_HEAD, moldSettings()));

    /*
     * Fired Mold Items
     */
    public static final Item HANDLE = register("handle_mold", new FiredMoldItem(MoldTypes.HANDLE, moldSettings()));
    public static final Item HILT = register("hilt_mold", new FiredMoldItem(MoldTypes.HILT, moldSettings()));
    public static final Item BOLTS = register("bolts_mold", new FiredMoldItem(MoldTypes.BOLTS, moldSettings()));

    public static final Item PICK_HEAD = register("pick_head_mold", new FiredMoldItem(MoldTypes.PICK_HEAD, moldSettings()));
    public static final Item SHOVEL_HEAD = register("shovel_head_mold", new FiredMoldItem(MoldTypes.SHOVEL_HEAD, moldSettings()));
    public static final Item AXE_HEAD = register("axe_head_mold", new FiredMoldItem(MoldTypes.AXE_HEAD, moldSettings()));
    public static final Item HOE_HEAD = register("hoe_head_mold", new FiredMoldItem(MoldTypes.HOE_HEAD, moldSettings()));

    public static final Item SCYTHE_HEAD = register("scythe_head_mold", new FiredMoldItem(MoldTypes.SCYTHE_HEAD, moldSettings()));
    public static final Item PITCHFORK_HEAD = register("pitchfork_head_mold", new FiredMoldItem(MoldTypes.PITCHFORK_HEAD, moldSettings()));
    public static final Item BILL_HEAD = register("bill_head_mold", new FiredMoldItem(MoldTypes.BILL_HEAD, moldSettings()));

    public static final Item MATTOCK_HEAD = register("mattock_head_mold", new FiredMoldItem(MoldTypes.MATTOCK_HEAD, moldSettings()));
    public static final Item SHOVELBLADE_HEAD = register("shovelblade_head_mold", new FiredMoldItem(MoldTypes.SHOVELBLADE_HEAD, moldSettings()));
    public static final Item BREAKERBLADE_HEAD = register("breakerblade_head_mold", new FiredMoldItem(MoldTypes.BREAKERBLADE_HEAD, moldSettings()));

    public static final Item SHORTSWORD_HEAD = register("shortsword_head_mold", new FiredMoldItem(MoldTypes.SHORTSWORD_HEAD, moldSettings()));
    public static final Item LONGSWORD_HEAD = register("longsword_head_mold", new FiredMoldItem(MoldTypes.LONGSWORD_HEAD, moldSettings()));
    public static final Item GREATSWORD_HEAD = register("greatsword_head_mold", new FiredMoldItem(MoldTypes.GREATSWORD_HEAD, moldSettings()));
    public static final Item CLADHEMOR_HEAD = register("cladhemor_head_mold", new FiredMoldItem(MoldTypes.CLADHEMOR_HEAD, moldSettings()));

    public static final Item KNIFE_HEAD = register("knifehead_head_mold", new FiredMoldItem(MoldTypes.KNIFE_HEAD, moldSettings()));
    public static final Item MACE_HEAD = register("mace_head_mold", new FiredMoldItem(MoldTypes.MACE_HEAD, moldSettings()));
    public static final Item BATTLEAXE_HEAD = register("battleaxe_head_mold", new FiredMoldItem(MoldTypes.BATTLEAXE_HEAD, moldSettings()));
    public static final Item HALBRED_HEAD = register("halbred_head_mold", new FiredMoldItem(MoldTypes.HALBRED_HEAD, moldSettings()));
    public static final Item WARHAMMER_HEAD = register("warhammer_head_mold", new FiredMoldItem(MoldTypes.WARHAMMER_HEAD, moldSettings()));
}
