package mod.icarus.crimsonrevelations.events;

import java.util.List;

import baubles.api.BaublesApi;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.init.CRSoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.runicGirdleKinetic) > 2 && !(player.getCooldownTracker().hasCooldown(CRItems.runicGirdleKinetic))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_KINETIC, SoundCategory.PLAYERS, 0.8F, 1.0F);
                    player.world.createExplosion(player, player.posX, player.posY + player.height / 2.0F, player.posZ, 2.0F, false);

                    List<Entity> entities = player.world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().grow(3.0D, 3.0D, 3.0D));

                    for (Entity entity : entities) {
                        if (entity instanceof EntityLivingBase) {
                            EntityLivingBase mob = (EntityLivingBase) entity;
                            mob.knockBack(player, 2.0F, player.posX - mob.posX, player.posZ - mob.posZ);
                        }
                    }

                    ((EntityPlayer) player).addStat(StatList.getObjectUseStats(CRItems.runicGirdleKinetic));
                    ((EntityPlayer) player).getCooldownTracker().setCooldown(CRItems.runicGirdleKinetic, 20 * 20);
                }

                // Revitalizing Ring of Shielding - Gives 6 seconds of Regeneration II when the Runic Shielding is pierced (20 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.runicRingRegen) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.runicRingRegen))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_REGEN, SoundCategory.PLAYERS, 1.5F, 1.0F);
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6 * 20, 1, true, true));

                    ((EntityPlayer) player).addStat(StatList.getObjectUseStats(CRItems.runicRingRegen));
                    ((EntityPlayer) player).getCooldownTracker().setCooldown(CRItems.runicRingRegen, 20 * 20);
                }

                // Amulet of Emergency Shielding - Gives 8 points of Runic Shielding when the Runic Shielding is pierced (40 second cooldown).
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.runicAmuletEmergency) > -1 && !(player.getCooldownTracker().hasCooldown(CRItems.runicAmuletEmergency))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_EMERGENCY, SoundCategory.PLAYERS, 1.0F, 1.0F);
                    player.setAbsorptionAmount(charge + 8);

                    ((EntityPlayer) player).addStat(StatList.getObjectUseStats(CRItems.runicAmuletEmergency));
                    ((EntityPlayer) player).getCooldownTracker().setCooldown(CRItems.runicAmuletEmergency, 40 * 20);
                }

                // Charged Ring of Shielding - 25% chance to give 1 point of Runic Shielding, often restoring the Runic Shielding lost from damage.
                if (BaublesApi.isBaubleEquipped(player, CRItems.runicRingCharged) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.runicRingCharged)) && player.world.rand.nextDouble() <= 0.25D) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, CRSoundEvents.RUNIC_BAUBLE_CHARGE, SoundCategory.PLAYERS, 1.0F, 1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
                    player.setAbsorptionAmount(charge + 1);

                    ((EntityPlayer) player).addStat(StatList.getObjectUseStats(CRItems.runicRingCharged));
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
}
