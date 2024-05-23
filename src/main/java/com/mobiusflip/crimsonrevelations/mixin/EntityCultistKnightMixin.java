package com.mobiusflip.crimsonrevelations.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;

@Mixin(value = EntityCultistKnight.class, remap = false)
public class EntityCultistKnightMixin extends EntityCultist {
    public EntityCultistKnightMixin(World world) {
        super(world);
        this.setDropChance(EntityEquipmentSlot.CHEST, 0.025F);
        this.setDropChance(EntityEquipmentSlot.FEET, 0.025F);
        this.setDropChance(EntityEquipmentSlot.HEAD, 0.025F);
        this.setDropChance(EntityEquipmentSlot.LEGS, 0.025F);
        this.setDropChance(EntityEquipmentSlot.MAINHAND, -1.0F);
    }

    // Replaces the original equip event with ours
    @Inject(method = "setLoot", at = @At(value = "HEAD"), cancellable = true)
    public void crSetLoot(DifficultyInstance diff, CallbackInfo ci) {
        ci.cancel();
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonPlateHelm));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonPlateChest));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonPlateLegs));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));

        if (this.rand.nextFloat() < 0.05F) {
            int i = this.rand.nextInt(5);

            if (i == 0) {
                this.setHeldItem(this.getActiveHand(), new ItemStack(ItemsTC.voidSword));
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonRobeHelm));
            } else {
                this.setHeldItem(this.getActiveHand(), new ItemStack(ItemsTC.thaumiumSword));
                this.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
            }
        } else {
            this.setHeldItem(this.getActiveHand(), new ItemStack(Items.IRON_SWORD));
        }
    }
}
