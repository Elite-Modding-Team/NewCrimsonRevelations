package com.icarus.crimsonrevelations.init;

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
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

public class RecipeHandler {
    public static void initArcaneCrafting() {
        // defaultGroup is meant for recipe books and is not really needed here.
        ResourceLocation defaultGroup = new ResourceLocation("");

        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimsonbanner"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_REVELATIONS", 10,
                new AspectList(),
                new ItemStack(BlocksTC.bannerCrimsonCult), new Object[]{
                "FS",
                "PS",
                "FD",
                'S', "stickWood", 'D', "slabWood", 'F', RegistryHandler.crimsonFabric, 'P', RegistryHandler.crimsonPlate
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "glyphstone"), new ShapedArcaneRecipe(
                defaultGroup, "ANCIENT_STONE", 15,
                new AspectList(),
                new ItemStack(BlocksTC.stoneAncientGlyphed, 6, 0), new Object[]{
                "SBS",
                "SBS",
                "SBS",
                'S', BlocksTC.stoneAncient, 'B', Items.BOOK
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_helm"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateHelm), new Object[]{
                "PPP",
                "P P",
                'P', RegistryHandler.crimsonPlate
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_chestplate"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateChest), new Object[]{
                "P P",
                "FBF",
                "PFP",
                'F', RegistryHandler.crimsonFabric, 'B', new ItemStack(BlocksTC.bannerCrimsonCult), 'P', RegistryHandler.crimsonPlate
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_greaves"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonPlateLegs), new Object[]{
                "PBP",
                "P P",
                "P P",
                'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', RegistryHandler.crimsonPlate
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_hood"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeHelm), new Object[]{
                "FFF",
                "F F",
                'F', RegistryHandler.crimsonFabric
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_robes"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeChest), new Object[]{
                "F F",
                "PBP",
                "FPF",
                'F', RegistryHandler.crimsonFabric, 'B', new ItemStack(BlocksTC.bannerCrimsonCult), 'P', RegistryHandler.crimsonPlate
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_leggings"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonRobeLegs), new Object[]{
                "PBP",
                "F F",
                "F F",
                'F', RegistryHandler.crimsonFabric, 'B', new ItemStack(ItemsTC.baubles, 1, 2), 'P', RegistryHandler.crimsonPlate
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_boots"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_ARMOR", 50,
                new AspectList(),
                new ItemStack(ItemsTC.crimsonBoots), new Object[]{
                "F F",
                "P P",
                'P', RegistryHandler.crimsonPlate, 'F', RegistryHandler.crimsonFabric
        }
        ));
        ThaumcraftApi.addArcaneCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimson_sword"), new ShapedArcaneRecipe(
                defaultGroup, "CRIMSON_SWORD", 50,
                new AspectList(),
                new ItemStack(RegistryHandler.crimsonSword), new Object[]{
                "EPE",
                "EIE",
                "EPE",
                'P', RegistryHandler.crimsonPlate, 'I', new ItemStack(Items.IRON_SWORD, 1, OreDictionary.WILDCARD_VALUE), 'E', Items.SPIDER_EYE
        }
        ));
    }

    public static void initCrucible() {
        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "ancientstone"),
                new CrucibleRecipe("ANCIENT_STONE", new ItemStack(BlocksTC.stoneAncient), new ItemStack(BlocksTC.stoneArcane), new AspectList().add(Aspect.ELDRITCH, 5)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropyblazepowder"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.BLAZE_POWDER, 4, 0), new ItemStack(Items.BLAZE_ROD), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropybonemeal"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 6, 15), new ItemStack(Items.BONE), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropysunflower"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 11), new ItemStack(Blocks.DOUBLE_PLANT, 1, 0), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropylilac"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 13), new ItemStack(Blocks.DOUBLE_PLANT, 1, 1), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropyrose"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 1), new ItemStack(Blocks.DOUBLE_PLANT, 1, 4), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropypeony"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.DYE, 4, 9), new ItemStack(Blocks.DOUBLE_PLANT, 1, 5), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "entropysugar"),
                new CrucibleRecipe("ENTROPIC_PROCESSING", new ItemStack(Items.SUGAR, 2, 0), new ItemStack(Items.REEDS, 1, 0), new AspectList().add(Aspect.ENTROPY, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "orderwool"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.STRING, 4, 0), "wool", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "orderglowstone"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.GLOWSTONE_DUST, 4, 0), new ItemStack(Blocks.GLOWSTONE), new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "ordermagma"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.MAGMA_CREAM, 2, 0), new ItemStack(Blocks.MAGMA), new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "orderquartz"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.QUARTZ, 4, 0), "blockQuartz", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "ordersandstone"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Blocks.SAND, 4, 0), "sandstone", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "orderprismarine"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.PRISMARINE_SHARD, 4, 0), "blockPrismarine", new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "orderchorus"),
                new CrucibleRecipe("ORDERED_DECONSTRUCTION", new ItemStack(Items.CHORUS_FRUIT_POPPED, 4, 0), new ItemStack(Blocks.PURPUR_BLOCK), new AspectList().add(Aspect.ORDER, 25)));

        ThaumcraftApi.addCrucibleRecipe(new ResourceLocation("crimsonrevelations", "quartzcluster"),
                new CrucibleRecipe("QUARTZ_PURIFICATION", new ItemStack(ItemsTC.clusters, 2, 7), "oreQuartz", new AspectList().add(Aspect.ORDER, 5).add(Aspect.CRYSTAL, 5)));
    }

    public static void initInfusion() {
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("crimsonrevelations", "crimsonblade"),
                new InfusionRecipe("CRIMSON_BLADE", new ItemStack(ItemsTC.crimsonBlade), 7,
                        new AspectList().add(Aspect.AVERSION, 75).add(Aspect.DEATH, 75).add(Aspect.TRAP, 25).add(Aspect.DESIRE, 25),
                        new ItemStack(ItemsTC.voidSword), new Object[]{
                        ThaumcraftApiHelper.makeCrystal(Aspect.AVERSION), ThaumcraftApiHelper.makeCrystal(Aspect.DEATH), "plateVoid", "plateVoid", RegistryHandler.crimsonFabric, RegistryHandler.crimsonFabric
                }
                ));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("crimsonrevelations", "praetor_helm"),
                new InfusionRecipe("PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorHelm), 2,
                        new AspectList().add(Aspect.METAL, 50).add(Aspect.ELDRITCH, 25).add(Aspect.PROTECT, 20),
                        new ItemStack(ItemsTC.crimsonPlateHelm), new Object[]{
                        RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate
                }
                ));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("crimsonrevelations", "praetor_chestplate"),
                new InfusionRecipe("PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorChest), 2,
                        new AspectList().add(Aspect.METAL, 50).add(Aspect.ELDRITCH, 25).add(Aspect.PROTECT, 30),
                        new ItemStack(ItemsTC.crimsonPlateChest), new Object[]{
                        RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, BlocksTC.bannerCrimsonCult
                }
                ));
        ThaumcraftApi.addInfusionCraftingRecipe(new ResourceLocation("crimsonrevelations", "praetor_greaves"),
                new InfusionRecipe("PRAETOR_ARMOR", new ItemStack(ItemsTC.crimsonPraetorLegs), 2,
                        new AspectList().add(Aspect.METAL, 50).add(Aspect.ELDRITCH, 25).add(Aspect.PROTECT, 25),
                        new ItemStack(ItemsTC.crimsonPlateLegs), new Object[]{
                        RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, RegistryHandler.crimsonPlate, BlocksTC.bannerCrimsonCult
                }
                ));
    }
}
