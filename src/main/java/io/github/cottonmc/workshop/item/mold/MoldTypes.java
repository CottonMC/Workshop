package io.github.cottonmc.workshop.item.mold;

import com.google.common.collect.ImmutableMap;
import io.github.cottonmc.workshop.Workshop;
import net.minecraft.util.registry.DefaultedRegistry;

import java.util.HashMap;
import java.util.Map;

public final class MoldTypes {
    private static final DefaultedRegistry<MoldType> moldTypes = new DefaultedRegistry<>("invalid_recipe");
    private static Map<MoldType, HardMoldItem> MOLD_SMELT_RESULTS = new HashMap<>();

    // Base components
    public static final MoldType HANDLE = register(new MoldType(Workshop.id("handle")));
    public static final MoldType HILT = register(new MoldType(Workshop.id("hilt")));
    public static final MoldType BOLTS = register(new MoldType(Workshop.id("bolts")));

    // Tool Molds
    public static final MoldType PICK_HEAD = register(new MoldType(Workshop.id("pickaxe_head")));
    public static final MoldType SHOVEL_HEAD = register(new MoldType(Workshop.id("shovel_head")));
    public static final MoldType AXE_HEAD = register(new MoldType(Workshop.id("axe_head")));
    public static final MoldType HOE_HEAD = register(new MoldType(Workshop.id("hoe_head")));
        // Special Molds
    public static final MoldType SCYTHE_HEAD = register(new MoldType(Workshop.id("scythe_head")));
    public static final MoldType PITCHFORK_HEAD = register(new MoldType(Workshop.id("pitchfork_head")));
    public static final MoldType BILL_HEAD = register(new MoldType(Workshop.id("bill_head")));
        // Advanced Molds
    public static final MoldType MATTOCK_HEAD = register(new MoldType(Workshop.id("mattock_head")));
    public static final MoldType SHOVELBLADE_HEAD = register(new MoldType(Workshop.id("shovelblade_head")));
    public static final MoldType BREAKERBLADE_HEAD = register(new MoldType(Workshop.id("breakerblade_head")));

    // Sword Head Molds
    public static final MoldType SHORTSWORD_HEAD = register(new MoldType(Workshop.id("shortsword_head")));
    public static final MoldType LONGSWORD_HEAD = register(new MoldType(Workshop.id("longsword_head")));
    public static final MoldType GREATSWORD_HEAD = register(new MoldType(Workshop.id("greatsword_head")));

    // Special Molds
    public static final MoldType CLADHEMOR_HEAD = register(new MoldType(Workshop.id("cladhemor_head")));

    // Knife Molds
    public static final MoldType KNIFE_HEAD = register(new MoldType(Workshop.id("knife_head")));

    // Weapon Molds
    public static final MoldType MACE_HEAD = register(new MoldType(Workshop.id("mace_head")));
    public static final MoldType BATTLEAXE_HEAD = register(new MoldType(Workshop.id("battleaxe_head")));
    public static final MoldType HALBRED_HEAD = register(new MoldType(Workshop.id("halbred_head")));
    public static final MoldType WARHAMMER_HEAD = register(new MoldType(Workshop.id("warhammer_head")));

    private MoldTypes() {
    }

    public static void init() {
    }

    public static MoldType register(MoldType type) {
        if(Workshop.isLocked()) {
            throw new UnsupportedOperationException("Cannot register new MoldTypes after game is loaded.");
        }

        return moldTypes.add(type.getIdentifier(), type);
    }

    public static void setHardenedType(MoldType type, HardMoldItem result) {
        MOLD_SMELT_RESULTS.put(type, result);
    }

    public static ImmutableMap<MoldType, HardMoldItem> getTypeToHardenedMoldMap() {
        return ImmutableMap.copyOf(MOLD_SMELT_RESULTS);
    }
}
