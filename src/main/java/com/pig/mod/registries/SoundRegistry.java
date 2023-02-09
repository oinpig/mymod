package com.pig.mod.registries;

import com.pig.mod.MyMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundRegistry {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MyMod.MOD_ID);

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENT.register(name, () -> new SoundEvent(new ResourceLocation(MyMod.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }

    public static final RegistryObject<SoundEvent> DOWSING_ROD_FOUND_ORE =
            registerSoundEvent("dowsing_rod_found_ore");

    public static RegistryObject<SoundEvent> CITRINE_LAMP_BREAK = registerSoundEvent("citrine_lamp_break");
    public static RegistryObject<SoundEvent> CITRINE_LAMP_STEP = registerSoundEvent("citrine_lamp_step");
    public static RegistryObject<SoundEvent> CITRINE_LAMP_PLACE = registerSoundEvent("citrine_lamp_place");
    public static RegistryObject<SoundEvent> CITRINE_LAMP_HIT = registerSoundEvent("citrine_lamp_hit");
    public static RegistryObject<SoundEvent> CITRINE_LAMP_FALL = registerSoundEvent("citrine_lamp_fall");


    public static final ForgeSoundType CITRINE_LAMP_SOUNDS = new ForgeSoundType(1f, 1f,
            SoundRegistry.CITRINE_LAMP_BREAK, SoundRegistry.CITRINE_LAMP_STEP, SoundRegistry.CITRINE_LAMP_PLACE,
            SoundRegistry.CITRINE_LAMP_HIT, SoundRegistry.CITRINE_LAMP_FALL);

}
