package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectBlindingFlash;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectHex;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectPoison;
import mod.icarus.crimsonrevelations.casters.foci.FocusEffectTaintPoison;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.Thaumcraft;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.casters.FocusEngine;
import thaumcraft.api.golems.EnumGolemTrait;
import thaumcraft.api.golems.parts.GolemMaterial;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ScanBlock;
import thaumcraft.api.research.ScanEntity;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;

public class CRResearchRegistry {
    public static void init() {
        // Golem Materials
        GolemMaterial.register(
                new GolemMaterial("CR_CULT_PLATE", new String[]{"CR_GOLEM_MAT_CULT_PLATE"}, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/golem/mat_cult_plate.png"),
                        4342338, 22, 9, 3, // [Color, Health, Armor , Damage] - [1 = 0.5]
                        new ItemStack(CRItems.CRIMSON_PLATE), new ItemStack(ItemsTC.mechanismSimple), // Base Component, Base Mechanism
                        new EnumGolemTrait[]{EnumGolemTrait.LIGHT, EnumGolemTrait.FIREPROOF} // Starting Traits
                )
        );
        GolemMaterial.register(
                new GolemMaterial("CR_FLESH", new String[]{"CR_GOLEM_MAT_FLESH"}, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/golem/mat_flesh.png"),
                        13474967, 2, 0, 1, // [Color, Health, Armor , Damage] - [1 = 0.5]
                        new ItemStack(BlocksTC.fleshBlock), new ItemStack(ItemsTC.mechanismSimple), // Base Component, Base Mechanism
                        new EnumGolemTrait[]{EnumGolemTrait.REPAIR} // Starting Traits
                )
        );
        GolemMaterial.register(
                new GolemMaterial("CR_TALLOW", new String[]{"CR_GOLEM_MAT_TALLOW"}, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/golem/mat_tallow.png"),
                        12823156, 14, 4, 3, // [Color, Health, Armor , Damage] - [1 = 0.5]
                        new ItemStack(CRBlocks.MAGIC_TALLOW_BLOCK), new ItemStack(ItemsTC.mechanismSimple), // Base Component, Base Mechanism
                        new EnumGolemTrait[]{} // Starting Traits
                )
        );

        // Focus Effects
        FocusEngine.registerElement(FocusEffectBlindingFlash.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/blinding_flash.png"), 16776421);
        FocusEngine.registerElement(FocusEffectHex.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/hex.png"), 2815273);
        FocusEngine.registerElement(FocusEffectPoison.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/poison.png"), 9039872);

        // Research Categories
        ResearchCategories.registerCategory("REVELATIONS", "CrimsonRites", new AspectList(), new ResourceLocation(Thaumcraft.MODID, "textures/items/crimson_rites.png"), new ResourceLocation(NewCrimsonRevelations.MODID, "textures/gui/research_background.jpg"), new ResourceLocation(Thaumcraft.MODID, "textures/gui/gui_research_back_over.png"));

        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/auromancy"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/bestiary"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/golemancy"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/research"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/revelations"));

        ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/compat/thaumcraft"));

        if (CRConfig.general_settings.RESEARCH_FORBIDDEN_MAGIC) {
            ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/forbidden"));
        }

        // Research
        ScanningManager.addScannableThing(new ScanEntity("!CR_CRIMSON_CLERIC", EntityCultistCleric.class, true));
        ScanningManager.addScannableThing(new ScanEntity("!CR_CRIMSON_KNIGHT", EntityCultistKnight.class, true));

        ScanningManager.addScannableThing(new ScanBlock("!CR_MANA_POD", CRBlocks.MANA_POD));

        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.mod_integration_settings.TA_INTEGRATION) {
            // Focus Effects
            FocusEngine.registerElement(FocusEffectTaintPoison.class, new ResourceLocation(NewCrimsonRevelations.MODID, "textures/foci/taint_poison.png"), 10354925);

            // Research Categories
            ThaumcraftApi.registerResearchLocation(new ResourceLocation(NewCrimsonRevelations.MODID, "research/compat/thaumic_augmentation"));

            // Research
            ScanningManager.addScannableThing(new ScanEntity("!CR_OVERGROWN_TAINTACLE", EntityOvergrownTaintacle.class, true));
        }
    }
}
