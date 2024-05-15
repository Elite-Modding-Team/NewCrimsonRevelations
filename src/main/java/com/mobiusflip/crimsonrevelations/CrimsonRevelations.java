package com.mobiusflip.crimsonrevelations;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

import com.mobiusflip.crimsonrevelations.recipes.CrimsonRecipes;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;

@Mod(modid = CrimsonRevelations.MODID, name = CrimsonRevelations.NAME, version = CrimsonRevelations.VERSION, dependencies = CrimsonRevelations.DEPENDENCIES)
public class CrimsonRevelations {
    public static final String MODID = "crimsonrevelations";
    public static final String NAME = "New Crimson Revelations";
    public static final String VERSION = "1.0";
    public static final String DEPENDENCIES = "required-after:thaumcraft@[1.12.2-6.1.BETA26,);after:thaumicaugmentation";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CrimsonRecipes.initRecipes();
        ResearchCategories.registerCategory("CR", "CrimsonRites", new AspectList(), new ResourceLocation("thaumcraft", "textures/items/crimson_rites.png"), new ResourceLocation("crimsonrevelations", "textures/gui/research_background.jpg"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("crimsonrevelations", "research/revelations"));
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
