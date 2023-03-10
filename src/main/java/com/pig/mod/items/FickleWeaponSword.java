package com.pig.mod.items;

import com.pig.mod.items.Interfaces.FickleWeaponInterface;
import com.pig.mod.util.FickleWeaponUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;

public class FickleWeaponSword extends SwordItem implements FickleWeaponInterface {
    public FickleWeaponSword(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(Tiers.NETHERITE, 10, 3.0f, pProperties);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pStack.hasTag()){
            if (pStack.getTag().getInt(EnergyUsed) > 0){
                pStack.getTag().putInt(EnergyUsed,pStack.getTag().getInt(EnergyUsed) - 1);
            }else if (pEntity instanceof Player player){
                FickleWeaponUtil.BackToFickleWeapon(pSlotId,player,pStack);
            }
        }else {
            pStack.setTag(NBT);
            pStack.getTag().putInt(EnergyUsed,MaxEnergy);
        }
    }

}
