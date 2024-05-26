package com.icarus.crimsonrevelations.compat.thaumicaugmentation;

import net.minecraft.entity.EnumCreatureType;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import thaumcraft.common.entities.monster.tainted.EntityTaintSeed;
import thaumcraft.common.entities.monster.tainted.EntityTaintSwarm;
import thecodex6824.thaumicaugmentation.api.world.TABiomes;

// TODO: Make it all configurable
public class TAIntegration {
    public static void init() {
        // Extra taint mobs
        EntityRegistry.addSpawn(EntityTaintSeed.class, 20, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);
        EntityRegistry.addSpawn(EntityTaintSwarm.class, 10, 1, 1, EnumCreatureType.MONSTER, TABiomes.TAINTED_LANDS);
    }
}
