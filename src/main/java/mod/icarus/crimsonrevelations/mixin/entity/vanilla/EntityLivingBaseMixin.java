package mod.icarus.crimsonrevelations.mixin.entity.vanilla;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import mod.icarus.crimsonrevelations.config.CRConfig;
import thaumcraft.common.items.tools.ItemThaumometer;

@Mixin(EntityLivingBase.class)
public abstract class EntityLivingBaseMixin extends Entity {
    public EntityLivingBaseMixin(World world) {
        super(world);
    }

    // Prevents scanning from occasionally playing equip sounds.
    @Inject(method = "playEquipSound", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EntityLivingBase;playSound(Lnet/minecraft/util/SoundEvent;FF)V", shift = At.Shift.BEFORE), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true)
    protected void playEquipSoundInject(ItemStack stack, CallbackInfo ci, SoundEvent soundEvent, Item item) {
        if (stack.getItem() instanceof ItemThaumometer && !Loader.isModLoaded("oldresearch") && CRConfig.general_settings.THAUMOMETER_OLD_SCANNING) {
            ci.cancel();
        }
    }
}
