package io.github.cottonmc.workshop.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cottonmc.workshop.Workshop;
import io.github.cottonmc.workshop.container.MoldTableContainer;
import io.github.cottonmc.workshop.inventory.MoldTableInventory;
import io.github.cottonmc.workshop.recipe.moldtable.MoldTableRecipe;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.AbstractContainerScreen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import java.util.List;

@Environment(EnvType.CLIENT)
public class MoldTableScreen extends AbstractContainerScreen<MoldTableContainer> {
    private static final Identifier TEXTURE = Workshop.id("textures/gui/container/moldtable.png");
    private boolean mouseClicked;
    private boolean canCraft;
    private int scrollOffset;
    private float scrollAmount;

    public MoldTableScreen(MoldTableContainer moldTableContainer, PlayerInventory playerInventory, Text title) {
        super(moldTableContainer, playerInventory, title);
        moldTableContainer.setContentsChangedListener(this::onInventoryChange);
    }

    protected void drawForeground(int mouseX, int mouseY) {
        this.font.draw(this.title.asFormattedString(), 8.0F, 6.0F, 4210752);
        this.font.draw(this.playerInventory.getDisplayName().asFormattedString(), 8.0F, this.containerHeight - 96 + 2, 4210752);
    }

    public void render(int mouseX, int mouseY, float delta) {
        this.renderBackground();
        this.drawBackground(delta, mouseX, mouseY);
        super.render(mouseX, mouseY, delta);
        this.drawMouseoverTooltip(mouseX, mouseY);
    }

    @Override
    protected void drawBackground(float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bindTexture(TEXTURE);
        int i = (this.width - this.containerWidth) / 2;
        int j = (this.height - this.containerHeight) / 2;
        this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);

        if ((this.container.getSlot(MoldTableInventory.INPUT).hasStack() || this.container.getSlot(MoldTableInventory.CARVER).hasStack()) && !this.container.getSlot(MoldTableInventory.OUTPUT).hasStack()) {
            this.blit(i + 92, j + 31, this.containerWidth, 0, 28, 21);
        }

        this.blit(i, j, 0, 0, this.containerWidth, this.containerHeight);
        int k = (int)(41.0F * this.scrollAmount);
        this.blit(i + 119, j + 15 + k, 176 + (this.shouldScroll() ? 0 : 12), 0, 12, 15);
        int l = this.x + 52;
        int m = this.y + 14;
        int n = this.scrollOffset + 12;
        this.renderScrollWheel(mouseX, mouseY, l, m, n);
        this.renderChoices(l, m, n);
    }

    private void renderScrollWheel(int i, int j, int k, int l, int m) {
        for(int n = this.scrollOffset; n < m && n < this.container.getAvailableRecipeCount(); ++n) {
            int o = n - this.scrollOffset;
            int p = k + o % 4 * 16;
            int q = o / 4;
            int r = l + q * 18 + 2;
            int s = this.containerHeight;
            if (n == this.container.getSelectedRecipe()) {
                s += 18;
            } else if (i >= p && j >= r && i < p + 16 && j < r + 18) {
                s += 36;
            }

            this.blit(p, r - 1, 0, s, 16, 18);
        }

    }

    private void renderChoices(int i, int j, int k) {
        List<MoldTableRecipe> list = this.container.getAvailableRecipes();

        for(int l = this.scrollOffset; l < k && l < this.container.getAvailableRecipeCount(); ++l) {
            int m = l - this.scrollOffset;
            int n = i + m % 4 * 16;
            int o = m / 4;
            int p = j + o * 18 + 2;
            this.minecraft.getItemRenderer().renderGuiItem(list.get(l).getOutput(), n, p);
        }

    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        this.mouseClicked = false;
        if (this.canCraft) {
            int i = this.x + 52;
            int j = this.y + 14;
            int k = this.scrollOffset + 12;

            for(int l = this.scrollOffset; l < k; ++l) {
                int m = l - this.scrollOffset;
                double d = mouseX - (i + m % 4 * 16);
                double e = mouseY - (j + m / 4 * 18);
                if (d >= 0.0D && e >= 0.0D && d < 16.0D && e < 18.0D && this.container.onButtonClick(this.minecraft.player, l)) {
                    MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_STONECUTTER_SELECT_RECIPE, 1.0F));
                    this.minecraft.interactionManager.clickButton(this.container.syncId, l);
                    return true;
                }
            }

            i = this.x + 119;
            j = this.y + 9;
            if (mouseX >= i && mouseX < (i + 12) && mouseY >= j && mouseY < (j + 54)) {
                this.mouseClicked = true;
            }
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        if (this.mouseClicked && this.shouldScroll()) {
            int i = this.y + 14;
            int j = i + 54;
            this.scrollAmount = ((float)mouseY - i - 7.5F) / ((j - i) - 15.0F);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int)((this.scrollAmount * this.getMaxScroll()) + 0.5D) * 4;
            return true;
        } else {
            return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
        }
    }

    public boolean mouseScrolled(double d, double e, double amount) {
        if (this.shouldScroll()) {
            int i = this.getMaxScroll();
            this.scrollAmount = (float)(this.scrollAmount - amount / i);
            this.scrollAmount = MathHelper.clamp(this.scrollAmount, 0.0F, 1.0F);
            this.scrollOffset = (int)((this.scrollAmount * i) + 0.5D) * 4;
        }

        return true;
    }

    private boolean shouldScroll() {
        return this.canCraft && (this.container.getAvailableRecipeCount() > 12);
    }

    protected int getMaxScroll() {
        return (this.container.getAvailableRecipeCount() + 4 - 1) / 4 - 3;
    }

    private void onInventoryChange() {
        this.canCraft = this.container.canCraft();
        if (!this.canCraft) {
            this.scrollAmount = 0.0F;
            this.scrollOffset = 0;
        }
    }
}
