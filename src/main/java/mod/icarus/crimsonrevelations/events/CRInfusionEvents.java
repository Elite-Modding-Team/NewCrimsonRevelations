package mod.icarus.crimsonrevelations.events;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.init.CREnchantments;
import mod.icarus.crimsonrevelations.init.CRRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.common.lib.enchantment.EnumInfusionEnchantment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;

@Mod.EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
public class CRInfusionEvents {
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHeadDrop(LivingDropsEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        Random random = entity.world.rand;

        if (entity.getAttackingEntity() != null) {
            int level = EnumInfusionEnchantment.getInfusionEnchantmentLevel(entity.getAttackingEntity().getHeldItemMainhand(), CREnchantments.BEHEADING);

            if (shouldDropHead(entity, level)) {
                Collection<ItemStack> heads = getHeadDrop(event.getEntityLiving());

                if (!heads.isEmpty()) {
                    // Pick one random ItemStack from the collection
                    ItemStack head = heads.toArray(new ItemStack[0])[random.nextInt(heads.size())];

                    if (head.getCount() > 1) {
                        head.setCount(random.nextInt(head.getCount()) + 1);
                    }

                    if (!head.isEmpty() && !alreadyContainsDrop(event, head)) {
                        EntityItem entityitem = new EntityItem(event.getEntityLiving().getEntityWorld(), event.getEntityLiving().posX, event.getEntityLiving().posY, event.getEntityLiving().posZ, head.copy());
                        entityitem.setDefaultPickupDelay();
                        event.getDrops().add(entityitem);
                    }
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onPlayerHeadDrop(LivingDeathEvent event) {
        // If keepInventory is true, players do not fire the living drops event
        EntityLivingBase entity = event.getEntityLiving();
        Random random = entity.world.rand;

        if (entity.getAttackingEntity() != null && entity.world.getGameRules().getBoolean("keepInventory") && entity instanceof EntityPlayerMP) {
            int level = EnumInfusionEnchantment.getInfusionEnchantmentLevel(entity.getAttackingEntity().getHeldItemMainhand(), CREnchantments.BEHEADING);

            if (shouldDropHead(entity, level)) {
                Collection<ItemStack> heads = getHeadDrop(entity);

                if (!heads.isEmpty()) {
                    // Pick one random ItemStack from the collection
                    ItemStack head = heads.toArray(new ItemStack[0])[random.nextInt(heads.size())];

                    if (head.getCount() > 1) {
                        head.setCount(random.nextInt(head.getCount()) + 1);
                    }

                    if (!head.isEmpty()) {
                        ((EntityPlayerMP) entity).dropItem(head.copy(), true);
                    }
                }
            }
        }
    }

    public static Collection<ItemStack> getHeadDrop(EntityLivingBase entity) {
        Collection<ItemStack> drops = new ArrayList<>();

        for (Map.Entry<Class<? extends EntityLivingBase>, Function<EntityLivingBase, ItemStack>> entry : CRRegistry.headDrops.entries()) {
            if (entry.getKey().isAssignableFrom(entity.getClass())) {
                ItemStack stack = entry.getValue().apply(entity);

                if (!stack.isEmpty()) {
                    drops.add(stack.copy());
                }
            }
        }
        return drops;
    }

    // Beheading code taken directly from Tinkers' Antique for maximum configuration.
    private static boolean alreadyContainsDrop(LivingDropsEvent event, ItemStack head) {
        // We want to add a new head drop even if players have their own head in their inventory
        if (event.getEntityLiving() instanceof EntityPlayerMP) {
            return false;
        }

        return event.getDrops().stream()
                .map(EntityItem::getItem)
                .anyMatch(drop -> ItemStack.areItemStacksEqual(drop, head));
    }

    private static boolean shouldDropHead(Entity entity, int level) {
        return level > 0 && level > entity.world.rand.nextInt(10);
    }
}
