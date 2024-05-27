package mod.icarus.crimsonrevelations.compat;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import mod.icarus.crimsonrevelations.compat.jer.JERIntegration;
import mod.icarus.crimsonrevelations.compat.thaumicaugmentation.TAIntegration;
import mod.icarus.crimsonrevelations.core.CRConfig;
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
        if (Loader.isModLoaded("jeresources") && CRConfig.general_settings.JER_INTEGRATION)
            JERIntegration.init();
    }

    public static void postInit() {
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            TAIntegration.postInit();
    }
}
