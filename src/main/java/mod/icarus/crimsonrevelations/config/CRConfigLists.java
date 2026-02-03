package mod.icarus.crimsonrevelations.config;

import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;

public class CRConfigLists {
    public static List<Potion> manaBeanEffects = new ArrayList<>();

    public static void initLists() {
        manaBeanEffects.clear();

        try {
            for (String entry : CRConfig.mana_beans.effectList) {
                ResourceLocation resLoc = new ResourceLocation(entry);

                if (ForgeRegistries.POTIONS.containsKey(resLoc)) {
                    manaBeanEffects.add(ForgeRegistries.POTIONS.getValue(resLoc));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
