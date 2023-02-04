package com.pig.mod.util;

import com.google.common.collect.Maps;
import com.pig.mod.registries.ItemRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

import java.util.Map;

public class ModItemProperties {
    public static void register(IEventBus eventBus){

    }
    public static void addCustomItemProperties() {
        makeBow(ItemRegistry.KAUPENBOW.get());
        makeFickleItem(ItemRegistry.FICKLE_WEAPON.get());
    }

    private static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pul"), (itemStack, clientLevel, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            } else {
                return livingEntity.getUseItem() != itemStack ? 0.0F : (float) (itemStack.getUseDuration() -
                        livingEntity.getUseItemRemainingTicks()) / 10.0F;
            }
        });

        ItemProperties.register(item, new ResourceLocation("pullin"), (itemStack, clientLevel, livingEntity, i) -> {
            return livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F;
        });
    }

    private static void makeFickleItem(Item item){
        ItemProperties.register(item,new ResourceLocation("changing"),(pStack, pLevel, pEntity, pSeed) -> {
            if (pStack.getTag() != null){
                return (float) pStack.getTag().getInt("testmod.fickleweapon.changingProgress") / 10f;
            }else {
                return 0f;
            }
        });
        ItemProperties.register(item,new ResourceLocation("rand"),(pStack, pLevel, pEntity, pSeed) -> {
            if (pStack.getTag() != null){
                return (float) pStack.getTag().getInt("testmod.fickleweapon_rand");
            }else {
                return 0f;
            }
        });
        ItemProperties.register(item,new ResourceLocation("fickle_pull"),(pStack, pLevel, pEntity, pSeed) -> {
            float f = 0f;
            if (pStack.hasTag()){
                if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 2){
                    if (pEntity == null) {
                        f= 0.0F;
                    } else {
                        f= pEntity.getUseItem() != pStack ? 0.0F : (float) (pStack.getUseDuration() -
                                pEntity.getUseItemRemainingTicks()) / 10.0F;
                    }
                }
            }else {
                f=0f;
            }
            return f;
        });
        ItemProperties.register(item,new ResourceLocation("fickle_pulling"),(pStack, pLevel, pEntity, pSeed) -> {
            float f = 0f;
            if (pStack.hasTag()){
                if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 2){
                    f= pEntity != null && pEntity.isUsingItem() && pEntity.getUseItem() == pStack ? 1.0F : 0.0F;
                }
            }else {
                f= 0f;
            }
            return f;
        });
    }
}
