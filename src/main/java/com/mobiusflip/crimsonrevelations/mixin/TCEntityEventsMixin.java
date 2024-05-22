package com.mobiusflip.crimsonrevelations.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mobiusflip.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraftforge.event.entity.living.LivingDropsEvent;
import thaumcraft.common.lib.events.EntityEvents;

@Mixin(value = EntityEvents.class, remap = false)
public class TCEntityEventsMixin {
    @Inject(method = "livingDrops", at = @At(value = "HEAD"), cancellable = true)
    public void crLivingDrops(LivingDropsEvent event, CallbackInfo ci)
    {
    	// No hardcoded treasure bag drop
        if (!event.getEntity().world.isRemote && event.isRecentlyHit() && event.getEntity() instanceof EntityOvergrownTaintacle) ci.cancel();
    }
}
