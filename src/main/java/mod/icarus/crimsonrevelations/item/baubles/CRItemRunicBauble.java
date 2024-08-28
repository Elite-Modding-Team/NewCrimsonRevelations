package mod.icarus.crimsonrevelations.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.NonNullList;

public class CRItemRunicBauble extends CRItem implements IBauble {
    BaubleType type;
    EnumRarity rarity;
    int amount;

    public CRItemRunicBauble(BaubleType type, EnumRarity rarity, int amount) {
        super(rarity);
        this.maxStackSize = 1;
        this.type = type;
        this.rarity = rarity;
        this.amount = amount;
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return type;
    }

    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            ItemStack stack = new ItemStack(this);
            stack.setTagInfo("TC.RUNIC", new NBTTagByte((byte) amount));
            items.add(stack);
        }
    }
}
