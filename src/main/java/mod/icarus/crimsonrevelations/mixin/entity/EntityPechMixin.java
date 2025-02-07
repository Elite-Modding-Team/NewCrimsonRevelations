package mod.icarus.crimsonrevelations.mixin.entity;

import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.entities.monster.EntityPech;

@Mixin(EntityPech.class)
public class EntityPechMixin extends EntityMob {
    protected EntityPechMixin(World world) {
        super(world);
    }

    // Drop primal mana beans
    @Inject(method = "dropLoot", at = @At(value = "HEAD"))
    public void crDropLoot(boolean wasRecentlyHit, int lootingModifier, DamageSource source, CallbackInfo ci) {
        if (!CRConfig.general_settings.MANA_BEAN_PECH_LOOT) return;

        final Aspect[] aspects = Aspect.getPrimalAspects().toArray(new Aspect[0]);

        for (int a2 = 0; a2 < 1 + lootingModifier; ++a2) {
            if (this.rand.nextBoolean()) {
                final ItemStack is = new ItemStack(CRItems.MANA_BEAN);
                ((CRItemManaBean) is.getItem()).setAspects(is, new AspectList().add(aspects[this.rand.nextInt(aspects.length)], 1));
                this.entityDropItem(is, 1.5F);
            }
        }
    }
}
