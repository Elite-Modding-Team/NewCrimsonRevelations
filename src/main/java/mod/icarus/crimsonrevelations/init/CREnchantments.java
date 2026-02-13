package mod.icarus.crimsonrevelations.init;

import com.google.common.collect.ImmutableSet;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import java.util.Set;

public class CREnchantments {
    // Infusion Enchantments
    // Enchantment Name, Tool Class, Max Level, Research
    public static EnumInfusionEnchantment BEHEADING = EnumHelper.addEnum(
            EnumInfusionEnchantment.class, "BEHEADING",
            new Class<?>[]{Set.class, int.class, String.class},
            ImmutableSet.of("weapon"), 5, "CR_BEHEADING_INFUSION"
    );

    public static EnumInfusionEnchantment CHAMELEON = EnumHelper.addEnum(
            EnumInfusionEnchantment.class, "CHAMELEON",
            new Class<?>[]{Set.class, int.class, String.class},
            ImmutableSet.of("axe", "pickaxe", "shovel", "weapon"),
            //Two levels might be a bit much since that gives 3 enchant slots. Also, you may want to add
            //  a tooltip or message when the chameleon slot changes. Just toss it into the CycleChameleon
            //  message handler.
            2, "CR_CHAMELEON_INFUSION"
    );

    public static EnumInfusionEnchantment VIS_ATTUNEMENT = EnumHelper.addEnum(
            EnumInfusionEnchantment.class, "VIS_ATTUNEMENT",
            new Class<?>[]{Set.class, int.class, String.class},
            ImmutableSet.of("armor"),
            //Keeping the infusion level to 3 so it is on par with the Goggles of Revealing, but does not
            // make the higher tier TA armors obsolete.
            3, "CR_VIS_ATTUNEMENT_INFUSION"
    );
}
