package mod.icarus.crimsonrevelations.mixin.thaumometerscanning;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import thaumcraft.api.research.ScanningManager;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.client.lib.events.RenderEventHandler;
import thaumcraft.common.items.tools.ItemThaumometer;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.utils.EntityUtils;

// Courtesy of SumTingWong886
// Restores the slower scanning functionality from TC4. This is always disabled if TC4 Research Port is detected.
@Mixin(ItemThaumometer.class)
public abstract class ItemThaumometerMixin extends Item {
    @Shadow(remap = false)
    protected abstract RayTraceResult getRayTraceResultFromPlayerWild(World worldIn, EntityPlayer playerIn, boolean useLiquids);

    @Shadow(remap = false)
    private void updateAura(ItemStack stack, World world, EntityPlayerMP player) {
    }

    @Shadow(remap = false)
    public abstract void doScan(World worldIn, EntityPlayer playerIn);

    @Shadow(remap = false)
    private void drawFX(final World worldIn, final EntityPlayer playerIn) {
    }

    @Inject(method = "onItemRightClick", at = @At("HEAD"), cancellable = true)
    public void onItemRightClickInject(World world, EntityPlayer p, EnumHand hand, CallbackInfoReturnable<ActionResult<ItemStack>> cir) {
        ItemStack stack = p.getHeldItem(hand);
        p.setActiveHand(hand);
        cir.setReturnValue(new ActionResult<>(EnumActionResult.PASS, stack));
    }

    @Override
    public void onUsingTick(ItemStack stack, EntityLivingBase p, int count) {
        if (!(p instanceof EntityPlayer)) return;
        if (p.world.isRemote) {
            if (count <= 1) {
                p.stopActiveHand();
                p.world.playSound(p.posX, p.posY, p.posZ, SoundsTC.scan, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
            }

            if (count % 2 == 0) {
                this.drawFX(p.world, (EntityPlayer) p);
                p.world.playSound(p.posX, p.posY, p.posZ, SoundsTC.ticks, SoundCategory.PLAYERS, 0.2F, 0.45F + p.world.rand.nextFloat() * 0.1F, false);
            }
        } else {
            if (count <= 1) {
                this.doScan(p.world, (EntityPlayer) p);
                if (!p.world.isRemote) ((EntityPlayer) p).getCooldownTracker().setCooldown(this, 5);
            }
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 20;
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.NONE;
    }

    @Inject(method = "onUpdate", at = @At("HEAD"), cancellable = true)
    public void onUpdateInject(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected, CallbackInfo ci) {
        if (isSelected && !world.isRemote && entity.ticksExisted % 20 == 0 && entity instanceof EntityPlayerMP) {
            this.updateAura(stack, world, (EntityPlayerMP) entity);
        }

        if (isSelected && world.isRemote && entity.ticksExisted % 5 == 0 && entity instanceof EntityPlayer) {
            Entity target = EntityUtils.getPointedEntity(world, entity, 1.0, 16.0, 5.0F, true);

            if (target != null && ScanningManager.isThingStillScannable((EntityPlayer) entity, target)) {
                FXDispatcher.INSTANCE.scanHighlight(target);
            }

            RenderEventHandler.thaumTarget = target;
            RayTraceResult mop = this.getRayTraceResultFromPlayerWild(world, (EntityPlayer) entity, true);

            if (mop != null && ScanningManager.isThingStillScannable((EntityPlayer) entity, mop.getBlockPos())) {
                FXDispatcher.INSTANCE.scanHighlight(mop.getBlockPos());
            }
        }

        ci.cancel();
    }
}
