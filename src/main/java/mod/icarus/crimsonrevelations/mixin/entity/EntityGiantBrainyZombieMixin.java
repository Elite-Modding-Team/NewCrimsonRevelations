package mod.icarus.crimsonrevelations.mixin.entity;

import mod.icarus.crimsonrevelations.config.CRConfig;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;

@Mixin(value = EntityGiantBrainyZombie.class, remap = false)
public class EntityGiantBrainyZombieMixin extends EntityMob {
    public EntityGiantBrainyZombieMixin(World world) {
        super(world);
    }

    @Override
    public boolean getCanSpawnHere() {
        if (!CRConfig.general_settings.FURIOUS_ZOMBIE_UNDERGROUND_SPAWNING) {
            return super.getCanSpawnHere() && this.world.canSeeSky(new BlockPos(this));
        } else {
            return super.getCanSpawnHere();
        }
    }
}
