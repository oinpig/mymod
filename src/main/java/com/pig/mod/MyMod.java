package com.pig.mod;

import com.pig.mod.effect.EffectRegistery;
import com.pig.mod.registries.BlockRegistry;
import com.pig.mod.registries.ItemRegistry;
import com.pig.mod.registries.SoundRegistry;
import com.pig.mod.util.ModItemProperties;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("testmod")
public class MyMod {

    public static final CreativeModeTab MOD_TAB = new CreativeModeTab("testmod") {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ItemRegistry.EXAMPLE.get());
        }
    };
    public static final String MOD_ID = "testmod";

    public MyMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        BlockRegistry.register(bus);
        ItemRegistry.register(bus);
        SoundRegistry.register(bus);
        EffectRegistery.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::clientSetup);
        bus.addListener(this::setup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.EBONY_DOOR.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.EBONY_TRAPDOOR.get(), RenderType.translucent());

        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.PINK_ROSE.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.POTTED_PINK_ROSE.get(), RenderType.cutout());
        ModItemProperties.addCustomItemProperties();
    }

    private void setup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(BlockRegistry.PINK_ROSE.getId(), BlockRegistry.POTTED_PINK_ROSE);
        });
    }
}
