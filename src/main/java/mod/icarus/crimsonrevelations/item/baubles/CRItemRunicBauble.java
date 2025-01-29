package mod.icarus.crimsonrevelations.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.NonNullList;
import thaumcraft.common.lib.SoundsTC;

public class CRItemRunicBauble extends CRItemBauble implements IBauble {
    BaubleType type;
    EnumRarity rarity;
    int amount;

    public CRItemRunicBauble(BaubleType type, EnumRarity rarity, int amount) {
        super(type, rarity);
        this.maxStackSize = 1;
        this.type = type;
        this.rarity = rarity;
        this.amount = amount;
    }

    @Override
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        player.playSound(SoundsTC.hhon, 0.75F, 1.0F);
    }

    @Override
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        player.playSound(SoundsTC.hhoff, 0.75F, 1.0F);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ItemStack stack = new ItemStack(this);
            stack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) amount));
            items.add(stack);
        }
    }
}
