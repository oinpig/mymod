package com.pig.mod.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class SpeedBlock extends Block {
    public SpeedBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pLevel.isClientSide) {
            if (pEntity instanceof LivingEntity livingEntity) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 20, 3));
            }
        }
        super.stepOn(pLevel, pPos, pState, pEntity);
    }

}
