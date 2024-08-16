package mod.icarus.crimsonrevelations.init;

import com.google.common.base.Preconditions;
import mod.icarus.crimsonrevelations.CrimsonRevelations;
import mod.icarus.crimsonrevelations.core.CRConfig;
import mod.icarus.crimsonrevelations.entity.EntityCultistArcher;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.item.ItemCR;
import mod.icarus.crimsonrevelations.item.ItemCRArrow;
import mod.icarus.crimsonrevelations.item.ItemCRSword;
import mod.icarus.crimsonrevelations.item.armor.ItemCultistArcherArmor;
import mod.icarus.crimsonrevelations.item.weapons.ItemBoneBow;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import javax.annotation.Nonnull;

// TODO: Organize and split this class
@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = CrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(CrimsonRevelations.MODID)
public class RegistryHandler {
    @GameRegistry.ObjectHolder("air_primal_arrow")
    public static Item airPrimalArrow;
    @GameRegistry.ObjectHolder("bone_bow")
    public static Item boneBow;
    @GameRegistry.ObjectHolder("crimson_archer_helmet")
    public static Item crimsonArcherHelmet;
    @GameRegistry.ObjectHolder("crimson_archer_chestplate")
    public static Item crimsonArcherChestplate;
    @GameRegistry.ObjectHolder("crimson_archer_leggings")
    public static Item crimsonArcherLeggings;
    @GameRegistry.ObjectHolder("crimson_fabric")
    public static Item crimsonFabric;
    @GameRegistry.ObjectHolder("crimson_plate")
    public static Item crimsonPlate;
    @GameRegistry.ObjectHolder("crimson_sword")
    public static Item crimsonSword;
    @GameRegistry.ObjectHolder("earth_primal_arrow")
    public static Item earthPrimalArrow;
    @GameRegistry.ObjectHolder("embellished_crimson_fabric")
    public static Item embellishedCrimsonFabric;
    @GameRegistry.ObjectHolder("entropy_primal_arrow")
    public static Item entropyPrimalArrow;
    @GameRegistry.ObjectHolder("fire_primal_arrow")
    public static Item firePrimalArrow;
    @GameRegistry.ObjectHolder("order_primal_arrow")
    public static Item orderPrimalArrow;
    @GameRegistry.ObjectHolder("water_primal_arrow")
    public static Item waterPrimalArrow;

    public static ArmorMaterial ARMOR_CULTIST_ARCHER = EnumHelper.addArmorMaterial("CULTIST_ARCHER", "CULTIST_ARCHER", 17, new int[]{2, 5, 5, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F).setRepairItem(new ItemStack(crimsonPlate));

    public static ToolMaterial TOOL_CULTIST = EnumHelper.addToolMaterial("CULTIST", 3, 321, 7.5F, 2.5F, 20).setRepairItem(new ItemStack(crimsonPlate));

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                setup(new ItemCR(EnumRarity.UNCOMMON), "crimson_fabric"),
                setup(new ItemCR(EnumRarity.UNCOMMON), "embellished_crimson_fabric"),
                setup(new ItemCR(EnumRarity.UNCOMMON), "crimson_plate"),
                setup(new ItemCRSword(TOOL_CULTIST, EnumRarity.UNCOMMON), "crimson_sword"),

                setup(new ItemCultistArcherArmor(EntityEquipmentSlot.HEAD), "crimson_archer_helmet"),
                setup(new ItemCultistArcherArmor(EntityEquipmentSlot.CHEST), "crimson_archer_chestplate"),
                setup(new ItemCultistArcherArmor(EntityEquipmentSlot.LEGS), "crimson_archer_leggings"),

                setup(new ItemBoneBow(), "bone_bow"),
                setup(new ItemCRArrow(EnumRarity.UNCOMMON), "air_primal_arrow"),
                setup(new ItemCRArrow(EnumRarity.UNCOMMON), "fire_primal_arrow"),
                setup(new ItemCRArrow(EnumRarity.UNCOMMON), "water_primal_arrow"),
                setup(new ItemCRArrow(EnumRarity.UNCOMMON), "earth_primal_arrow"),
                setup(new ItemCRArrow(EnumRarity.UNCOMMON), "order_primal_arrow"),
                setup(new ItemCRArrow(EnumRarity.UNCOMMON), "entropy_primal_arrow")
        );
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        RecipeHandler.initArcaneCrafting();
        RecipeHandler.initCrucible();
        RecipeHandler.initInfusion();
    }

    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
        int id = 0;

        registerEntity("cultist_archer", EntityCultistArcher.class, id++, 64, 3, true, 0x1C1A2F, 0x5649B4);
        registerEntity("primal_arrow", EntityPrimalArrow.class, id++, 64, 1, true);

        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            registerEntity("overgrown_taintacle", EntityOvergrownTaintacle.class, id++, 64, 3, true, 0x1C1A2F, 0x5649B4);
    }

    @SubscribeEvent
    public static void registerAspects(AspectRegistryEvent event) {
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            ThaumcraftApi.registerEntityTag(CrimsonRevelations.MODID + ".overgrown_taintacle", new AspectList().add(Aspect.FLUX, 30).add(Aspect.ELDRITCH, 30).add(Aspect.AVERSION, 30).add(Aspect.PLANT, 30));
    }

    public static void registerEntity(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int eggColor1, int eggColor2) {
        EntityRegistry.registerModEntity(new ResourceLocation(CrimsonRevelations.MODID, name), clazz, CrimsonRevelations.MODID + "." + name, id, CrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates, eggColor1, eggColor2);
    }

    public static void registerEntity(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
        EntityRegistry.registerModEntity(new ResourceLocation(CrimsonRevelations.MODID, name), clazz, CrimsonRevelations.MODID + "." + name, id, CrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates);
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.getRegistryName().getNamespace().equals(CrimsonRevelations.MODID))
                .filter(block -> !(block instanceof BlockDoor)) // Doors should not have an item block registered
                .filter(block -> !(block instanceof BlockSlab)) // Slabs should not have an item block registered
                .forEach(block -> registry.register(setup(new ItemBlock(block), block.getRegistryName())));
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        for (Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.getRegistryName().getNamespace().equals(CrimsonRevelations.MODID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
            }
        }
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry<T>> T setup(T entry, String name) {
        return setup(entry, new ResourceLocation(CrimsonRevelations.MODID, name));
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry<T>> T setup(T entry, ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry to setup must not be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign must not be null!");
        entry.setRegistryName(registryName);
        if (entry instanceof Block) {
            ((Block) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(CrimsonRevelations.tabCR);
        }
        if (entry instanceof Item) {
            ((Item) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(CrimsonRevelations.tabCR);
        }
        return entry;
    }
}
