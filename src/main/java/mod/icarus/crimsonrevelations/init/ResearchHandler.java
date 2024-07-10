package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import mod.icarus.crimsonrevelations.core.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.item.foci.FocusEffectBlindingFlash;
import mod.icarus.crimsonrevelations.item.foci.FocusEffectPoison;
import mod.icarus.crimsonrevelations.item.foci.FocusEffectTaintPoison;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ScanEntity;
import thaumcraft.api.research.ScanningManager;

public class ResearchHandler {
    public static void init() {
        // Focus Effects
        FocusEngine.registerElement(FocusEffectBlindingFlash.class, new ResourceLocation(CrimsonRevelations.MODID, "textures/foci/blinding_flash.png"), 16776421);
        FocusEngine.registerElement(FocusEffectPoison.class, new ResourceLocation(CrimsonRevelations.MODID, "textures/foci/poison.png"), 9039872);

        // Research Categories
        ResearchCategories.registerCategory("REVELATIONS", "CrimsonRites", new AspectList(), new ResourceLocation(Thaumcraft.MODID, "textures/items/crimson_rites.png"), new ResourceLocation(CrimsonRevelations.MODID, "textures/gui/research_background.jpg"), new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_research_back_over.png"));

        // Research
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(CrimsonRevelations.MODID, "research/revelations"));

        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION) {
            // Focus Effects
            FocusEngine.registerElement(FocusEffectTaintPoison.class, new ResourceLocation(CrimsonRevelations.MODID, "textures/foci/taint_poison.png"), 10354925);

            // Research Categories
            ThaumcraftApi.registerResearchLocation(new ResourceLocation(CrimsonRevelations.MODID, "research/compat/thaumic_augmentation"));

            // Research
            ScanningManager.addScannableThing(new ScanEntity("!OVERGROWN_TAINTACLE", EntityOvergrownTaintacle.class, true));
        }
    }
}
