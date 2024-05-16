package com.mobiusflip.crimsonrevelations;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import com.mobiusflip.crimsonrevelations.init.CRCreativeTabs;
import com.mobiusflip.crimsonrevelations.init.ResearchHandler;

@Mod(modid = CrimsonRevelations.MODID, name = CrimsonRevelations.NAME, version = CrimsonRevelations.VERSION, dependencies = CrimsonRevelations.DEPENDENCIES)
public class CrimsonRevelations {
    public static final String MODID = "crimsonrevelations";
    public static final String NAME = "New Crimson Revelations";
    public static final String VERSION = "1.0";
    public static final String DEPENDENCIES = "required-after:thaumcraft@[1.12.2-6.1.BETA26,);after:thaumicaugmentation";
    public static final CreativeTabs tabCR = new CRCreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "CrimsonRevelationsTab");

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
    	ResearchHandler.init();
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {

    }
}
