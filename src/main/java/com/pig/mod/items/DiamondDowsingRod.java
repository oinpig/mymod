package com.pig.mod.items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class DiamondDowsingRod extends Item {
    public DiamondDowsingRod(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if (isValuableBlock(blockBelow)) {
                    outputfound(player);
                    Coordinates(positionClicked.below(i), player);
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendMessage(new TranslatableComponent("item.testmod.diamond_dowsing_rod.no_valuables"),
                        player.getUUID());
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));
        return super.useOn(pContext);
    }

    private boolean isValuableBlock(Block block) {
        return block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE;
    }


    private void outputfound(Player player) {
        player.sendMessage(new TranslatableComponent("item.testmod.diamond_dowsing_rod.found_sth"), player.getUUID());
    }

    private void Coordinates(BlockPos pos, Player player) {
        player.sendMessage(new TextComponent("[" + pos.getX() + "," + pos.getY()
                + "," + pos.getZ() + "]"), player.getUUID());
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BLOCK;
    }
}
