package mod.icarus.crimsonrevelations.mixin.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import thaumcraft.common.entities.monster.cult.EntityCultist;

@Mixin(value = EntityCultist.class, remap = false)
public class EntityCultistMixin extends EntityMob {
    public EntityCultistMixin(World world) {
        super(world);
    }

    // Cultist projectiles no longer harm other cultists.
    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        Entity entity = source.getTrueSource();

        if (entity != null && entity instanceof EntityLivingBase && ((EntityLivingBase) entity).isOnSameTeam(this)) {
            return false;
        }

        return super.attackEntityFrom(source, amount);
    }
}
