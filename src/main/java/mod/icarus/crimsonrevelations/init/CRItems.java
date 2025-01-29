package mod.icarus.crimsonrevelations.init;

import baubles.api.BaubleType;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
import mod.icarus.crimsonrevelations.client.renderer.TileManaPodRenderer;
import mod.icarus.crimsonrevelations.item.CRItem;
import mod.icarus.crimsonrevelations.item.CRItemArrow;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import mod.icarus.crimsonrevelations.item.CRItemSword;
import mod.icarus.crimsonrevelations.item.armor.CRItemCultistArcherArmor;
import mod.icarus.crimsonrevelations.item.armor.CRItemMeteorBoots;
import mod.icarus.crimsonrevelations.item.baubles.CRItemRunicBauble;
import mod.icarus.crimsonrevelations.item.baubles.CRItemVerdantRing;
import mod.icarus.crimsonrevelations.item.tools.CRItemKnowledgeScribingTools;
import mod.icarus.crimsonrevelations.item.tools.CRItemPrimordialScribingTools;
import mod.icarus.crimsonrevelations.item.tools.CRItemSanitationScribingTools;
import mod.icarus.crimsonrevelations.item.weapons.CRItemBoneBow;
import mod.icarus.crimsonrevelations.recipe.VerdantCharmToRing;
import mod.icarus.crimsonrevelations.recipe.VerdantRingToCharm;
import mod.icarus.crimsonrevelations.tile.CRTileManaPod;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
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

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CRItems {
    public static final CRItem CRIMSON_FABRIC = null;
    public static final CRItem CRIMSON_PLATE = null;
    public static final CRItem EMBELLISHED_CRIMSON_FABRIC = null;
    public static final CRItemArrow AER_ARROW = null;
    public static final CRItemArrow AQUA_ARROW = null;
    public static final CRItemArrow IGNIS_ARROW = null;
    public static final CRItemArrow ORDO_ARROW = null;
    public static final CRItemArrow PERDITIO_ARROW = null;
    public static final CRItemArrow TERRA_ARROW = null;
    public static final CRItemBoneBow BONE_BOW = null;
    public static final CRItemCultistArcherArmor CRIMSON_ARCHER_CHESTPLATE = null;
    public static final CRItemCultistArcherArmor CRIMSON_ARCHER_HELMET = null;
    public static final CRItemCultistArcherArmor CRIMSON_ARCHER_LEGGINGS = null;
    public static final CRItemKnowledgeScribingTools KNOWLEDGE_SCRIBING_TOOLS = null;
    public static final CRItemManaBean MANA_BEAN = null;
    public static final CRItemMeteorBoots METEOR_BOOTS = null;
    public static final CRItemPrimordialScribingTools PRIMORDIAL_SCRIBING_TOOLS = null;
    public static final CRItemRunicBauble PROTECTION_RING = null;
    public static final CRItemRunicBauble RUNIC_AMULET = null;
    public static final CRItemRunicBauble RUNIC_AMULET_EMERGENCY = null;
    public static final CRItemRunicBauble RUNIC_GIRDLE = null;
    public static final CRItemRunicBauble RUNIC_GIRDLE_KINETIC = null;
    public static final CRItemRunicBauble RUNIC_RING = null;
    public static final CRItemRunicBauble RUNIC_RING_CHARGED = null;
    public static final CRItemRunicBauble RUNIC_RING_REGEN = null;
    public static final CRItemSanitationScribingTools SANITATION_SCRIBING_TOOLS = null;
    public static final CRItemSword CRIMSON_SWORD = null;
    public static final CRItemVerdantRing VERDANT_RING = null;

    @SubscribeEvent
    public static void registerItems(@Nonnull final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        registry.registerAll(
                CRRegistry.setup(new CRItem(EnumRarity.UNCOMMON), "crimson_fabric"),
                CRRegistry.setup(new CRItem(EnumRarity.UNCOMMON), "embellished_crimson_fabric"),
                CRRegistry.setup(new CRItem(EnumRarity.UNCOMMON), "crimson_plate"),
                CRRegistry.setup(new CRItemSword(CRMaterials.TOOL_CULTIST, EnumRarity.UNCOMMON), "crimson_sword"),

                CRRegistry.setup(new CRItemCultistArcherArmor(EntityEquipmentSlot.HEAD), "crimson_archer_helmet"),
                CRRegistry.setup(new CRItemCultistArcherArmor(EntityEquipmentSlot.CHEST), "crimson_archer_chestplate"),
                CRRegistry.setup(new CRItemCultistArcherArmor(EntityEquipmentSlot.LEGS), "crimson_archer_leggings"),

                CRRegistry.setup(new CRItemBoneBow(), "bone_bow"),
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

                CRRegistry.setup(new CRItemKnowledgeScribingTools(), "knowledge_scribing_tools"),
                CRRegistry.setup(new CRItemSanitationScribingTools(), "sanitation_scribing_tools"),
                CRRegistry.setup(new CRItemPrimordialScribingTools(), "primordial_scribing_tools"),

                CRRegistry.setup(new CRItemManaBean(), "mana_bean"),
                CRRegistry.setup(new CRItemVerdantRing(), "verdant_ring")
        );

        if (Loader.isModLoaded("thaumicaugmentation")) {
            registry.register(CRRegistry.setup(new CRItemMeteorBoots(EntityEquipmentSlot.FEET), "meteor_boots"));
        }

        // Item Blocks
        ForgeRegistries.BLOCKS.getValues().stream()
                .filter(block -> block.getRegistryName().getNamespace().equals(NewCrimsonRevelations.MODID))
                .filter(block -> !(block instanceof BlockDoor)) // Doors should not have an item block registered
                .filter(block -> !(block instanceof BlockSlab)) // Slabs should not have an item block registered
                .filter(block -> !(block instanceof CRBlockManaPod)) // Mana Pods should not have an item block registered >:|
                .forEach(block -> registry.register(CRRegistry.setup(new ItemBlock(block), block.getRegistryName())));
    }

    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
        final IForgeRegistry<IRecipe> registry = event.getRegistry();

        // Special recipes go here
        registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), KNOWLEDGE_SCRIBING_TOOLS, new ItemStack(KNOWLEDGE_SCRIBING_TOOLS, 1, OreDictionary.WILDCARD_VALUE),
                ThaumcraftApiHelper.makeCrystal(Aspect.SENSES)).setRegistryName(NewCrimsonRevelations.MODID, "knowledge_scribing_tools_refill"));
        registry.register(new ShapelessOreRecipe(new ResourceLocation(Thaumcraft.MODID, "inkwell"), SANITATION_SCRIBING_TOOLS, new ItemStack(SANITATION_SCRIBING_TOOLS, 1, OreDictionary.WILDCARD_VALUE),
                ThaumcraftApiHelper.makeCrystal(Aspect.MIND)).setRegistryName(NewCrimsonRevelations.MODID, "sanitation_scribing_tools_refill"));
        registry.register(new VerdantCharmToRing().setRegistryName(NewCrimsonRevelations.MODID, "verdant_charm_to_ring"));
        registry.register(new VerdantRingToCharm().setRegistryName(NewCrimsonRevelations.MODID, "verdant_ring_to_charm"));
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void onRegisterModelsEvent(@Nonnull final ModelRegistryEvent event) {
        // Item Models
        for (final Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.getRegistryName().getNamespace().equals(NewCrimsonRevelations.MODID)) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
            }
        }

        ClientRegistry.bindTileEntitySpecialRenderer(CRTileManaPod.class, new TileManaPodRenderer());
    }
}
