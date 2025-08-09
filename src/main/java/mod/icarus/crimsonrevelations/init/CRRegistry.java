package mod.icarus.crimsonrevelations.init;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@SuppressWarnings({"rawtypes", "unchecked"})
@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CRRegistry {
    public static final Multimap<Class<? extends EntityLivingBase>, Function<EntityLivingBase, ItemStack>> headDrops = ArrayListMultimap.create();
    public static final Multimap<Class<? extends EntityLivingBase>, ItemStack> headDropsRaw = ArrayListMultimap.create();

    @Nonnull
    public static <T extends IForgeRegistryEntry> T setup(@Nonnull final T entry, @Nonnull final String name) {
        return setup(entry, new ResourceLocation(NewCrimsonRevelations.MODID, name));
    }

    @Nonnull
    public static <T extends IForgeRegistryEntry> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry to setup must not be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign must not be null!");

        entry.setRegistryName(registryName);
        if (entry instanceof Block)
            ((Block) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(NewCrimsonRevelations.tabCR);
        if (entry instanceof Item)
            ((Item) entry).setTranslationKey(registryName.getNamespace() + "." + registryName.getPath()).setCreativeTab(NewCrimsonRevelations.tabCR);
        return entry;
    }

    // Gets biomes from selected entity.
    public static Biome[] getEntityBiomes(Class<? extends Entity> spawn) {
        List<Biome> biomes = new ArrayList<>();

        for (Biome biome : Biome.REGISTRY) {
            List<Biome.SpawnListEntry> spawnList = biome.getSpawnableList(EnumCreatureType.MONSTER);

            for (Biome.SpawnListEntry list : spawnList)
                if (list.entityClass == spawn) {
                    biomes.add(biome);
                    break;
                }
        }

        return biomes.toArray(new Biome[0]);
    }

    /**
     * Registers a beheading head drop for all entities that extend the given class
     *
     * @param clazz Entity class
     * @param head  Head that drops from that entity
     */
    public static void registerHeadDropForAll(Class<? extends EntityLivingBase> clazz, ItemStack head) {
        for (EntityEntry entry : ForgeRegistries.ENTITIES) {
            Class<? extends Entity> entityClass = entry.getEntityClass();
            
            if (clazz.isAssignableFrom(entityClass)) {
                registerHeadDrop((Class<? extends EntityLivingBase>) entityClass, head);
            }
        }
    }

    /**
     * Registers a beheading head drop for an entity
     *
     * @param clazz    Entity class
     * @param callback Callback function, takes entity as a parameter and returns an item stack
     */
    public static void registerHeadDrop(Class<? extends EntityLivingBase> clazz, Function<EntityLivingBase, ItemStack> callback) {
        headDrops.put(clazz, callback);
    }

    /**
     * Registers a beheading head drop for an entity
     *
     * @param clazz Entity class
     * @param head  Head that drops from that entity
     */
    public static void registerHeadDrop(Class<? extends EntityLivingBase> clazz, ItemStack head) {
        final ItemStack safeStack = head.copy();
        
        registerHeadDrop(clazz, e -> safeStack);
        headDropsRaw.put(clazz, head);
    }
}
