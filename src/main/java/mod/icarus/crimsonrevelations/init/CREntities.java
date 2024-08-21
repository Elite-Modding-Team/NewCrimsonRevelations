package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CREntities {
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        int id = 0;

        //registerEntity("cultist_archer", EntityCultistArcher.class, id++, 64, 3, true, 0x1C1A2F, 0x5649B4);
        registerEntity("primal_arrow", EntityPrimalArrow.class, id++, 64, 1, true);

        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            registerEntity("overgrown_taintacle", EntityOvergrownTaintacle.class, id++, 64, 3, true, 0x1C1A2F, 0x5649B4);
    }

    public static void registerEntity(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int eggColor1, int eggColor2) {
        EntityRegistry.registerModEntity(new ResourceLocation(NewCrimsonRevelations.MODID, name), clazz, NewCrimsonRevelations.MODID + "." + name, id, NewCrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates, eggColor1, eggColor2);
    }

    public static void registerEntity(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation(NewCrimsonRevelations.MODID, name), clazz, NewCrimsonRevelations.MODID + "." + name, id, NewCrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates);
    }
}
