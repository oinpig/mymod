package com.pig.mod.event;

import com.pig.mod.MyMod;
import com.pig.mod.event.loot.CoalCokeFromCreeperAdditionModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = MyMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final
                                                   RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
        event.getRegistry().registerAll(
                new CoalCokeFromCreeperAdditionModifier.Serializer().setRegistryName
                        (new ResourceLocation(MyMod.MOD_ID,"coal_coke_from_creeper"))
        );
    }
}
