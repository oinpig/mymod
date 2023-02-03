package com.pig.mod.items;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.GameRules;

public class Flare extends Item {
    public Flare(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            Player player = pContext.getPlayer();
            Player Allplayer = pContext.getPlayer().getServer().getPlayerList().getPlayer(pContext.getPlayer().getUUID());
            String playername = player.getName().toString();
            ServerLevel serverLevel = pContext.getLevel().getServer().getLevel(pContext.getLevel().dimension());

            pContext.getLevel().getGameRules()
                    .getRule(GameRules.RULE_DAYLIGHT).set(true, pContext.getPlayer().getServer());
            serverLevel.setDayTime(6000);
            pContext.getLevel().getServer().getPlayerList().broadcastMessage(new TranslatableComponent("item.flare.lit_sky")
                    , ChatType.CHAT, player.getUUID());
            pContext.getLevel().getServer().getPlayerList().broadcastMessage(new TextComponent("Player:" + player.getName())
                    , ChatType.CHAT, player.getUUID());


        }
        return super.useOn(pContext);
    }

}
