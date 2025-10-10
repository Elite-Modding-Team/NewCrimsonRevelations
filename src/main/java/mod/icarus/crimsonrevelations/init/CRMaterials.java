package mod.icarus.crimsonrevelations.init;

import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.common.lib.SoundsTC;

public class CRMaterials {
    public static final ItemArmor.ArmorMaterial ARMOR_CULTIST_ARCHER = EnumHelper.addArmorMaterial("CULTIST_ARCHER", "CULTIST_ARCHER", 17, new int[]{2, 5, 5, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F).setRepairItem(new ItemStack(CRItems.CRIMSON_PLATE));
    public static final ItemArmor.ArmorMaterial BOOTS_COMET = EnumHelper.addArmorMaterial("COMET_BOOTS", "COMET_BOOTS", 30, new int[]{2, 0, 0, 0}, 25, SoundsTC.ice, 2.0F).setRepairItem(new ItemStack(Items.SNOWBALL));
    public static final ItemArmor.ArmorMaterial BOOTS_METEOR = EnumHelper.addArmorMaterial("METEOR_BOOTS", "METEOR_BOOTS", 30, new int[]{2, 0, 0, 0}, 25, SoundEvents.ITEM_FIRECHARGE_USE, 2.0F).setRepairItem(new ItemStack(Items.BLAZE_POWDER));
    public static final Item.ToolMaterial TOOL_CULTIST = EnumHelper.addToolMaterial("CULTIST", 3, 321, 7.5F, 2.5F, 20).setRepairItem(new ItemStack(CRItems.CRIMSON_PLATE));
}
