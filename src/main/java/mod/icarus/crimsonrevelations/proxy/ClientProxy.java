package mod.icarus.crimsonrevelations.proxy;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.client.fx.FXDispatcher;

import java.util.Random;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void spawnPortalParticle(World world, Random rand, Entity cultist) {
        for (int i = 0; i < 20; ++i) {
            double d0 = rand.nextGaussian() * 0.05;
            double d2 = rand.nextGaussian() * 0.05;
            double d3 = rand.nextGaussian() * 0.05;
            double d4 = 2.0;

            FXDispatcher.GenPart pp = new FXDispatcher.GenPart();
            pp.age = 10 + world.rand.nextInt(10);
            pp.alpha = new float[]{0.8F, 0.8F};
            pp.grid = 32;
            pp.layer = 1;
            pp.partInc = 1;
            pp.partNum = 5;
            pp.partStart = 337;
            float s = (float) (3.0F + world.rand.nextGaussian() * 2.0F);
            pp.scale = new float[]{s, s};
            pp.redEnd = 0.6F;
            pp.greenEnd = 0.0F;
            pp.blueEnd = 0.0F;

            FXDispatcher.INSTANCE.drawGenericParticles(cultist.posX + rand.nextFloat() * cultist.width * 2.0f - cultist.width + d0 * d4,
                    cultist.posY + rand.nextFloat() * cultist.height + d2 * d4,
                    cultist.posZ + rand.nextFloat() * cultist.width * 2.0f - cultist.width + d3 * d4, d0, d2, d3, pp);
        }
    }
}
