package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectBlindingFlash;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectPoison;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectTaintPoison;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ScanEntity;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;

public class CRResearchRegistry {
    public static void init() {
        // Focus Effects
        FocusEngine.registerElement(FocusEffectBlindingFlash.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/blinding_flash.png"), 16776421);
        FocusEngine.registerElement(FocusEffectPoison.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/poison.png"), 9039872);

        // Research Categories
        ResearchCategories.registerCategory("REVELATIONS", "CrimsonRites", new AspectList(), new ResourceLocation(Thaumcraft.MODID, "textures/items/crimson_rites.png"), new ResourceLocation(NewCrimsonRevelations.MODID, "textures/gui/research_background.jpg"), new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_research_back_over.png"));

        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/bestiary"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/revelations"));

        // Research
        ScanningManager.addScannableThing(new ScanEntity("!CR_CRIMSON_CLERIC", EntityCultistCleric.class, true));
        ScanningManager.addScannableThing(new ScanEntity("!CR_CRIMSON_KNIGHT", EntityCultistKnight.class, true));

        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION) {
            // Focus Effects
            FocusEngine.registerElement(FocusEffectTaintPoison.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/taint_poison.png"), 10354925);

            // Research Categories
            ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/compat/thaumic_augmentation"));

            // Research
            ScanningManager.addScannableThing(new ScanEntity("!OVERGROWN_TAINTACLE", EntityOvergrownTaintacle.class, true));
        }
    }
}
