package mod.icarus.crimsonrevelations.mixin.event;

import mod.icarus.crimsonrevelations.init.CRItems;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import com.llamalad7.mixinextras.sugar.Local;

import baubles.api.BaublesApi;
import thaumcraft.common.lib.events.PlayerEvents;

@Mixin(value = PlayerEvents.class, remap = false)
public class TCPlayerEventsMixin {
    @ModifyConstant(method = "handleRunicArmor", constant = @Constant(intValue = 20, ordinal = 0))
    private static int chargedAmuletShieldingMixin(int constant, @Local(argsOnly = true) EntityPlayer player) {
        // Charged Ring of Shielding - Speeds up the recharging process of runic shielding by 50%.
        if (BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_RING_CHARGED) > 0) {
            return constant / 2;
        }

        return constant;
    }
}
