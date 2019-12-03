package io.github.cottonmc.workshop.block;


import io.github.cottonmc.workshop.impl.HitPosGetter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockerBlock extends Block {
	public BlockerBlock(Settings settings) {
		super(settings);
	}

	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.INVISIBLE;
	}

	@Override
	public PistonBehavior getPistonBehavior(BlockState state) {
		return PistonBehavior.BLOCK;
	}

	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, EntityContext ctx) {
		//TODO: support for more locations
		return world.getBlockState(pos.down()).getOutlineShape(world, pos.down()).offset(0, -1, 0);
	}

	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
		//TODO: support for more locations
		BlockHitResult relocHit = new BlockHitResult(((HitPosGetter)hit).workshop_getPos(), hit.getSide(), hit.getBlockPos().down(), hit.isInsideBlock());
		return world.getBlockState(pos.down()).onUse(world, player, hand, relocHit);
	}

	@Override
	public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
		//TODO: support for more locations
		world.breakBlock(pos.down(), !player.isCreative());
	}

	@Override
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		//TODO: support for more locations
		return world.getBlockState(pos.down()).getBlock().getPickStack(world, pos.down(), state);
	}
}
