package com.pig.mod.registries;

import com.pig.mod.MyMod;
import com.pig.mod.items.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MyMod.MOD_ID);

    public static final RegistryObject<Item> EXAMPLE = ITEMS.register("example_item", () ->
            new BowItem(new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> DIAMOND_ROD = ITEMS.register("diamond_dowsing_rod", () -> new DiamondDowsingRod(
            new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> LAVA_ROD = ITEMS.register("lava_dowsing_rod", () -> new LavaDowsingRod(
            new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> SUPER_COAL = ITEMS.register("super_coal", () -> new SuperCoal(
            new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> NIGHTMARE = ITEMS.register("nightmare_starter", () -> new NightmareStarter(
            new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> FLARE = ITEMS.register("flare", () -> new Flare(
            new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> CIRTINE_SWORD = ITEMS.register("citrine_sword",
            () -> new SwordItem(TierRegistry.CITRINE, 2, 1.0f,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_PICKAXE = ITEMS.register("citrine_pickaxe",
            () -> new PickaxeItem(TierRegistry.CITRINE, 2, 1.0f,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_AXE = ITEMS.register("citrine_axe",
            () -> new AxeItem(TierRegistry.CITRINE, 2, 1.0f,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_SHOVEL = ITEMS.register("citrine_shovel",
            () -> new ShovelItem(TierRegistry.CITRINE, 0, 0f,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_HOE = ITEMS.register("citrine_hoe",
            () -> new HoeItem(TierRegistry.CITRINE, 0, 0f,
                    new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> WUHU_SWORD = ITEMS.register("wuhu_sword",
            () -> new WuHuSword(TierRegistry.CITRINE, 2, 1.0f,
                    new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> OUTPUTTEST = ITEMS.register("output_test", () -> new ServerOutputTestItem(
            new Item.Properties().tab(MyMod.MOD_TAB)));

    public static final RegistryObject<Item> CIRTINE_HELMET = ITEMS.register("citrine_helmet",
            () -> new ModArmorItem(ModArmorMatirialsRegistry.CITRINE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_CHESTPLATE = ITEMS.register("citrine_chestplate",
            () -> new ModArmorItem(ModArmorMatirialsRegistry.CITRINE, EquipmentSlot.CHEST,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_LEGGING = ITEMS.register("citrine_legging",
            () -> new ModArmorItem(ModArmorMatirialsRegistry.CITRINE, EquipmentSlot.LEGS,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> CIRTINE_BOOTS = ITEMS.register("citrine_boots",
            () -> new ModArmorItem(ModArmorMatirialsRegistry.CITRINE, EquipmentSlot.FEET,
                    new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> MAGIC_DUST = ITEMS.register("magic_dust",
            () -> new Item(new Item.Properties().tab(MyMod.MOD_TAB)));
    public static final RegistryObject<Item> KAUPENBOW = ITEMS.register("kaupenbow",
            () -> new Kaupenbow(new Item.Properties().tab(MyMod.MOD_TAB).durability(500)));
    public static final RegistryObject<Item> FICKLE_WEAPON = ITEMS.register("fickle_weapon",
            () -> new FickleWeapon(new Item.Properties().tab(MyMod.MOD_TAB).stacksTo(1).fireResistant()));

    public static final RegistryObject<Item> CITRINE_STAFF = ITEMS.register("citrine_staff", () -> new Item(
            new Item.Properties().tab(MyMod.MOD_TAB).stacksTo(1).fireResistant()));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
