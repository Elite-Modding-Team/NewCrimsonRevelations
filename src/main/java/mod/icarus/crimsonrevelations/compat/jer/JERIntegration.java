package mod.icarus.crimsonrevelations.compat.jer;

import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.compatibility.JERAPI;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.init.CRLootTables;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;

public class JERIntegration {
    public static void init() {
        IJERAPI jerApi = JERAPI.getInstance();
        IMobRegistry jerMobRegistry = jerApi.getMobRegistry();
        World jerWorld = jerApi.getWorld();

        jerMobRegistry.register(new EntityCultistPortalLesser(jerWorld), LightLevel.any, 40, CRLootTables.LESSER_CULTIST_PORTAL);

        // These are already added by Just Enough Magiculture, so they'll immediately be turned off when detected
        if (!Loader.isModLoaded("justenoughmagiculture")) {
            jerMobRegistry.register(new EntityCultistCleric(jerWorld), LightLevel.any, EntityCultist.LOOT);
            jerMobRegistry.register(new EntityCultistKnight(jerWorld), LightLevel.any, EntityCultist.LOOT);
        }

        // Thaumic Augmentation Integration
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            jerMobRegistry.register(new EntityOvergrownTaintacle(jerWorld), LightLevel.any, new String[]{"Tainted Lands"}, CRLootTables.OVERGROWN_TAINTACLE);
    }
}
