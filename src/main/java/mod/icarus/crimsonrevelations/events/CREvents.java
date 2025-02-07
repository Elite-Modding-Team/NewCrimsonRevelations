package mod.icarus.crimsonrevelations.events;

import baubles.api.BaublesApi;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.block.CRBlockManaPod;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.init.CRSoundEvents;
import mod.icarus.crimsonrevelations.world.WorldGenManaPods;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.common.world.biomes.BiomeGenMagicalForest;

import java.util.List;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CREvents {
    @SubscribeEvent
    public static void runicShieldHurtEvent(LivingHurtEvent event) {
        World world = event.getEntity().world;
        Entity source = event.getSource().getTrueSource();

        if (event.getEntityLiving() instanceof EntityPlayer && source instanceof EntityLivingBase && !world.isRemote) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int charge = (int) player.getAbsorptionAmount();

            // Kinetic Girdle of Shielding - Explodes when the Runic Shielding is pierced (20 second cooldown).
            if (charge > 0) {
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_GIRDLE_KINETIC) > 2 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_GIRDLE_KINETIC))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_KINETIC, SoundCategory.PLAYERS, 0.8F, 1.0F);
                    player.world.createExplosion(player, player.posX, player.posY + player.height / 2.0F, player.posZ, 2.0F, false);

                    List<Entity> entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(3.0D, 3.0D, 3.0D));

                    for (Entity entity : entities) {
                        if (entity instanceof EntityLivingBase) {
                            EntityLivingBase mob = (EntityLivingBase) entity;
                            mob.knockBack(player, 2.0F, player.posX - mob.posX, player.posZ - mob.posZ);
                        }
                    }

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_GIRDLE_KINETIC));
                    player.getCooldownTracker().setCooldown(CRItems.RUNIC_GIRDLE_KINETIC, 20 * 20);
                }

                // Revitalizing Ring of Shielding - Gives 6 seconds of Regeneration II when the Runic Shielding is pierced (20 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_RING_REGEN) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_RING_REGEN))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_REGEN, SoundCategory.PLAYERS, 1.5F, 1.0F);
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6 * 20, 1, true, true));

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_RING_REGEN));
                    player.getCooldownTracker().setCooldown(CRItems.RUNIC_RING_REGEN, 20 * 20);
                }

                // Amulet of Emergency Shielding - Gives 8 points of Runic Shielding when the Runic Shielding is pierced (40 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_AMULET_EMERGENCY) > -1 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_AMULET_EMERGENCY))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_EMERGENCY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.setAbsorptionAmount(charge + 8);

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_AMULET_EMERGENCY));
                    player.getCooldownTracker().setCooldown(CRItems.RUNIC_AMULET_EMERGENCY, 40 * 20);
                }

                // Charged Ring of Shielding - 25% chance to give 1 point of Runic Shielding, often restoring the Runic Shielding lost from damage.
                if (BaublesApi.isBaubleEquipped(player, CRItems.RUNIC_RING_CHARGED) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.RUNIC_RING_CHARGED)) && player.world.rand.nextDouble() <= 0.25D) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_CHARGE, SoundCategory.PLAYERS, 1.0F, 1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
                    player.setAbsorptionAmount(charge + 1);

                    player.addStat(StatList.getObjectUseStats(CRItems.RUNIC_RING_CHARGED));
                }
            }
        }
    }

    @SubscribeEvent
    public static void onAttackEvent(LivingAttackEvent event) {
        for (ItemStack stack : event.getEntityLiving().getArmorInventoryList()) {

            // Prevents screen shaking and damage sound.
            if (stack.getItem() == CRItems.meteorBoots) {
                if (event.getSource() == DamageSource.HOT_FLOOR) {
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDamageEvent(LivingDamageEvent event) {
        for (ItemStack stack : event.getEntityLiving().getArmorInventoryList()) {

            // Immune to these damage types.
            if (stack.getItem() == CRItems.meteorBoots) {
                if (event.getSource() == DamageSource.HOT_FLOOR) {
                    event.setAmount(0.0F);
                    event.setCanceled(true);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onUseBonemeal(BonemealEvent event) {
        // Prevent bonemeal from working on mana beans
        if (event.getBlock().getBlock() instanceof CRBlockManaPod) {
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onDecorateBiome(DecorateBiomeEvent event) {
        if (event.getWorld().getBiome(event.getPos()) instanceof BiomeGenMagicalForest) {
            WorldGenManaPods worldGenManaPods = new WorldGenManaPods();

            for (int k = 0; k < CRConfig.general_settings.MANA_BEAN_GENERATION_FREQUENCY; k++) {
                int l = event.getPos().getX() + event.getWorld().rand.nextInt(16) + 8;
                byte b0 = 64;
                int i1 = event.getPos().getZ() + event.getWorld().rand.nextInt(16) + 8;
                worldGenManaPods.generate(event.getWorld(), event.getWorld().rand, new BlockPos(l, b0, i1));
            }
        }
    }
}
