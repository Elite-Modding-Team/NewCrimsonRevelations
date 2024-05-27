package com.icarus.crimsonrevelations.compat.thaumicaugmentation;

import com.icarus.crimsonrevelations.core.CRConfig;
import com.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
import thecodex6824.thaumicaugmentation.api.world.TABiomes;

public class TAIntegration {
    public static void postInit() {
        // Extra taint mobs
        EntityRegistry.addSpawn(EntityTaintSeed.class, CRConfig.TA_integration_settings.TAINT_SEED_WEIGHT, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);
        EntityRegistry.addSpawn(EntityTaintSwarm.class, CRConfig.TA_integration_settings.TAINT_SWARM_WEIGHT, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);

        // Boss
        EntityRegistry.addSpawn(EntityOvergrownTaintacle.class, CRConfig.TA_integration_settings.OVERGROWN_TAINTACLE_WEIGHT, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);
    }
}
