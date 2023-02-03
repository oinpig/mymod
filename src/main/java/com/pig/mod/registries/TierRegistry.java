package com.pig.mod.registries;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class TierRegistry {
    public static final ForgeTier CITRINE = new ForgeTier(3, 2000, 2.0f, 2.0f, 100,
            BlockTags.NEEDS_IRON_TOOL, () -> Ingredient.of(ItemRegistry.EXAMPLE.get()));
}
