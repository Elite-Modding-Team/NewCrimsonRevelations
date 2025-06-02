package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;

public class CRItemPickaxe extends ItemPickaxe {
    EnumRarity rarity;

    public CRItemPickaxe(ToolMaterial material, EnumRarity rarity) {
        super(material);
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }
}
