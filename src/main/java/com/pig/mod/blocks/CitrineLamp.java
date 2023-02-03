package com.pig.mod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class CitrineLamp extends Block {
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    public CitrineLamp(Properties p_49795_) {
        super(p_49795_);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(OPEN);
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pIsMoving) {
        pState.setValue(OPEN, true);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos
            , Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND) {
            boolean currentstate = pState.getValue(OPEN);
            pLevel.setBlock(pPos, pState.setValue(OPEN, !currentstate), 3);
        }
        return InteractionResult.SUCCESS;
    }
}
