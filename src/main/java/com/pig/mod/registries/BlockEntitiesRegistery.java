package com.pig.mod.registries;

import com.pig.mod.MyMod;
import com.pig.mod.blocks.BlockEntity.GemCutterEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntitiesRegistery {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MyMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<GemCutterEntity>> GEM_CUTTER_ENTITY = BLOCK_ENTITIES.register("gem_cutter_entity",
            () -> BlockEntityType.Builder.of(GemCutterEntity::new , BlockRegistry.GEM_CUTER.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }

}
