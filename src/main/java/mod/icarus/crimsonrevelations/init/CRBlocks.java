package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.CRBlockEtherealBloom;
import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
import mod.icarus.crimsonrevelations.block.CRBlockMaterial;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.item.CRItemDistortedPickaxe;
import mod.icarus.crimsonrevelations.tile.CRTileEtherealBloom;
import mod.icarus.crimsonrevelations.tile.CRTileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import javax.annotation.Nonnull;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CRBlocks {
    public static final CRBlockMaterial MAGIC_TALLOW_BLOCK = null;
    public static final CRBlockManaPod MANA_POD = null;

    // Optional Content
    public static final CRBlockEtherealBloom ETHEREAL_BLOOM = null;

    @SubscribeEvent
    public static void registerBlocks(@Nonnull final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        registry.registerAll(
                CRRegistry.setup(new CRBlockMaterial(Material.ROCK, MapColor.SAND, 4.0F, 15.0F, SoundType.STONE), "magic_tallow_block"),
                CRRegistry.setup(new CRBlockManaPod(), "mana_pod")
        );

        if (CRConfig.ethereal_bloom.enableEtherealBloom) {
            registry.register(CRRegistry.setup(new CRBlockEtherealBloom(), "ethereal_bloom"));
            GameRegistry.registerTileEntity(CRTileEtherealBloom.class, new ResourceLocation(NewCrimsonRevelations.MODID, "ethereal_bloom"));
        }

        GameRegistry.registerTileEntity(CRTileManaPod.class, new ResourceLocation(NewCrimsonRevelations.MODID, "mana_pod"));
    }
}
