package com.mobiusflip.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCR extends Item {
    EnumRarity rarity;

    public ItemCR(EnumRarity rarity) {
        super();
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }
}
