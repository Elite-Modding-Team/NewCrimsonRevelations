package mod.icarus.crimsonrevelations.tile;

import mod.icarus.crimsonrevelations.client.CRPacketHandler;
import mod.icarus.crimsonrevelations.client.fx.CRPacketFXArcBolt;
import mod.icarus.crimsonrevelations.init.CRSoundEvents;
import mod.icarus.crimsonrevelations.util.TCVec3;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ITickable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.common.blocks.world.taint.ITaintBlock;
import thaumcraft.common.lib.SoundsTC;

import java.util.List;

public class CRTileEtherealBloom extends TileEntity implements ITickable {
    public int counter = 0;
    public int rad;
    public int rad1 = 0;
    public int growthCounter = 100;
    public int foundTaint = 100;
    public static final int bloomsleep = 300;
    public int sleepcounter = 300;
    public boolean sleep = false;

    public void resetSleep() {
        this.foundTaint = 100;
        this.sleepcounter = 300;
        this.sleep = false;
    }

    @Override
    public void update() {
        if (this.counter == 0) {
            this.rad = this.counter = this.world.rand.nextInt(100);
        }

        ++this.counter;

        if (this.foundTaint == 0) {
            --this.sleepcounter;

            if (!this.sleep) {
                this.counter = 0;
                this.sleepcounter = 300;
            }
        }

        /*if (!this.world.isRemote && this.counter % 20 == 0 && !this.sleep) {
            List<EntityLivingBase> entities = this.world.getEntitiesWithinAABB(EntityLivingBase.class, new AxisAlignedBB(pos.getX() - 16, pos.getY() - 16, pos.getZ() - 16, pos.getX() + 16, pos.getY() + 16, pos.getZ() + 16));

            for (EntityLivingBase victim : entities) {
                if (victim instanceof ITaintedMob) {
                    if (!(victim instanceof EntityPlayer)) {
                        boolean attack = victim.attackEntityFrom(DamageSource.MAGIC, (float) 2.0F);

                        if (attack) {
                            this.world.playSound(null, pos, SoundsTC.zap, SoundCategory.BLOCKS, 1.0F, 1.1F);
                            CRPacketHandler.INSTANCE.sendToAllAround(new CRPacketFXArcBolt(new Vec3d(this.pos.add(0.0D, 1.0D, 0.0D)), new Vec3d(victim.getPosition()), 0x23333, 1), new NetworkRegistry.TargetPoint(this.world.provider.getDimension(), victim.posX, victim.posY, victim.posZ, 64.0D));
                        }
                    }
                }
            }
        }*/

        if (!this.world.isRemote && this.counter % 20 == 0 && !this.sleep) {
            this.rad = (int) ((double) this.rad + (5.0D + Math.sqrt(1 + this.rad1) * 5.0D + (double) this.world.rand.nextInt(5)));

            if (this.rad > 360) {
                this.rad -= 360;
                this.rad1 += 5 + this.world.rand.nextInt(5);

                if (this.rad1 > 87) {
                    this.rad1 -= 87;
                }
            }

            boolean foundsomething = false;
            TCVec3 vsource = TCVec3.createVectorHelper((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D);

            for (int q = 1; q < 8; ++q) {
                TCVec3 vtar = TCVec3.createVectorHelper(q, 0.0D, 0.0D);
                vtar.rotateAroundZ((float) this.rad1 / 180.0F * (float) Math.PI);
                vtar.rotateAroundY((float) this.rad / 180.0F * (float) Math.PI);
                TCVec3 vres1 = vsource.addVector(vtar.xCoord, vtar.yCoord, vtar.zCoord);
                TCVec3 vres2 = vsource.addVector(-vtar.xCoord, -vtar.yCoord, -vtar.zCoord);
                BlockPos t1 = new BlockPos(MathHelper.floor(vres1.xCoord), MathHelper.floor(vres1.yCoord), MathHelper.floor(vres1.zCoord));

                while (this.world.isAirBlock(t1) && t1.getY() > 0) {
                    t1 = t1.down();
                }

                BlockPos t2 = new BlockPos(MathHelper.floor(vres2.xCoord), MathHelper.floor(vres2.yCoord), MathHelper.floor(vres2.zCoord));

                while (this.world.isAirBlock(t2) && t2.getY() > 0) {
                    t2 = t2.down();
                }

                if (this.clearBlock(t1)) {
                    foundsomething = true;
                }

                if (this.clearBlock(t2)) {
                    foundsomething = true;
                }

                if (!foundsomething) continue;

                this.resetSleep();
                break;
            }

            if (this.foundTaint > 0 && !foundsomething) {
                --this.foundTaint;
            }
        }

        if (this.world.isRemote && this.growthCounter == 0) {
            this.world.playSound((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D, (double) this.pos.getZ() + 0.5D, CRSoundEvents.MISC_ROOTS, SoundCategory.BLOCKS, 1.0F, 0.6F, false);
        }

        ++this.growthCounter;
    }

    private boolean clearBlock(BlockPos p) {
        boolean bc = false;
        Block bt = this.world.getBlockState(p).getBlock();

        if (bt instanceof ITaintBlock) {
            ((ITaintBlock) bt).die(this.world, p, this.world.getBlockState(p));
            bc = true;
        }

        return bc;
    }
}