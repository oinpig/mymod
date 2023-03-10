package com.pig.mod.util;

import com.pig.mod.registries.ItemRegistry;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class FickleWeaponUtil {
    public static void FickleWeaponToSword(){

    }
    public static void FickleWeaponToBow(){

    }
    public static void BackToFickleWeapon(int SlotId, Player player, ItemStack stack){
        player.getInventory().removeItem(stack);
        player.getInventory().setItem(SlotId,new ItemStack(ItemRegistry.FICKLE_WEAPON.get()));
    }
}
