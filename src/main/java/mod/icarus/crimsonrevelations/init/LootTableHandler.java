package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.LootEntry;
import net.minecraft.world.storage.loot.LootEntryItem;
import net.minecraft.world.storage.loot.LootPool;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.KilledByPlayer;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.LootFunction;
import net.minecraft.world.storage.loot.functions.LootingEnchantBonus;
import net.minecraft.world.storage.loot.functions.SetCount;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.Thaumcraft;

@EventBusSubscriber(modid = CrimsonRevelations.MODID)
public class LootTableHandler {
    public static final ResourceLocation LESSER_CULTIST_PORTAL = new ResourceLocation(CrimsonRevelations.MODID, ("entities/lesser_cultist_portal"));

    // Bosses
    public static final ResourceLocation OVERGROWN_TAINTACLE = new ResourceLocation(CrimsonRevelations.MODID, ("entities/boss/overgrown_taintacle"));

    @SubscribeEvent
    public static void onLootTableLoad(LootTableLoadEvent event) {
        if (event.getName().equals(new ResourceLocation(Thaumcraft.MODID, "cultist"))) {
            LootPool crimson_material_pool = event.getTable().getPool("crimson_material");

            if (crimson_material_pool == null) {
                crimson_material_pool = new LootPool(new LootEntry[0], new LootCondition[0], new RandomValueRange(1, 1), new RandomValueRange(1, 1), "crimson_material");
                event.getTable().addPool(crimson_material_pool);
            }

            if (crimson_material_pool != null) {
                crimson_material_pool.addEntry(new LootEntryItem(new ItemStack(RegistryHandler.crimsonFabric).getItem(), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:crimson_fabric"));
                crimson_material_pool.addEntry(new LootEntryItem(new ItemStack(RegistryHandler.crimsonPlate).getItem(), 1, 0,
                        new LootFunction[]{new SetCount(new LootCondition[]{new KilledByPlayer(false)}, new RandomValueRange(0, 1)),
                                new LootingEnchantBonus(new LootCondition[0], new RandomValueRange(0, 1), 3)},
                        new LootCondition[0], "crimsonrevelations:crimson_plate"));
            }
        }
    }
}
