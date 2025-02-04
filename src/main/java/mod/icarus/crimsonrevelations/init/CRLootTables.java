package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraft.world.storage.loot.functions.SetNBT;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.Thaumcraft;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CRLootTables {
    // Loot Tables
    public static final ResourceLocation LESSER_CULTIST_PORTAL = new ResourceLocation(NewCrimsonRevelations.MODID, ("entities/lesser_cultist_portal"));

    // Boss Loot Tables
    public static final ResourceLocation OVERGROWN_TAINTACLE = new ResourceLocation(NewCrimsonRevelations.MODID, ("entities/boss/overgrown_taintacle"));

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        // Crimson Cultists
        if (event.getName().equals(new ResourceLocation(Thaumcraft.MODID, "cultist"))) {
            LootPool crimson_material_pool = event.getTable().getPool("crimson_material");

            if (crimson_material_pool == null) {
                crimson_material_pool = new LootPool(new LootEntry[0], new LootCondition[0], new RandomValueRange(1, 1), new RandomValueRange(1, 1), "crimson_material");
                event.getTable().addPool(crimson_material_pool);
            }

            if (crimson_material_pool != null) {
                crimson_material_pool.addEntry(new LootEntryItem(new ItemStack(CRItems.CRIMSON_FABRIC).getItem(), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                crimson_material_pool.addEntry(new LootEntryItem(new ItemStack(CRItems.CRIMSON_PLATE).getItem(), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:crimson_plate"));
            }
        }

        // Pechs
        if (event.getName().equals(new ResourceLocation(Thaumcraft.MODID, "pech"))) {
            LootPool mana_bean_pool = event.getTable().getPool("mana_bean");

            if (mana_bean_pool == null) {
                mana_bean_pool = new LootPool(new LootEntry[0], new LootCondition[0], new RandomValueRange(1, 1), new RandomValueRange(1, 1), "mana_bean");
                event.getTable().addPool(mana_bean_pool);
            }

            // TODO: Only make pechs drop primal aspect mana beans
            if (mana_bean_pool != null) {
                mana_bean_pool.addEntry(new LootEntryItem((new ItemStack(CRItems.MANA_BEAN).getItem()), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new SetNBT(new LootCondition[0], null),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:mana_bean"));
            }
        }

        // Structures
        if (event.getName().equals(LootTableList.CHESTS_DESERT_PYRAMID) || event.getName().equals(LootTableList.CHESTS_SIMPLE_DUNGEON)) {
            LootPool main = event.getTable().getPool("main");
            main.addEntry(new LootEntryItem(CRItems.PROTECTION_RING, 5, 0, new LootFunction[0], new LootCondition[0], "loottable:protection_ring"));
        }

        if (event.getName().equals(LootTableList.CHESTS_JUNGLE_TEMPLE) || event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CROSSING) ||
                event.getName().equals(LootTableList.CHESTS_STRONGHOLD_CORRIDOR)) {
            LootPool main = event.getTable().getPool("main");
            main.addEntry(new LootEntryItem(CRItems.PROTECTION_RING, 3, 0, new LootFunction[0], new LootCondition[0], "loottable:protection_ring"));
        }
    }
}
