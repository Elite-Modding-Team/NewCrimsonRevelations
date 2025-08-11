package mod.icarus.crimsonrevelations.casters.foci;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.casters.FocusEffect;
import thaumcraft.api.casters.NodeSetting;
import thaumcraft.api.casters.Trajectory;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXBlockBamf;

// While identical to Curse, Hex sacrifices some effects and the static field in favor of more damage and the option to have all effects applied at once
public class FocusEffectHex extends FocusEffect {
    @Override
    public Aspect getAspect() {
        return Aspect.DEATH;
    }

    @Override
    public String getKey() {
        return "focus." + NewCrimsonRevelations.MODID + ".hex";
    }

    @Override
    public NodeSetting[] createSettings() {
        int[] nightshade = {0, 1};
        String[] nightshadeDesc = {"focus.common.no", "focus.common.yes"};
        return new NodeSetting[]{new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("duration", "focus.common.duration", new NodeSetting.NodeSettingIntRange(1, 10)), new NodeSetting("nightshade", "focus.common.nightshade", new NodeSetting.NodeSettingIntList(nightshade, nightshadeDesc))};
    }

    @Override
    public int getComplexity() {
        return this.getSettingValue("duration") + getSettingValue("nightshade") * 4 + this.getSettingValue("power") * 3;
    }

    @Override
    public float getDamageForDisplay(float finalPower) {
        return (2.0F + this.getSettingValue("power")) * finalPower;
    }

    @Override
    public String getResearch() {
        return "CR_FOCUS_HEX";
    }

    @Override
    public boolean execute(RayTraceResult target, Trajectory trajectory, float finalPower, int num) {
        PacketHandler.INSTANCE.sendToAllAround(new PacketFXBlockBamf(target.hitVec.x, target.hitVec.y, target.hitVec.z, 2815273, true, true, null), new NetworkRegistry.TargetPoint(getPackage().world.provider.getDimension(), target.hitVec.x, target.hitVec.y, target.hitVec.z, 64.0D));

        if (target.typeOfHit == RayTraceResult.Type.ENTITY && target.entityHit != null) {
            float damage = this.getDamageForDisplay(finalPower);
            int duration = 20 * this.getSettingValue("duration");
            int potency = (int) (this.getSettingValue("power") * finalPower / 2.0F);

            if (potency < 0) {
                potency = 0;
            }

            target.entityHit.attackEntityFrom(DamageSource.causeIndirectMagicDamage((target.entityHit != null) ? target.entityHit : this.getPackage().getCaster(), this.getPackage().getCaster()), damage);

            // If Nightshade is enabled, apply all effects at once instead.
            if (target.entityHit instanceof EntityLivingBase) {
                if (getSettingValue("nightshade") > 0) {
                    ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, duration, Math.round(potency)));
                    ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, Math.round(potency)));
                    ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, duration, Math.round(potency)));
                    ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.UNLUCK, duration * 3, Math.round(potency)));
                } else {
                    ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, duration, Math.round(potency)));
                    float c = 0.85F;

                    if (getPackage().world.rand.nextFloat() < c) {
                        ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, duration, Math.round(potency)));
                        c -= 0.15F;
                    }

                    if (getPackage().world.rand.nextFloat() < c) {
                        ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS, duration, Math.round(potency)));
                        c -= 0.15F;
                    }

                    if (getPackage().world.rand.nextFloat() < c) {
                        ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.UNLUCK, duration * 3, Math.round(potency)));
                    }
                }
            }

            return true;
        }

        return false;
    }

    @Override
    public void onCast(Entity caster) {
        caster.world.playSound(null, caster.getPosition().up(), SoundEvents.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.PLAYERS, 0.15F, 1.0F + caster.world.rand.nextFloat() / 2.0F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderParticleFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        FXDispatcher.INSTANCE.drawGenericParticles16(posX, posY, posZ, velX, velY, velZ, 0.5F, 0.0F, 1.0F, 0.7F, false, 108 + world.rand.nextInt(4), 1, 1, 4, 0, (float) (2.0F + world.rand.nextGaussian() * 0.5F), 0.0F, 0);
    }
}