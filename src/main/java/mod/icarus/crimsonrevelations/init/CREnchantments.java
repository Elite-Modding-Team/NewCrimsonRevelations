package mod.icarus.crimsonrevelations.init;

import com.google.common.collect.ImmutableSet;
import net.minecraftforge.common.util.EnumHelper;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import java.util.*;

// TODO: Add recipe and research
public class CREnchantments {
    // Infusion Enchantments
    // Enchantment Name, Tool Class, Max Level, Research
    public static EnumInfusionEnchantment BEHEADING = EnumHelper.addEnum(
            EnumInfusionEnchantment.class, "BEHEADING",
            new Class<?>[]{Set.class, int.class, String.class},
            ImmutableSet.of("weapon"), 5, "CR_BEHEADING_INFUSION"
    );
}
