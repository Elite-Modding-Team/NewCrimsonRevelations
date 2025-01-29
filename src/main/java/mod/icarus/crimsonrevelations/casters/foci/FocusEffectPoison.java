package mod.icarus.crimsonrevelations.casters.foci;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
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
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXGeneric;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXFocusPartImpact;

public class FocusEffectPoison extends FocusEffect {
    @Override
    public Aspect getAspect() {
        return Aspect.ALCHEMY;
    }

    @Override
    public String getKey() {
        return "focus." + NewCrimsonRevelations.MODID + ".poison";
    }

    @Override
    public NodeSetting[] createSettings() {
        return new NodeSetting[]{new NodeSetting("power", "focus.common.power", new NodeSetting.NodeSettingIntRange(1, 5)), new NodeSetting("duration", "focus.common.double_duration", new NodeSetting.NodeSettingIntRange(1, 10))};
    }

    @Override
    public int getComplexity() {
        return this.getSettingValue("duration") + this.getSettingValue("power") * 2;
    }

    @Override
    public float getDamageForDisplay(final float finalPower) {
        return (2.0F + this.getSettingValue("power")) * finalPower;
    }

    @Override
    public String getResearch() {
        return "CR_FOCUS_POISON";
    }

    @Override
    public boolean execute(final RayTraceResult target, final Trajectory trajectory, final float finalPower, final int num) {
        PacketHandler.INSTANCE.sendToAllAround(new PacketFXFocusPartImpact(target.hitVec.x, target.hitVec.y, target.hitVec.z, new String[]{this.getKey()}), new NetworkRegistry.TargetPoint(this.getPackage().world.provider.getDimension(), target.hitVec.x, target.hitVec.y, target.hitVec.z, 64.0D));
        this.getPackage().world.playSound(null, target.hitVec.x, target.hitVec.y, target.hitVec.z, SoundsTC.bubble, SoundCategory.PLAYERS, 0.33F, 5.0F + (float) (this.getPackage().world.rand.nextGaussian() * 0.05F));

        if (target.typeOfHit == RayTraceResult.Type.ENTITY && target.entityHit != null) {
            final float damage = this.getDamageForDisplay(finalPower);
            final int duration = 40 * this.getSettingValue("duration");
            final int potency = (int) (1.0F + this.getSettingValue("power") * finalPower / 2.0F);
            target.entityHit.attackEntityFrom(DamageSource.causeThrownDamage((target.entityHit != null) ? target.entityHit : this.getPackage().getCaster(), this.getPackage().getCaster()), damage);

            if (target.entityHit instanceof EntityLivingBase) {
                ((EntityLivingBase) target.entityHit).addPotionEffect(new PotionEffect(MobEffects.POISON, duration, potency));
            }
        }

        return false;
    }

    @Override
    public void onCast(Entity caster) {
        caster.world.playSound(null, caster.getPosition().up(), SoundsTC.bubble, SoundCategory.PLAYERS, 0.33F, 0.9F + caster.world.rand.nextFloat() * 0.1F);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void renderParticleFX(World world, double posX, double posY, double posZ, double velX, double velY, double velZ) {
        final FXGeneric pp = new FXGeneric(world, posX, posY, posZ, velX, velY, velZ);
        int color = 9039872;
        
        pp.setAlphaF(0.7F);
        pp.setGravity(-0.2F);
        pp.setMaxAge(7 + world.rand.nextInt(5));
        pp.setParticles(575, 8, 8);
        pp.setRBGColorF(((color >> 16) & 0xFF) / 255.0F, ((color >> 8) & 0xFF) / 255.0F, (color & 0xFF) / 255.0F);
        pp.setSlowDown(0.75D);
        pp.setScale((float) (0.1F + world.rand.nextGaussian() * 0.2F), 2.0F);
        ParticleEngine.addEffect(world, pp);
    }
}