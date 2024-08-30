package mod.icarus.crimsonrevelations.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class CRItemBauble extends CRItem implements IBauble {
    BaubleType type;
    EnumRarity rarity;

    public CRItemBauble(BaubleType type, EnumRarity rarity) {
        super(rarity);
        this.maxStackSize = 1;
        this.type = type;
        this.rarity = rarity;
    }

    @Override
    public BaubleType getBaubleType(ItemStack stack) {
        return type;
    }
}
