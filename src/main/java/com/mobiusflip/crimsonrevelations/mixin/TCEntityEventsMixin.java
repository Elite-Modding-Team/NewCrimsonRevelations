package com.mobiusflip.crimsonrevelations.mixin;

import com.mobiusflip.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.damagesource.DamageSourceThaumcraft;
import thaumcraft.common.lib.events.EntityEvents;

@Mixin(value = EntityEvents.class, remap = false)
public class TCEntityEventsMixin {
    @Inject(method = "livingDrops", at = @At(value = "HEAD"), cancellable = true)
    private static void crLivingDrops(LivingDropsEvent event, CallbackInfo ci) {
        // No hardcoded treasure bag drop
        if (!event.getEntity().world.isRemote && event.isRecentlyHit() && event.getEntity() instanceof EntityOvergrownTaintacle)
            ci.cancel();

        // We will need this to make sure liquid death can properly work on our affected mobs again
        if (event.getSource() == DamageSourceThaumcraft.dissolve && event.getEntity() instanceof EntityOvergrownTaintacle) {
            AspectList aspects = AspectHelper.getEntityAspects(event.getEntityLiving());

            if (aspects != null && aspects.size() > 0) {
                Aspect[] list = aspects.getAspects();

                for (int f = MathHelper.getInt(event.getEntity().getEntityWorld().rand, 1, 1 + aspects.visSize() / 10), a = 0; a < f; ++a) {
                    Aspect aspect = list[event.getEntity().getEntityWorld().rand.nextInt(list.length)];
                    ItemStack stack = ThaumcraftApiHelper.makeCrystal(aspect);
                    event.getDrops().add(new EntityItem(event.getEntity().world, event.getEntityLiving().posX, event.getEntityLiving().posY + event.getEntityLiving().getEyeHeight(), event.getEntityLiving().posZ, stack));
                }
            }
        }
    }
}
