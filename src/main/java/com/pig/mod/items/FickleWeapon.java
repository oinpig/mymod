package com.pig.mod.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FickleWeapon extends Item implements Vanishable {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;
    public FickleWeapon(Properties pProperties) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", (float)-2.0f, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }



    private final int MaxEnergy = 100;
    CompoundTag NBT = new CompoundTag();




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //put tags in ItemStack
        //In "rand": 0 = nothing  1=sword  2=bow
        pPlayer.getItemInHand(InteractionHand.MAIN_HAND).setTag(NBT);
        NBT.putInt("testmod.fickleweapon_rand",0);
        NBT.putInt("testmod.fickleweapon.energyUsed",0);
        if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag()
                .getInt("testmod.fickleweapon.changingProgress") == Integer.parseInt(null)){
            NBT.putInt("testmod.fickleweapon.changingProgress",0);
        }else {
            NBT.putInt("testmod.fickleweapon.changingProgress",
                    pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon.changingProgress"));
        }

        //Dash when the item is a sword
        Dash(pPlayer);
        //Pull the bow when the item is a bow
        PullBow();
        //random roll
        if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand") == 0
                && pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon.energyUsed") == 0) {
                int randMax = 90;
                int randMin = 1;
                int i = (int) Math.floor(Math.random() * (randMax - randMin + 1) + randMin);
                if (i <= 45) {
                    pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().putInt("testmod.fickleweapon_rand",1);
                } else if (i > 45 && i <= 90) {
                    pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().putInt("testmod.fickleweapon_rand",1);
                }
                System.out.println(pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand"));
            }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        //Changing in progress
        if (pStack.getTag().getInt("testmod.fickleweapon.changingProgress") < 10
                && pStack.getTag().getInt("testmod.fickleweapon_rand") != 0) {
            pStack.getTag().putInt("testmod.fickleweapon.changingProgress",
                    pStack.getTag().getInt("testmod.fickleweapon.changingProgress") + 1);
        } else if (pStack.getTag().getInt("testmod.fickleweapon_rand") != 0){
            pStack.getTag().putInt("testmod.fickleweapon.changingProgress",10);
        }
        //Charging up
        if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 0 &&
                pStack.getTag().getInt("testmod.fickleweapon.energyUsed") > 0) {
            pStack.getTag().putInt("testmod.fickleweapon.energyUsed",
                    pStack.getTag().getInt("testmod.fickleweapon.energyUsed") -1);
        }
        //Using energy
        if (pStack.getTag().getInt("testmod.fickleweapon_rand") != 0){
            int TimeLeft = MaxEnergy -
                    pStack.getTag().getInt("testmod.fickleweapon.energyUsed");
            pStack.getTag().putInt("testmod.fickleweapon.energyUsed",
                    pStack.getTag().getInt("testmod.fickleweapon.energyUsed")+1);
            //After Using the Energy
            if (TimeLeft == 1){
                pStack.getTag().putInt("testmod.fickleweapon_rand",0);
                pStack.getTag().putInt("testmod.fickleweapon.changingProgress",0);
            }
        }
        //messing with "Damage" NBT data
        if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 1){
            pStack.getTag().putInt("testmod.fickleweapon.damage",10);
        }else {
            pStack.getTag().putInt("testmod.fickleweapon.damage",0);
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Mth.hsvToRgb( 0.5F, 1.0F, 1.0F);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        return pStack.getTag().getInt("testmod.fickleweapon.energyUsed") != 0;
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        return Math.round(13.0F - (float)pStack.getTag().getInt("testmod.fickleweapon.energyUsed") * 13.0F / (float)MaxEnergy);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.hurt(DamageSource.playerAttack(pAttacker.getLevel().getPlayerByUUID(pAttacker.getUUID())),(float)pStack.getTag().getInt("testmod.fickleweapon.damage"));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    //the dash method
    private void Dash(Player player){
        if (player.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand") == 1
                && player.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon.changingProgress") >= 10) {
            Vec3 playerLookingAt = player.getLookAngle();
            player.setDeltaMovement(playerLookingAt.x * 3, 0, playerLookingAt.z * 3);
            player.getCooldowns().addCooldown(this, 20);
        }
    }
    //Pulling the bow
    private void PullBow(){

    }
}
