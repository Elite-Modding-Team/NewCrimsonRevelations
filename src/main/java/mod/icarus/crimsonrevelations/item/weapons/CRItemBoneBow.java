package mod.icarus.crimsonrevelations.item.weapons;

import mod.icarus.crimsonrevelations.item.CRItemBow;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class CRItemBoneBow extends CRItemBow {
    public CRItemBoneBow() {
        // Durability, Damage Multiplier, Velocity Multiplier, Draw Time Multipler, Inaccuracy, Rarity, Repair Material
        super(512, 1.15F, 1.5F, 0.8F, 0.8F, EnumRarity.RARE, Ingredient.fromStacks(new ItemStack(Items.BONE)));
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        int ticks = this.getMaxItemUseDuration(stack) - count;

        if (ticks > 18) {
            player.stopActiveHand();
        }
    }

    @Override
    public boolean canContinueUsing(ItemStack oldStack, ItemStack newStack) {
        return true;
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public int getItemEnchantability() {
        return 3;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repairMaterial.test(repair) || super.getIsRepairable(toRepair, repair);
    }
}
