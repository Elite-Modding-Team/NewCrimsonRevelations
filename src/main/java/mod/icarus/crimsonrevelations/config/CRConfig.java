package mod.icarus.crimsonrevelations.config;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = NewCrimsonRevelations.MODID, name = NewCrimsonRevelations.NAME)
public class CRConfig {
    @Config.Comment("General")
    public static GeneralSettings general_settings = new GeneralSettings();

    @Config.Comment("Thaumic Augmentation Integration")
    public static TAIntegrationSettings TA_integration_settings = new TAIntegrationSettings();

    public static class GeneralSettings {
        @Config.Name("Just Enough Resources Integration")
        @Config.Comment("Enables Just Enough Resources integration. [default: true]")
        @Config.RequiresMcRestart
        public boolean JER_INTEGRATION = true;

        @Config.Name("Thaumic Augmentation Integration")
        @Config.Comment("Enables Thaumic Augmentation integration. [default: true]")
        @Config.RequiresMcRestart
        public boolean TA_INTEGRATION = true;
    }

    public static class TAIntegrationSettings {
        @Config.Name("Boots of the Meteor: Jump Boost")
        @Config.Comment("The boost applied when the wearer jumps, this is added to the base jump height of the player. [default: 0.3]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double METEOR_BOOTS_JUMP_BOOST = 0.3D;

        @Config.Name("Boots of the Meteor: Jump Factor")
        @Config.Comment("The boost applied to player movement while in the air. Note that sprinting's jump modifier uses this value as well. [default: 0.02]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double METEOR_BOOTS_JUMP_FACTOR = 0.02D;

        @Config.Name("Boots of the Meteor: Land Speed Boost")
        @Config.Comment("The boost applied while the wearer is on the ground, this is added to the base movement of the player per tick. [default: 0.06]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double METEOR_BOOTS_LAND_SPEED = 0.06D;

        @Config.Name("Boots of the Meteor: Sneak Reduction")
        @Config.Comment("Any speed boosts (not jump) will be divided by this value while sneaking. [default: 4.0]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double METEOR_BOOTS_SNEAK_REDUCTION = 4.0D;

        @Config.Name("Boots of the Meteor: Step Height")
        @Config.Comment("The boost applied to the player's step height (while not sneaking), this is added to the vanilla default value of 0.6. [default: 0.67]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double METEOR_BOOTS_STEP_HEIGHT = 0.67D;

        @Config.Name("Boots of the Meteor: Water Speed Boost")
        @Config.Comment("The boost applied while the wearer is in water, this is added to the base movement of the player per tick. [default: 0.03]")
        @Config.RangeDouble(min = 0.0D, max = 10.0D)
        @Config.RequiresMcRestart
        public double METEOR_BOOTS_WATER_SPEED = 0.03D;

        @Config.Name("Overgrown Taintacle: Boss Bar")
        @Config.Comment("Enables the Overgrown Taintacle's boss bar. [default: true]")
        @Config.RequiresWorldRestart
        public boolean OVERGROWN_TAINTACLE_BOSS_BAR = true;

        @Config.Name("Overgrown Taintacle: Spawn Weight")
        @Config.Comment("Default spawn weight of Overgrown Taintacles. [default: 1]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int OVERGROWN_TAINTACLE_WEIGHT = 1;

        @Config.Name("Taint Seed: Spawn Weight")
        @Config.Comment("Default spawn weight of Taint Seeds. [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int TAINT_SEED_WEIGHT = 20;

        @Config.Name("Taint Swarm: Spawn Weight")
        @Config.Comment("Default spawn weight of Taint Swarms. [default: 20]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int TAINT_SWARM_WEIGHT = 20;
    }

    @Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
    public static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent event) {
            if (event.getModID().equals(NewCrimsonRevelations.MODID)) {
                ConfigManager.sync(NewCrimsonRevelations.MODID, Config.Type.INSTANCE);
            }
        }
    }
}
