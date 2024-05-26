package com.icarus.crimsonrevelations.item;

import com.icarus.crimsonrevelations.init.RegistryHandler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;

public class ItemCRSword extends ItemSword {
    EnumRarity rarity;

    public ItemCRSword(ToolMaterial material, EnumRarity rarity) {
        super(material);
        this.rarity = rarity;
    }

    @Override
    public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
        if (this == RegistryHandler.crimsonSword)
            target.addPotionEffect(new PotionEffect(MobEffects.POISON, 6 * 20, 1));

        stack.damageItem(1, attacker);
        return true;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }
}
