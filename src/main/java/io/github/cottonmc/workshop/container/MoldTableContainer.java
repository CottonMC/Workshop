package io.github.cottonmc.workshop.container;

import io.github.cottonmc.workshop.inventory.MoldTableInventory;
import io.github.cottonmc.workshop.inventory.slot.TagPredicatedSlot;
import io.github.cottonmc.workshop.recipe.WorkshopRecipeTypes;
import io.github.cottonmc.workshop.recipe.moldtable.MoldTableRecipe;
import io.github.cottonmc.workshop.util.WorkshopPredicates;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.container.BlockContext;
import net.minecraft.container.Container;
import net.minecraft.container.Property;
import net.minecraft.container.Slot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoldTableContainer extends Container {
    private final MoldTableInventory craftingInv;
    private final BlockContext context;
    private final PlayerEntity player;
    private final PlayerInventory playerInventory;
    private final CraftingResultInventory craftingResultInv;
    final Slot inputSlot;
    final Slot outputSlot;
    final Slot carverSlot;
    private ItemStack cachedInputStack = ItemStack.EMPTY;
    private ItemStack cachedCarverStack = ItemStack.EMPTY;
    private List<MoldTableRecipe> availableRecipes = new ArrayList<>();
    private Property selectedRecipe = Property.create();
    private World world;
    public Runnable contentsChangedListener;

    public MoldTableContainer(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, BlockContext.EMPTY);
    }

    public MoldTableContainer(int syncId, PlayerInventory playerInventory, BlockContext context) {
        super(null, syncId);
        this.world = playerInventory.player.world;
        this.craftingInv = new MoldTableInventory(this);
        this.context = context;
        this.contentsChangedListener = () -> {
        };
        this.playerInventory = playerInventory;
        this.player = playerInventory.player;
        this.craftingResultInv = new CraftingResultInventory();
        this.inputSlot = new Slot(craftingInv, 0, 29, 19);
        this.carverSlot = new TagPredicatedSlot(craftingInv, 1, 29, 40, WorkshopPredicates::isKnifeItem);
        this.outputSlot = new Slot(craftingResultInv, 2, 129, 34) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }

            @Override
            public ItemStack onTakeItem(PlayerEntity player, ItemStack itemStack) {
                craftingInv.setInvStack(MoldTableInventory.INPUT, ItemStack.EMPTY);
                ItemStack stack = craftingInv.getInvStack(MoldTableInventory.CARVER).copy();
                stack.damage(1, new Random(), null);
                craftingInv.setInvStack(MoldTableInventory.CARVER, stack);
                return itemStack;
            }
        };

        this.addSlot(inputSlot);
        this.addSlot(carverSlot);
        this.addSlot(outputSlot);

        for(int ySlotPos = 0; ySlotPos < 3; ++ySlotPos) {
            for(int xSlotPos = 0; xSlotPos < 9; ++xSlotPos) {
                this.addSlot(new Slot(playerInventory, xSlotPos + ySlotPos * 9 + 9, 8 + xSlotPos * 18, 84 + ySlotPos * 18));
            }
        }

        for(int xSlotPos = 0; xSlotPos < 9; ++xSlotPos) {
            this.addSlot(new Slot(playerInventory, xSlotPos, 8 + xSlotPos * 18, 142));
        }
    }

    public boolean onButtonClick(PlayerEntity player, int id) {
        if (id >= 0 && id < this.availableRecipes.size()) {
            this.selectedRecipe.set(id);
            this.populateResult();
        }

        return true;
    }

    private void populateResult() {
        if (!this.availableRecipes.isEmpty()) {
            MoldTableRecipe moldTableRecipe = this.availableRecipes.get(this.selectedRecipe.get());
            this.outputSlot.setStack(moldTableRecipe.craft(this.craftingInv));
        } else {
            this.outputSlot.setStack(ItemStack.EMPTY);
        }

        this.sendContentUpdates();
    }

    public boolean canInsertIntoSlot(ItemStack stack, Slot slot) {
        return slot.inventory != this.craftingResultInv && super.canInsertIntoSlot(stack, slot);
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public void onContentChanged(Inventory inventory) {
        ItemStack inputStack = this.getSlot(MoldTableInventory.INPUT).getStack();
        ItemStack carverStack = this.getSlot(MoldTableInventory.CARVER).getStack();

        if (inputStack.getItem() != this.cachedInputStack.getItem() || carverStack.getItem() != this.cachedCarverStack.getItem()) {
            this.cachedInputStack = inputStack.copy();
            this.cachedCarverStack = carverStack.copy();
            this.updateInput(inventory, inputStack);
        }

    }

    private void updateInput(Inventory inventory, ItemStack itemStack) {
        this.availableRecipes.clear();
        this.selectedRecipe.set(-1);
        this.outputSlot.setStack(ItemStack.EMPTY);
        if (!itemStack.isEmpty()) {
            this.availableRecipes = this.world.getRecipeManager().getAllMatches(WorkshopRecipeTypes.MOLDS, this.craftingInv, this.world);
        }
    }

    public void close(PlayerEntity player) {
        super.close(player);
        this.craftingResultInv.removeInvStack(1);
        this.context.run((world, blockPos) -> {
            this.dropInventory(player, player.world, this.craftingInv);
        });
    }

    @Environment(EnvType.CLIENT)
    public int getSelectedRecipe() {
        return this.selectedRecipe.get();
    }

    @Environment(EnvType.CLIENT)
    public List<MoldTableRecipe> getAvailableRecipes() {
        return this.availableRecipes;
    }

    @Environment(EnvType.CLIENT)
    public int getAvailableRecipeCount() {
        return this.availableRecipes.size();
    }

    @Environment(EnvType.CLIENT)
    public boolean canCraft() {
        return this.inputSlot.hasStack() && !this.availableRecipes.isEmpty();
    }

    @Environment(EnvType.CLIENT)
    public void setContentsChangedListener(Runnable runnable) {
        this.contentsChangedListener = runnable;
    }
}
