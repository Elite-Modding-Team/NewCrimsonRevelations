package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.Thaumcraft;

@EventBusSubscriber(modid = CrimsonRevelations.MODID)
public class LootTableHandler {
    public static final ResourceLocation CULTIST = new ResourceLocation(CrimsonRevelations.MODID, ("entities/cultist"));
    public static final ResourceLocation LESSER_CULTIST_PORTAL = new ResourceLocation(CrimsonRevelations.MODID, ("entities/lesser_cultist_portal"));

    // Bosses
    public static final ResourceLocation OVERGROWN_TAINTACLE = new ResourceLocation(CrimsonRevelations.MODID, ("entities/boss/overgrown_taintacle"));

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(new ResourceLocation(Thaumcraft.MODID, "cultist"))) {
            LootTable lootCultist = event.getLootTableManager().getLootTableFromLocation(CULTIST);
            event.getTable().addPool(lootCultist.getPool("crimson_material"));
        }
    }
}
