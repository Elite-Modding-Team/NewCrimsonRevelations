package mod.icarus.crimsonrevelations.compat.jer;

import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.compatibility.JERAPI;
import mod.icarus.crimsonrevelations.core.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.init.LootTableHandler;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;

public class JERIntegration {
    public static void init() {
        IJERAPI jerApi = JERAPI.getInstance();
        IMobRegistry jerMobRegistry = jerApi.getMobRegistry();
        World jerWorld = jerApi.getWorld();

        // TODO: Add spawn biomes?
        jerMobRegistry.register(new EntityCultistPortalLesser(jerWorld), LightLevel.any, 40, LootTableHandler.LESSER_CULTIST_PORTAL);
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            jerMobRegistry.register(new EntityOvergrownTaintacle(jerWorld), LightLevel.any, LootTableHandler.OVERGROWN_TAINTACLE);
    }
}
