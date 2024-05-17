package com.mobiusflip.crimsonrevelations.init;

import com.mobiusflip.crimsonrevelations.CrimsonRevelations;
import com.mobiusflip.crimsonrevelations.item.foci.FocusEffectPoison;

import net.minecraft.util.ResourceLocation;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.research.ResearchCategories;

public class ResearchHandler {
    public static void init() {
        ResearchCategories.registerCategory("REVELATIONS", "CrimsonRites", new AspectList(), new ResourceLocation(Thaumcraft.MODID, "textures/items/crimson_rites.png"), new ResourceLocation(CrimsonRevelations.MODID, "textures/gui/research_background.jpg"), new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_research_back_over.png"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(CrimsonRevelations.MODID, "research/revelations"));
        
        // Focus Effects
    	FocusEngine.registerElement(FocusEffectPoison.class, new ResourceLocation(CrimsonRevelations.MODID, "textures/foci/poison.png"), 9039872);
    }
}
