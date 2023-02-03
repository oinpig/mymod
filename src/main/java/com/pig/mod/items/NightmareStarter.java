package com.pig.mod.items;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameRules;

public class NightmareStarter extends Item {
    public NightmareStarter(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            Player player = pContext.getPlayer().getServer().getPlayerList().getPlayer(pContext.getPlayer().getUUID());
            pContext.getLevel().getGameRules()
                    .getRule(GameRules.RULE_DAYLIGHT).set(false, pContext.getPlayer().getServer());
            ServerLevel serverLevel = pContext.getLevel().getServer().getLevel(pContext.getLevel().dimension());
            serverLevel.setDayTime(18000);

            pContext.getLevel().getServer().getPlayerList().broadcastMessage(new TranslatableComponent("item.nightmare_start")
                    , ChatType.CHAT, player.getUUID());
        }
        return super.useOn(pContext);
    }
}