package com.pig.mod.items;

import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;

public class ServerOutputTestItem extends Item {
    public ServerOutputTestItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide) {
            Player player = pContext.getPlayer();
            String component = new TranslatableComponent("item.output.test").getString();

            pContext.getLevel().getServer().getPlayerList().broadcastMessage(new TextComponent
                            ("§4" + pContext.getPlayer().getLevel().getServer().getSingleplayerName() + "§6" + component)
                    , ChatType.CHAT, player.getUUID());
        }
        return super.useOn(pContext);
    }
}
