package mod.icarus.crimsonrevelations.events;

import java.util.List;

import baubles.api.BaublesApi;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.init.CRItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import thaumcraft.common.lib.SoundsTC;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CREvents {
    @SubscribeEvent
    public static void onHurtEvent(LivingHurtEvent event) {
        World world = event.getEntity().world;
        Entity source = event.getSource().getTrueSource();

        if (event.getEntityLiving() instanceof EntityPlayer && source instanceof EntityLivingBase && !world.isRemote) {
            EntityPlayer player = (EntityPlayer) event.getEntityLiving();
            int charge = (int) player.getAbsorptionAmount();

            if (charge > 0) {
                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.runicGirdleKinetic) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.runicGirdleKinetic))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsTC.poof, SoundCategory.PLAYERS, 1.0F, 1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
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

                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.runicRingRegen) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.runicRingRegen))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsTC.runicShieldEffect, SoundCategory.PLAYERS, 1.0F, 1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
                    player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 6 * 20, 1, true, true));

                    ((EntityPlayer) player).addStat(StatList.getObjectUseStats(CRItems.runicRingRegen));
                    ((EntityPlayer) player).getCooldownTracker().setCooldown(CRItems.runicRingRegen, 20 * 20);
                }

                if (charge <= event.getAmount() && BaublesApi.isBaubleEquipped(player, CRItems.runicAmuletEmergency) > 0 && !(player.getCooldownTracker().hasCooldown(CRItems.runicAmuletEmergency))) {
                    player.world.playSound(null, player.posX, player.posY, player.posZ, SoundsTC.egscreech, SoundCategory.PLAYERS, 1.0F, 1.0F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
                    player.setAbsorptionAmount(charge + 8);

                    ((EntityPlayer) player).addStat(StatList.getObjectUseStats(CRItems.runicAmuletEmergency));
                    ((EntityPlayer) player).getCooldownTracker().setCooldown(CRItems.runicAmuletEmergency, 60 * 20);
                }
            }
        }
    }
}
