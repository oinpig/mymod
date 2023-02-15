package com.pig.mod.gui;

import com.pig.mod.MyMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = MyMod.MOD_ID)
public class GUIEvents {
    @SubscribeEvent
    public static void GUIRenderer(ScreenEvent event){
        Screen screen = event.getScreen();

    }
}
