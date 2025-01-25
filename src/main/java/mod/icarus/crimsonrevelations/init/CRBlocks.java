package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
import mod.icarus.crimsonrevelations.block.CRBlockMaterial;
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
    @GameRegistry.ObjectHolder("magic_tallow_block")
    public static Block magicTallowBlock;

    @GameRegistry.ObjectHolder("mana_pod")
    public static Block manaPodBlock;

    @SubscribeEvent
    public static void registerBlocks(@Nonnull final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        registry.registerAll(
                CRRegistry.setup(new CRBlockMaterial(Material.ROCK, MapColor.SAND, 4.0F, 15.0F, SoundType.STONE), "magic_tallow_block")
        );

        registry.register(new CRBlockManaPod().setTranslationKey(NewCrimsonRevelations.MODID + "." + "mana_pod").setRegistryName(NewCrimsonRevelations.MODID, "mana_pod"));
        GameRegistry.registerTileEntity(CRTileManaPod.class, new ResourceLocation(NewCrimsonRevelations.MODID, "mana_pod"));
    }
}
