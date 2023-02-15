package com.pig.mod.effect;

import com.pig.mod.MyMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EffectRegistery {
    public static final DeferredRegister<MobEffect> MOB_EFFECT
            =DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, MyMod.MOD_ID);

    public static void register(IEventBus eventBus){
        MOB_EFFECT.register(eventBus);
    }


    public static final RegistryObject<MobEffect> FREEZE
            = MOB_EFFECT.register("freeze",()->new FreezeEffect(MobEffectCategory.HARMFUL,1));
}
