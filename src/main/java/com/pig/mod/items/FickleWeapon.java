package com.pig.mod.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
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
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class FickleWeapon extends Item implements Vanishable {

    private final Multimap<Attribute, AttributeModifier> defaultModifiers;

    public FickleWeapon(Properties pProperties) {
        super(pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", -2.0f, AttributeModifier.Operation.ADDITION));
        this.defaultModifiers = builder.build();
    }


    private final int MaxEnergy = 200;
    CompoundTag NBT = new CompoundTag();


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        //Dash when the item is a sword
        Dash(pPlayer);
        //Pull the bow when the item is a bow
        if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).hasTag()) {
            if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand") == 2 &&
                    pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon.changingProgress") == 10) {
                ItemStack itemstack = pPlayer.getItemInHand(InteractionHand.MAIN_HAND);
                boolean flag = true;

                InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, pLevel, pPlayer, InteractionHand.MAIN_HAND, flag);
                if (ret != null) return ret;

                if (!pPlayer.getAbilities().instabuild && !flag) {
                    return InteractionResultHolder.fail(itemstack);
                } else {
                    pPlayer.startUsingItem(InteractionHand.MAIN_HAND);
                    return InteractionResultHolder.consume(itemstack);
                }
            }
        }
        //random roll
        if (pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand") == 0
                && pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon.energyUsed") == 0) {
            int randMax = 90;
            int randMin = 1;
            int i = (int) Math.floor(Math.random() * (randMax - randMin + 1) + randMin);
            if (i <= 45) {
                pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().putInt("testmod.fickleweapon_rand", 2);
            } else if (i > 45 && i <= 90) {
                pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().putInt("testmod.fickleweapon_rand", 2);
            }
            System.out.println(pPlayer.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand"));
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (pStack.hasTag()) {
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 0) {
                pStack.getTag().putInt("testmod.fickleweapon.changingProgress", 0);
            }
            //Changing in progress
            if (pStack.getTag().getInt("testmod.fickleweapon.changingProgress") < 10
                    && pStack.getTag().getInt("testmod.fickleweapon_rand") != 0) {
                pStack.getTag().putInt("testmod.fickleweapon.changingProgress",
                        pStack.getTag().getInt("testmod.fickleweapon.changingProgress") + 1);
            } else if (pStack.getTag().getInt("testmod.fickleweapon_rand") != 0) {
                pStack.getTag().putInt("testmod.fickleweapon.changingProgress", 10);
            }
            //Charging up
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 0 &&
                    pStack.getTag().getInt("testmod.fickleweapon.energyUsed") > 0) {
                pStack.getTag().putInt("testmod.fickleweapon.energyUsed",
                        pStack.getTag().getInt("testmod.fickleweapon.energyUsed") - 1);
            }
            //Using energy
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") != 0) {
                int TimeLeft = MaxEnergy -
                        pStack.getTag().getInt("testmod.fickleweapon.energyUsed");
                pStack.getTag().putInt("testmod.fickleweapon.energyUsed",
                        pStack.getTag().getInt("testmod.fickleweapon.energyUsed") + 1);
                //After Using the Energy
                if (TimeLeft == 1) {
                    pStack.getTag().putInt("testmod.fickleweapon_rand", 0);
                    pStack.getTag().putInt("testmod.fickleweapon.changingProgress", 0);
                }
            }
            //messing with "Damage" NBT data
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 1) {
                pStack.getTag().putInt("testmod.fickleweapon.damage", 10);
            } else {
                pStack.getTag().putInt("testmod.fickleweapon.damage", 0);
            }
        } else {
            NBT.putInt("testmod.fickleweapon_rand", 0);
            NBT.putInt("testmod.fickleweapon.energyUsed", 0);
            pStack.setTag(NBT);
        }
        super.inventoryTick(pStack, pLevel, pEntity, pSlotId, pIsSelected);
    }

    @Override
    public int getBarColor(ItemStack pStack) {
        return Mth.hsvToRgb(0.5F, 1.0F, 1.0F);
    }

    @Override
    public boolean isBarVisible(ItemStack pStack) {
        if (pStack.hasTag()) {
            return pStack.getTag().getInt("testmod.fickleweapon.energyUsed") != 0;
        } else {
            return false;
        }
    }

    @Override
    public int getBarWidth(ItemStack pStack) {
        if (pStack.hasTag()) {
            return Math.round(13.0F - (float) pStack.getTag().getInt("testmod.fickleweapon.energyUsed") * 13.0F / (float) MaxEnergy);
        } else {
            return 0;
        }
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pTarget.hurt(DamageSource.playerAttack(pAttacker.getLevel().getPlayerByUUID(pAttacker.getUUID())), (float) pStack.getTag().getInt("testmod.fickleweapon.damage"));
        return super.hurtEnemy(pStack, pTarget, pAttacker);
    }

    //the bow part
    public void releaseUsing(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving, int pTimeLeft) {
        if (pStack.hasTag()) {
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 2) {
                if (pEntityLiving instanceof Player player) {
                    boolean flag = true;
                    ItemStack itemstack = player.getProjectile(pStack);

                    int i = this.getUseDuration(pStack) - pTimeLeft;
                    itemstack.isEmpty();
                    i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(pStack, pLevel, player, i, true);
                    if (i < 0) return;

                    itemstack.isEmpty();
                    if (itemstack.isEmpty()) {
                        itemstack = new ItemStack(Items.ARROW);
                    }

                    float f = getPowerForTime(i);
                    if (!((double) f < 0.1D)) {
                        boolean flag1 = true;
                        if (!pLevel.isClientSide) {
                            ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                            AbstractArrow abstractarrow = arrowitem.createArrow(pLevel, itemstack, player);
                            abstractarrow = customArrow(abstractarrow);
                            abstractarrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);
                            if (f == 1.0F) {
                                abstractarrow.setCritArrow(true);
                            }

                            int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, pStack);
                            if (j > 0) {
                                abstractarrow.setBaseDamage(abstractarrow.getBaseDamage() + (double) j * 0.5D + 0.5D);
                            }

                            int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, pStack);
                            if (k > 0) {
                                abstractarrow.setKnockback(k);
                            }

                            if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, pStack) > 0) {
                                abstractarrow.setSecondsOnFire(100);
                            }

                            pStack.hurtAndBreak(1, player, (p_40665_) -> {
                                p_40665_.broadcastBreakEvent(player.getUsedItemHand());
                            });
                            if (flag1 || player.getAbilities().instabuild && (itemstack.is(Items.SPECTRAL_ARROW) || itemstack.is(Items.TIPPED_ARROW))) {
                                abstractarrow.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                            }

                            pLevel.addFreshEntity(abstractarrow);
                        }

                        pLevel.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (pLevel.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                        if (!flag1 && !player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                player.getInventory().removeItem(itemstack);
                            }
                        }

                        player.awardStat(Stats.ITEM_USED.get(this));
                    }
                }
            }
        }
    }

    public static float getPowerForTime(int pCharge) {
        float f = (float) pCharge / 10.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    public int getUseDuration(ItemStack pStack) {
        int x = 0;
        if (pStack.hasTag()) {
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 2) {
                x = 72000;
            } else {
                x = 0;
            }
        }
        return x;
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        UseAnim useAnim = UseAnim.NONE;
        if (pStack.hasTag()) {
            if (pStack.getTag().getInt("testmod.fickleweapon_rand") == 2) {
                useAnim = UseAnim.BOW;
            }
        } else {
            useAnim = UseAnim.NONE;
        }
        return useAnim;
    }

    public AbstractArrow customArrow(AbstractArrow arrow) {
        return arrow;
    }

    public int getDefaultProjectileRange() {
        return 15;
    }


    //the dash method
    private void Dash(Player player) {
        if (player.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon_rand") == 1
                && player.getItemInHand(InteractionHand.MAIN_HAND).getTag().getInt("testmod.fickleweapon.changingProgress") >= 10) {
            Vec3 playerLookingAt = player.getLookAngle();
            player.setDeltaMovement(playerLookingAt.x * 3, 0, playerLookingAt.z * 3);
            player.getCooldowns().addCooldown(this, 20);
        }
    }

    //Pulling the bow
    private void PullBow() {

    }
}
