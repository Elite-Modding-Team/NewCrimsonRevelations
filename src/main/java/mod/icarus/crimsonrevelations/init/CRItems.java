package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.item.CRItem;
import mod.icarus.crimsonrevelations.item.CRItemArrow;
import mod.icarus.crimsonrevelations.item.CRItemSword;
import mod.icarus.crimsonrevelations.item.armor.ItemCultistArcherArmor;
import mod.icarus.crimsonrevelations.item.armor.ItemMeteorBoots;
import mod.icarus.crimsonrevelations.item.baubles.CRItemRunicBauble;
import mod.icarus.crimsonrevelations.item.tools.ItemKnowledgeScribingTools;
import mod.icarus.crimsonrevelations.item.tools.ItemPrimordialScribingTools;
import mod.icarus.crimsonrevelations.item.weapons.ItemBoneBow;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;

import javax.annotation.Nonnull;

import baubles.api.BaubleType;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CRItems {
    @GameRegistry.ObjectHolder("aer_arrow")
    public static Item aerArrow;
    @GameRegistry.ObjectHolder("aqua_arrow")
    public static Item aquaArrow;
    @GameRegistry.ObjectHolder("bone_bow")
    public static Item boneBow;
    @GameRegistry.ObjectHolder("crimson_archer_helmet")
    public static Item crimsonArcherHelmet;
    @GameRegistry.ObjectHolder("crimson_archer_chestplate")
    public static Item crimsonArcherChestplate;
    @GameRegistry.ObjectHolder("crimson_archer_leggings")
    public static Item crimsonArcherGreaves;
    @GameRegistry.ObjectHolder("meteor_boots")
    public static Item meteorBoots;
    @GameRegistry.ObjectHolder("crimson_fabric")
    public static Item crimsonFabric;
    @GameRegistry.ObjectHolder("crimson_plate")
    public static Item crimsonPlate;
    @GameRegistry.ObjectHolder("crimson_sword")
    public static Item crimsonSword;
    @GameRegistry.ObjectHolder("embellished_crimson_fabric")
    public static Item embellishedCrimsonFabric;
    @GameRegistry.ObjectHolder("ignis_arrow")
    public static Item ignisArrow;
    @GameRegistry.ObjectHolder("knowledge_scribing_tools")
    public static Item knowledgeScribingTools;
    @GameRegistry.ObjectHolder("ordo_arrow")
    public static Item ordoArrow;
    @GameRegistry.ObjectHolder("perditio_arrow")
    public static Item perditioArrow;
    @GameRegistry.ObjectHolder("primordial_scribing_tools")
    public static Item primordialScribingTools;
    @GameRegistry.ObjectHolder("protection_ring")
    public static Item protectionRing;
    @GameRegistry.ObjectHolder("terra_arrow")
    public static Item terraArrow;
    @GameRegistry.ObjectHolder("runic_amulet")
    public static Item runicAmulet;
    @GameRegistry.ObjectHolder("runic_amulet_emergency")
    public static Item runicAmuletEmergency;
    @GameRegistry.ObjectHolder("runic_girdle")
    public static Item runicGirdle;
    @GameRegistry.ObjectHolder("runic_girdle_kinetic")
    public static Item runicGirdleKinetic;
    @GameRegistry.ObjectHolder("runic_ring")
    public static Item runicRing;
    @GameRegistry.ObjectHolder("runic_ring_charged")
    public static Item runicRingCharged;
    @GameRegistry.ObjectHolder("runic_ring_regen")
    public static Item runicRingRegen;

    public static ArmorMaterial ARMOR_CULTIST_ARCHER = EnumHelper.addArmorMaterial("CULTIST_ARCHER", "CULTIST_ARCHER", 17, new int[]{2, 5, 5, 2}, 13, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0F).setRepairItem(new ItemStack(crimsonPlate));
    public static ArmorMaterial BOOTS_METEOR = EnumHelper.addArmorMaterial("METEOR_BOOTS", "METEOR_BOOTS", 30, new int[]{2, 0, 0, 0}, 25, SoundEvents.ITEM_FIRECHARGE_USE, 2.0F).setRepairItem(new ItemStack(Items.BLAZE_POWDER));
    
    public static ToolMaterial TOOL_CULTIST = EnumHelper.addToolMaterial("CULTIST", 3, 321, 7.5F, 2.5F, 20).setRepairItem(new ItemStack(crimsonPlate));

    @SubscribeEvent
    public static void registerItems(@Nonnull final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(
                CRRegistry.setup(new CRItem(EnumRarity.UNCOMMON), "crimson_fabric"),
                CRRegistry.setup(new CRItem(EnumRarity.UNCOMMON), "embellished_crimson_fabric"),
                CRRegistry.setup(new CRItem(EnumRarity.UNCOMMON), "crimson_plate"),
                CRRegistry.setup(new CRItemSword(TOOL_CULTIST, EnumRarity.UNCOMMON), "crimson_sword"),

                CRRegistry.setup(new ItemCultistArcherArmor(EntityEquipmentSlot.HEAD), "crimson_archer_helmet"),
                CRRegistry.setup(new ItemCultistArcherArmor(EntityEquipmentSlot.CHEST), "crimson_archer_chestplate"),
                CRRegistry.setup(new ItemCultistArcherArmor(EntityEquipmentSlot.LEGS), "crimson_archer_leggings"),

                CRRegistry.setup(new ItemBoneBow(), "bone_bow"),
                CRRegistry.setup(new CRItemArrow(EnumRarity.UNCOMMON), "aer_arrow"),
                CRRegistry.setup(new CRItemArrow(EnumRarity.UNCOMMON), "aqua_arrow"),
                CRRegistry.setup(new CRItemArrow(EnumRarity.UNCOMMON), "ignis_arrow"),
                CRRegistry.setup(new CRItemArrow(EnumRarity.UNCOMMON), "ordo_arrow"),
                CRRegistry.setup(new CRItemArrow(EnumRarity.UNCOMMON), "perditio_arrow"),
                CRRegistry.setup(new CRItemArrow(EnumRarity.UNCOMMON), "terra_arrow"),

                CRRegistry.setup(new CRItemRunicBauble(BaubleType.RING, EnumRarity.COMMON, 1), "protection_ring"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.RING, EnumRarity.UNCOMMON, 5), "runic_ring"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.AMULET, EnumRarity.UNCOMMON, 8), "runic_amulet"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.BELT, EnumRarity.UNCOMMON, 10), "runic_girdle"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.RING, EnumRarity.RARE, 4), "runic_ring_charged"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.RING, EnumRarity.RARE, 4), "runic_ring_regen"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.AMULET, EnumRarity.RARE, 7), "runic_amulet_emergency"),
                CRRegistry.setup(new CRItemRunicBauble(BaubleType.BELT, EnumRarity.RARE, 9), "runic_girdle_kinetic"),

                CRRegistry.setup(new ItemPrimordialScribingTools(), "primordial_scribing_tools")
        );
        
        if (Loader.isModLoaded("thaumicaugmentation")) {
            registry.register(CRRegistry.setup(new ItemMeteorBoots(EntityEquipmentSlot.FEET), "meteor_boots"));
        }

        // Do not register Scribing Tools of Knowledge if TC4 Research Port is detected. It doesn't use observations and theories for its overhauled research.
        if (!Loader.isModLoaded("oldresearch")) {
            registry.register(CRRegistry.setup(new ItemKnowledgeScribingTools(), "knowledge_scribing_tools"));
        }

        // Item Blocks
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.getRegistryName().getNamespace().equals(NewCrimsonRevelations.MODID))
                .filter(block -> !(block instanceof BlockDoor)) // Doors should not have an item block registered
                .filter(block -> !(block instanceof BlockSlab)) // Slabs should not have an item block registered
                .forEach(block -> registry.register(CRRegistry.setup(new ItemBlock(block), block.getRegistryName())));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        final IForgeRegistry<IRecipe> registry = event.getRegistry();

        // Special recipes go here
        if (!Loader.isModLoaded("oldresearch")) {
            registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), knowledgeScribingTools, new ItemStack(knowledgeScribingTools, 1, OreDictionary.WILDCARD_VALUE),
                    ThaumcraftApiHelper.makeCrystal(Aspect.SENSES)).setRegistryName(NewCrimsonRevelations.MODID, "knowledge_scribing_tools_refill"));
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public static void onRegisterModelsEvent(@Nonnull final ModelRegistryEvent event) {
        // Item Models
        for (final Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.getRegistryName().getNamespace().equals(NewCrimsonRevelations.MODID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
        }
    }
}
