package mod.icarus.crimsonrevelations.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import mod.icarus.crimsonrevelations.init.CREnchantments;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.common.items.casters.CasterManager;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

@Mixin(value = CasterManager.class, remap = false)
public class CasterManagerMixin {
    @ModifyReturnValue(method = "getTotalVisDiscount", at = @At("RETURN"))
    private static float visAttunementInfusionAdjustmentMixin(float original, @Local(argsOnly = true) EntityPlayer player) {
        for(ItemStack armor : player.inventory.armorInventory) {
            if(!armor.isEmpty() && !(armor.getItem() instanceof IVisDiscountGear)) {
                int level = EnumInfusionEnchantment.getInfusionEnchantmentLevel(armor, CREnchantments.VIS_ATTUNEMENT);
                if(level > 0) {
                    original += level;
                }
            }
        }
        return original;
    }
}
