package com.pig.mod.items;

import com.pig.mod.items.Interfaces.FickleWeaponInterface;
import com.pig.mod.util.FickleWeaponUtil;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FicklwWeaponBow extends BowItem implements FickleWeaponInterface {
    public FicklwWeaponBow(Properties p_40660_) {
        super(p_40660_);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pStack.hasTag()){
            if (pStack.getTag().getInt(EnergyUsed) > 0){
                pStack.getTag().putInt(EnergyUsed,pStack.getTag().getInt(EnergyUsed) - 1);
            }else if (pEntity instanceof Player player){
                FickleWeaponUtil.BackToFickleWeapon(pSlotId,player,pStack);
            }
        }
    }
}
