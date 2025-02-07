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

    @Config.Comment("Mod Integration")
    public static ModIntegrationSettings mod_integration_settings = new ModIntegrationSettings();

    @Config.Comment("Thaumic Augmentation")
    public static TAIntegrationSettings thaumic_augmentation_settings = new TAIntegrationSettings();

    public static class GeneralSettings {
        @Config.Name("Furious Zombie: Spawning")
        @Config.Comment("Enables Furious Zombies to spawn naturally. [default: true]")
        @Config.RequiresMcRestart
        public boolean FURIOUS_ZOMBIE_SPAWNING = true;

        @Config.Name("Furious Zombie: Underground Spawning")
        @Config.Comment("Enables Furious Zombies to spawn naturally underground. [default: false]")
        @Config.RequiresMcRestart
        public boolean FURIOUS_ZOMBIE_UNDERGROUND_SPAWNING = false;

        @Config.Name("Furious Zombie: Spawn Weight")
        @Config.Comment("Default spawn weight of Furious Zombies. [default: 5]")
        @Config.RangeInt(min = 0, max = 99999)
        @Config.RequiresMcRestart
        public int FURIOUS_ZOMBIE_WEIGHT = 5;

        @Config.Name("Mana Bean: Aspect Count")
        @Config.Comment("Mana Bean contained aspect count. [default: 5]")
        @Config.RangeInt(min = 1, max = 128)
        @Config.RequiresMcRestart
        public int MANA_BEAN_ASPECT_COUNT = 5;

        @Config.Name("Mana Bean: Effect List")
        @Config.Comment("Configurable list of possible effects that eaten Mana Beans can apply.")
        public String[] MANA_BEAN_EFFECT_LIST = new String[]{
                "minecraft:absorption",
                "minecraft:blindness",
                "minecraft:fire_resistance",
                "minecraft:haste",
                "minecraft:health_boost",
                "minecraft:hunger",
                "minecraft:instant_damage",
                "minecraft:instant_health",
                "minecraft:invisibility",
                "minecraft:jump_boost",
                "minecraft:levitation",
                "minecraft:luck",
                "minecraft:mining_fatigue",
                "minecraft:nausea",
                "minecraft:night_vision",
                "minecraft:poison",
                "minecraft:regeneration",
                "minecraft:resistance",
                "minecraft:saturation",
                "minecraft:slowness",
                "minecraft:speed",
                "minecraft:strength",
                "minecraft:unluck",
                "minecraft:water_breathing",
                "minecraft:weakness",
                "minecraft:wither",
                "thaumcraft:blurredvision",
                "thaumcraft:deathgaze",
                "thaumcraft:fluxtaint",
                "thaumcraft:infectiousvisexhaust",
                "thaumcraft:sunscorned",
                "thaumcraft:thaumarhia",
                "thaumcraft:unnaturalhunger",
                "thaumcraft:visexhaust",
                "thaumcraft:warpward"
        };

        @Config.Name("Mana Bean: Generation Frequency")
        @Config.Comment("The amount of iterations over block positions to generate Mana Pods. [default: 10]")
        @Config.RangeInt(min = 0, max = 100)
        public int MANA_BEAN_GENERATION_FREQUENCY = 10;

        @Config.Name("Mana Bean: Research Chance")
        @Config.Comment("The percentage chance for an eaten Mana Bean to grant observations and theories for research. [default: 0.33]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double MANA_BEAN_RESEARCH_CHANCE = 0.33D;

        @Config.Name("Primordial Scribing Tools: Curiosity Chance")
        @Config.Comment("The percentage chance for a curiosity to be obtained from the Primordial Scribing Tools. [default: 0.3]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double PRIMORDIAL_TOOLS_CURIOSITY_CHANCE = 0.3D;

        @Config.Name("Scribing Tools of Knowledge: Curiosity Chance")
        @Config.Comment("The percentage chance for a curiosity to be obtained from the Scribing Tools of Knowledge. [default: 0.2]")
        @Config.RangeDouble(min = 0.0D, max = 1.0D)
        @Config.RequiresMcRestart
        public double KNOWLEDGE_TOOLS_CURIOSITY_CHANCE = 0.2D;
    }

    public static class ModIntegrationSettings {
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
                CRConfigLists.initLists();
            }
        }
    }
}
