package com.mobiusflip.crimsonrevelations.init;

import com.mobiusflip.crimsonrevelations.CrimsonRevelations;
import com.mobiusflip.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import com.mobiusflip.crimsonrevelations.item.foci.FocusEffectPoison;

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
        FocusEngine.registerElement(FocusEffectPoison.class, new ResourceLocation(CrimsonRevelations.MODID, "textures/foci/poison.png"), 9039872);

        // Research Categories
        ResearchCategories.registerCategory("REVELATIONS", "CrimsonRites", new AspectList(), new ResourceLocation(Thaumcraft.MODID, "textures/items/crimson_rites.png"), new ResourceLocation(CrimsonRevelations.MODID, "textures/gui/research_background.jpg"), new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_research_back_over.png"));

        // Researches
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(CrimsonRevelations.MODID, "research/revelations"));
        if (Loader.isModLoaded("thaumicaugmentation"))
            ThaumcraftApi.registerResearchLocation(new ResourceLocation(CrimsonRevelations.MODID, "research/compat/thaumic_augmentation"));

        // Scanning
        if (Loader.isModLoaded("thaumicaugmentation"))
            ScanningManager.addScannableThing(new ScanEntity("!OVERGROWN_TAINTACLE", EntityOvergrownTaintacle.class, true));
    }
}
