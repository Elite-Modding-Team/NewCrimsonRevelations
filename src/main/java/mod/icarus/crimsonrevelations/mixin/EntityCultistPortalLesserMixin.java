package mod.icarus.crimsonrevelations.mixin;

import mod.icarus.crimsonrevelations.init.LootTableHandler;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;

import javax.annotation.Nullable;

@Mixin(value = EntityCultistPortalLesser.class, remap = false)
public class EntityCultistPortalLesserMixin extends EntityMob {
    public EntityCultistPortalLesserMixin(World world) {
        super(world);
    }

    @Override
    protected int getExperiencePoints(EntityPlayer player) {
        return 40;
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableHandler.LESSER_CULTIST_PORTAL;
    }
}
