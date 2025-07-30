package mod.icarus.crimsonrevelations.mixin.entity;

import mod.icarus.crimsonrevelations.entity.EntityCultistArcher;
import mod.icarus.crimsonrevelations.init.CRLootTables;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;
import thaumcraft.common.entities.monster.cult.EntityCultistKnight;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;
import thaumcraft.common.lib.SoundsTC;

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
        return CRLootTables.LESSER_CULTIST_PORTAL;
    }

    @Redirect(method = "onUpdate", at = @At(value = "INVOKE",
            target = "Lthaumcraft/common/entities/monster/cult/EntityCultistPortalLesser;spawnMinions()V",
            remap = false), remap = true)
    private void spawnMinionsRedirect(EntityCultistPortalLesser instance) {
        spawnCultists();
    }

    void spawnCultists() {
        EntityLiving cultist = null;
        int random;
        random = this.world.rand.nextInt(4);

        switch (random) {
            case 0:
                cultist = new EntityCultistCleric(this.world);
                break;
            case 1:
                cultist = new EntityCultistArcher(this.world);
                break;
            case 2:
                cultist = new EntityCultistKnight(this.world);
                break;
            case 3:
                cultist = new EntityCultistKnight(this.world);
                break;
        }

        cultist.setPosition(this.posX + this.rand.nextFloat() - this.rand.nextFloat(), this.posY + 0.25, this.posZ + this.rand.nextFloat() - this.rand.nextFloat());
        cultist.onInitialSpawn(this.world.getDifficultyForLocation(new BlockPos(cultist.getPosition())), null);

        // Restores portal spawning particles from TC5
        if (cultist != null) {
            for (int i = 0; i < 20; ++i) {
                double d0 = this.rand.nextGaussian() * 0.05;
                double d2 = this.rand.nextGaussian() * 0.05;
                double d3 = this.rand.nextGaussian() * 0.05;
                double d4 = 2.0;

                FXDispatcher.GenPart pp = new FXDispatcher.GenPart();
                pp.age = 10 + world.rand.nextInt(10);
                pp.alpha = new float[]{0.8F, 0.8F};
                pp.grid = 32;
                pp.layer = 1;
                pp.partStart = 337;
                pp.partInc = 1;
                pp.partNum = 5;
                float s = (float) (3.0F + world.rand.nextGaussian() * 2.0F);
                pp.scale = new float[]{s, s};
                pp.redEnd = 0.6F;
                pp.greenEnd = 0.0F;
                pp.blueEnd = 0.0F;

                FXDispatcher.INSTANCE.drawGenericParticles(cultist.posX + this.rand.nextFloat() * cultist.width * 2.0f - cultist.width + d0 * d4,
                        cultist.posY + this.rand.nextFloat() * cultist.height + d2 * d4,
                        cultist.posZ + this.rand.nextFloat() * cultist.width * 2.0f - cultist.width + d3 * d4, d0, d2, d3, pp);
            }
        }

        this.world.spawnEntity(cultist);
        cultist.playSound(SoundsTC.wandfail, 1.0F, 1.0F);
        this.attackEntityFrom(DamageSource.OUT_OF_WORLD, (5 + this.rand.nextInt(5)));
    }
}
