package mod.icarus.crimsonrevelations.core;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = CrimsonRevelations.MODID, name = CrimsonRevelations.NAME)
public class CRConfig {
    @Config.Comment("General")
    public static GeneralSettings general_settings = new GeneralSettings();

    @Config.Comment("Thaumic Augmentation Integration")
    public static TAIntegrationSettings TA_integration_settings = new TAIntegrationSettings();

    public static class GeneralSettings {
        @Config.Name("Just Enough Resources Integration")
        @Config.Comment("Enables Just Enough Resources integration [default: true]")
        @Config.RequiresMcRestart
        public boolean JER_INTEGRATION = true;

        @Config.Name("Thaumic Augmentation Integration")
        @Config.Comment("Enables Thaumic Augmentation integration [default: true]")
        @Config.RequiresMcRestart
        public boolean TA_INTEGRATION = true;
    }

    public static class TAIntegrationSettings {
        @Config.Name("Overgrown Taintacle Boss Bar")
        @Config.Comment("Enables the Overgrown Taintacle's boss bar [default: true]")
        @Config.RequiresWorldRestart
        public boolean OVERGROWN_TAINTACLE_BOSS_BAR = true;

        @Config.Name("Overgrown Taintacle Spawn Weight")
        @Config.Comment("Default spawn weight of Overgrown Taintacles [default: 2]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int OVERGROWN_TAINTACLE_WEIGHT = 2;

        @Config.Name("Taint Seed Spawn Weight")
        @Config.Comment("Default spawn weight of Taint Seeds [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int TAINT_SEED_WEIGHT = 20;

        @Config.Name("Taint Swarm Spawn Weight")
        @Config.Comment("Default spawn weight of Taint Swarms [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int TAINT_SWARM_WEIGHT = 20;
    }

    @Mod.EventBusSubscriber(modid = CrimsonRevelations.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(CrimsonRevelations.MODID)) {
                ConfigManager.sync(CrimsonRevelations.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
