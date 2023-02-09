package com.pig.mod.registries;

import com.pig.mod.MyMod;
import com.pig.mod.blocks.CitrineLamp;
import com.pig.mod.blocks.SpeedBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MyMod.MOD_ID);
    public static final RegistryObject<Block> CITRINE_BLOCK = registerBlock("citrine_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_STAIRS = registerBlock("citrine_stairs",
            () -> new StairBlock(() -> BlockRegistry.CITRINE_BLOCK.get().defaultBlockState()
                    , BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_ORE = registerBlock("citrine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(4f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> DEEP_CITRINE_ORE = registerBlock("deepslate_citrine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(5f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> END_CITRINE_ORE = registerBlock("endstone_citrine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> NETHER_CITRINE_ORE = registerBlock("netherrack_citrine_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(2f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> SPEED_BLOCK = registerBlock("speed_block",
            () -> new SpeedBlock(BlockBehaviour.Properties.of(Material.AMETHYST).strength(5f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB, "block.tooltip.SpeedBlock");
    public static final RegistryObject<Block> CITRINE_SLAB = registerBlock("citrine_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_FENCE = registerBlock("citrine_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_FENCE_GATE = registerBlock("citrine_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_WALL = registerBlock("citrine_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_BUTTON = registerBlock("citrine_button",
            () -> new StoneButtonBlock(BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> CITRINE_PRESSURE_PLATE = registerBlock("citrine_pressure_plate",
            () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of(Material.METAL).strength(9f)
                    .requiresCorrectToolForDrops()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> EBONY_DOOR = registerBlock("ebony_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(9f)
                    .requiresCorrectToolForDrops().noOcclusion()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> EBONY_TRAPDOOR = registerBlock("ebony_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(9f)
                    .requiresCorrectToolForDrops().noOcclusion()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> PINK_ROSE = registerBlock("pink_rose",
            () -> new FlowerBlock(MobEffects.SATURATION, 20,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION).noOcclusion()), MyMod.MOD_TAB);
    public static final RegistryObject<Block> POTTED_PINK_ROSE = registerBlockWithoutBlockItem("potted_pink_rose",
            () -> new FlowerPotBlock(null, BlockRegistry.PINK_ROSE,
                    BlockBehaviour.Properties.copy(Blocks.DANDELION).noOcclusion()));
    public static final RegistryObject<Block> CITRINE_LAMP = registerBlock("citrine_lamp",
            () -> new CitrineLamp(BlockBehaviour.Properties.of(Material.AMETHYST).strength(5f)
                    .requiresCorrectToolForDrops().lightLevel((state) ->
                            state.getValue(CitrineLamp.OPEN) ? 15 : 0).sound(SoundRegistry.CITRINE_LAMP_SOUNDS)), MyMod.MOD_TAB);


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

    private static <T extends Block> RegistryObject<Item>
    registerBlockItem(String name, RegistryObject<T> block,
                      CreativeModeTab tab) {
        return ItemRegistry.ITEMS.register
                (name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item>
    registerBlockItem(String name, RegistryObject<T> block,
                      CreativeModeTab tab, String Tooltip) {
        return ItemRegistry.ITEMS.register
                (name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)) {
                    @Override
                    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                        pTooltip.add(new TranslatableComponent(Tooltip));
                    }
                });
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name
            , Supplier<T> block, CreativeModeTab tab, String Tooltip) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, Tooltip);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }
}
