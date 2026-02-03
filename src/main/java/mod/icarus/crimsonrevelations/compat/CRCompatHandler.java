package mod.icarus.crimsonrevelations.compat;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.compat.jer.JERIntegration;
import mod.icarus.crimsonrevelations.compat.thaumicaugmentation.TAIntegration;
import mod.icarus.crimsonrevelations.config.CRConfig;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CRCompatHandler {
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
    }

    public static void preInit() {
    }

    public static void init() {
        if (Loader.isModLoaded("jeresources") && CRConfig.mod_integration_settings.enableJERIntegration)
            JERIntegration.init();
    }

    public static void postInit() {
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.mod_integration_settings.enableTAIntegration)
            TAIntegration.postInit();
    }
}
