package com.icarus.crimsonrevelations.compat.jer;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;

import com.icarus.crimsonrevelations.core.CRConfig;
import com.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import com.icarus.crimsonrevelations.init.LootTableHandler;

import jeresources.api.IJERAPI;
import jeresources.api.IMobRegistry;
import jeresources.api.conditionals.LightLevel;
import jeresources.compatibility.JERAPI;

public class JERIntegration {
    public static void init() {
        IJERAPI jerApi = JERAPI.getInstance();
        IMobRegistry jerMobRegistry = jerApi.getMobRegistry();
        World jerWorld = jerApi.getWorld();

        // TODO: Add spawn biomes?
        // Lesser Crimson Portals should display 40 experience rather than 10 experience
        jerMobRegistry.register(new EntityCultistPortalLesser(jerWorld), LightLevel.any, LootTableHandler.LESSER_CULTIST_PORTAL);
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            jerMobRegistry.register(new EntityOvergrownTaintacle(jerWorld), LightLevel.any, LootTableHandler.OVERGROWN_TAINTACLE);
    }
}
