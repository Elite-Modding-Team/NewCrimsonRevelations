package com.mobiusflip.crimsonrevelations.init;

import com.mobiusflip.crimsonrevelations.CrimsonRevelations;

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
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(new ResourceLocation(Thaumcraft.MODID, "cultist"))) {
            LootTable lootCultist = event.getLootTableManager().getLootTableFromLocation(CULTIST);
            event.getTable().addPool(lootCultist.getPool("crimson_material"));
        }
    }
}
