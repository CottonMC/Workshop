package io.github.cottonmc.workshop.block;

import io.github.cottonmc.workshop.Workshop;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class MoldTableBlock extends Block {
    private static final Text CONTAINER_NAME = new TranslatableText("workshop:container.moldtable");

    public MoldTableBlock(Settings settings) {
        super(settings);
    }

    @Override
    public boolean isTranslucent(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false; // This is in Settings in 1.15.
    }

    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult blockHitResult) {
        if (!world.isClient) {
            ContainerProviderRegistry.INSTANCE.openContainer(
                    Workshop.id("moldtable"),
                    playerEntity, (buf) -> buf.writeBlockPos(blockPos));
        }
        return true;
    }
}
