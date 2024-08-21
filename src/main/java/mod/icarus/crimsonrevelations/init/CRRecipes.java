package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.oredict.OreDictionary;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.IngredientNBTTC;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

public class CRRecipes {
    public static void initArcaneCrafting() {
        // defaultGroup is meant for recipe books and is not really needed here.
        ResourceLocation defaultGroup = new ResourceLocation("");

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "embellished_crimson_fabric"), new ShapelessArcaneRecipe(
                defaultGroup, "CRIMSON_REVELATIONS_BASE", 10,
                new AspectList(),
                new ItemStack(CRItems.embellishedCrimsonFabric),
                new Object[]{CRItems.crimsonFabric, CRItems.crimsonPlate}));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "glyphstone"), new ShapedArcaneRecipe(
                defaultGroup, "ANCIENT_STONE", 15,
                new AspectList(),
                new ItemStack(BlocksTC.stoneAncientGlyphed, 6, 0),
                "SBS",
                "SBS",
                "SBS",
                'S', BlocksTC.stoneAncient, 'B', Items.BOOK));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_helm"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 25,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateHelm),
                "PPP",
                "P P",
                'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_chestplate"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateChest),
                "P P",
                "FEF",
                "PPP",
                'F', CRItems.crimsonFabric, 'E', new ItemStack(CRItems.embellishedCrimsonFabric), 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_greaves"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 40,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateLegs),
                "PBP",
                "P P",
                "P P",
                'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_hood"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 25,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeHelm),
                "FFF",
                "F F",
                'F', CRItems.crimsonFabric));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_robes"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeChest),
                "P P",
                "FEF",
                "FFF",
                'F', CRItems.crimsonFabric, 'E', new ItemStack(CRItems.embellishedCrimsonFabric), 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_leggings"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 40,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeLegs),
                "PBP",
                "F F",
                "F F",
                'F', CRItems.crimsonFabric, 'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_archer_helm"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 25,
                new AspectList(),
                new ItemStack(CRItems.crimsonArcherHelmet),
                "PPP",
                "F F",
                'F', CRItems.crimsonFabric, 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_archer_chestplate"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(CRItems.crimsonArcherChestplate),
                "P P",
                "FEF",
                "FPF",
                'F', CRItems.crimsonFabric, 'E', new ItemStack(CRItems.embellishedCrimsonFabric), 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_archer_greaves"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 40,
                new AspectList(),
                new ItemStack(CRItems.crimsonArcherGreaves),
                "PBP",
                "P P",
                "F F",
                'F', CRItems.crimsonFabric, 'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', CRItems.crimsonPlate));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_boots"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 15,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonBoots),
                "F F",
                "P P",
                'P', CRItems.crimsonPlate, 'F', CRItems.crimsonFabric));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimson_sword"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_SWORD", 50,
                new AspectList(),
                new ItemStack(CRItems.crimsonSword),
                "EPE",
                "EIE",
                "EPE",
                'P', CRItems.crimsonPlate, 'I', new ItemStack(Items.IRON_SWORD, 1, OreDictionary.WILDCARD_VALUE), 'E', Items.SPIDER_EYE));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "aer_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.aerArrow, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.AIR))));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "aqua_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.aquaArrow, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.WATER))));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ignis_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.ignisArrow, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.FIRE))));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ordo_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.ordoArrow, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.ORDER))));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "perditio_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.perditioArrow, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY))));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "terra_arrow"), new ShapedArcaneRecipe(
                defaultGroup, "PRIMAL_ARROWS", 10,
                new AspectList(),
                new ItemStack(CRItems.terraArrow, 4),
                " A ",
                "ACA",
                " A ",
                'A', Items.ARROW, 'C', new IngredientNBTTC(ThaumcraftApiHelper.makeCrystal(Aspect.EARTH))));
    }

    public static void initCrucible() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ancientstone"),
                new CrucibleRecipe("ANCIENT_STONE", new ItemStack(BlocksTC.stoneAncient), new ItemStack(BlocksTC.stoneArcane), new AspectList().add(Aspect.ELDRITCH, 5)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropyblazepowder"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.BLAZE_POWDER, 4, 0), new ItemStack(Items.BLAZE_ROD), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropybonemeal"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 6, 15), new ItemStack(Items.BONE), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropysunflower"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 11), new ItemStack(Blocks.DOUBLE_PLANT, 1, 0), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropylilac"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 13), new ItemStack(Blocks.DOUBLE_PLANT, 1, 1), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropyrose"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 1), new ItemStack(Blocks.DOUBLE_PLANT, 1, 4), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropypeony"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 9), new ItemStack(Blocks.DOUBLE_PLANT, 1, 5), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "entropysugar"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.SUGAR, 2, 0), new ItemStack(Items.REEDS, 1, 0), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "orderwool"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.STRING, 4, 0), "wool", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "orderglowstone"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.GLOWSTONE_DUST, 4, 0), new ItemStack(Blocks.GLOWSTONE), new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ordermagma"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.MAGMA_CREAM, 2, 0), new ItemStack(Blocks.MAGMA), new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "orderquartz"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.QUARTZ, 4, 0), "blockQuartz", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "ordersandstone"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Blocks.SAND, 4, 0), "sandstone", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "orderprismarine"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.PRISMARINE_SHARD, 4, 0), "blockPrismarine", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "orderchorus"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.CHORUS_FRUIT_POPPED, 4, 0), new ItemStack(Blocks.PURPUR_BLOCK), new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "quartzcluster"),
                new CrucibleRecipe("QUARTZ_PURIFICATION", new ItemStack(ItemsTC.clusters, 2, 7), "oreQuartz", new AspectList().add(Aspect.ORDER, 5).add(Aspect.CRYSTAL, 5)));
    }

    public static void initInfusion() {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "bone_bow"),
                new InfusionRecipe("BONE_BOW", new ItemStack(CRItems.boneBow), 2,
                        new AspectList().add(Aspect.AIR, 40).add(Aspect.FLIGHT, 40).add(Aspect.ENTROPY, 40),
                        new ItemStack(Items.BOW),
                        ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY, 1),
                        ThaumcraftApiHelper.makeCrystal(Aspect.ENTROPY, 1), new ItemStack(ItemsTC.nuggets, 1, 10),
                        new ItemStack(Blocks.BONE_BLOCK)));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "crimsonblade"),
                new InfusionRecipe("CRIMSON_BLADE", new ItemStack(ItemsTC.crimsonBlade), 7,
                        new AspectList().add(Aspect.AVERSION, 75).add(Aspect.DEATH, 75).add(Aspect.TRAP, 25).add(Aspect.DESIRE, 25),
                        new ItemStack(ItemsTC.voidSword),
                        ThaumcraftApiHelper.makeCrystal(Aspect.AVERSION), ThaumcraftApiHelper.makeCrystal(Aspect.DEATH), "plateVoid", "plateVoid", CRItems.crimsonFabric, CRItems.crimsonFabric));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "praetor_helm"),
                new InfusionRecipe("PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorHelm), 2,
                        new AspectList().add(Aspect.METAL, 50).add(Aspect.ELDRITCH, 25).add(Aspect.PROTECT, 20),
                        new ItemStack(ItemsTC.crimsonPlateHelm),
                        CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "praetor_chestplate"),
                new InfusionRecipe("PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorChest), 2,
                        new AspectList().add(Aspect.METAL, 50).add(Aspect.ELDRITCH, 25).add(Aspect.PROTECT, 30),
                        new ItemStack(ItemsTC.crimsonPlateChest),
                        CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.embellishedCrimsonFabric));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation(NewCrimsonRevelations.MODID, "praetor_greaves"),
                new InfusionRecipe("PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorLegs), 2,
                        new AspectList().add(Aspect.METAL, 50).add(Aspect.ELDRITCH, 25).add(Aspect.PROTECT, 25),
                        new ItemStack(ItemsTC.crimsonPlateLegs),
                        CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.crimsonPlate, CRItems.embellishedCrimsonFabric));
    }
}
