package com.mobiusflip.crimsonrevelations.compat;

import com.mobiusflip.crimsonrevelations.CrimsonRevelations;
import com.mobiusflip.crimsonrevelations.compat.thaumicaugmentation.TAIntegration;

import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber(modid = CrimsonRevelations.MODID)
public class CompatHandler {
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
    }

    public static void preInit() {
    }

    public static void init() {
        if (Loader.isModLoaded("thaumicaugmentation")) TAIntegration.init();
        //if (Loader.isModLoaded("jeresources")) JERIntegration.init();
    }

    public static void postInit() {
    }
}
