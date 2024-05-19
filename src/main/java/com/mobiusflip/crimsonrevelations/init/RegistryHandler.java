package com.mobiusflip.crimsonrevelations.init;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.mobiusflip.crimsonrevelations.CrimsonRevelations;
import com.mobiusflip.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockSlab;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
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
import thaumcraft.api.aspects.AspectEventProxy;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = CrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(CrimsonRevelations.MODID)
public class RegistryHandler {
    @GameRegistry.ObjectHolder("crimson_fabric")
    public static Item crimsonFabric;
    @GameRegistry.ObjectHolder("crimson_plate")
    public static Item crimsonPlate;

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(
                setup(new Item(), "crimson_fabric"),
                setup(new Item(), "crimson_plate")
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

        entityRegistryHelper("overgrown_taintacle", EntityOvergrownTaintacle.class, id++, 64, 3, true, 0x1C1A2F, 0x5649B4);
    }

    @SubscribeEvent
    public static void registerAspects(AspectRegistryEvent event) {
        ThaumcraftApi.registerEntityTag(CrimsonRevelations.MODID + ".overgrown_taintacle", new AspectList().add(Aspect.FLUX, 30).add(Aspect.ELDRITCH, 30).add(Aspect.AVERSION, 30).add(Aspect.PLANT, 30));
    }

    public static void entityRegistryHelper(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates, int eggColor1, int eggColor2) {
        EntityRegistry.registerModEntity(new ResourceLocation(CrimsonRevelations.MODID, name), clazz, CrimsonRevelations.MODID + "." + name, id, CrimsonRevelations.instance, trackingRange,
                updateFrequency, sendVelocityUpdates, eggColor1, eggColor2);
    }

    public static void egglessEntityRegistryHelper(String name, Class<? extends Entity> clazz, int id, int trackingRange, int updateFrequency, boolean sendVelocityUpdates) {
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
