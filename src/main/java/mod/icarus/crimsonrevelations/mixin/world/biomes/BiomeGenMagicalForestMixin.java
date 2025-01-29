package mod.icarus.crimsonrevelations.mixin.world.biomes;

import mod.icarus.crimsonrevelations.world.WorldGenManaPods;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.common.world.biomes.BiomeGenMagicalForest;

import java.util.Random;

@Mixin(BiomeGenMagicalForest.class)
public abstract class BiomeGenMagicalForestMixin {
    @Inject(method = "decorate", at = @At(value = "TAIL"))
    public void crDecorate(World world, Random rand, BlockPos pos, CallbackInfo ci) {
        WorldGenManaPods worldGenManaPods = new WorldGenManaPods();

        for (int k = 0; k < 10; k++) {
            int l = pos.getX() + rand.nextInt(16) + 8;
            byte b0 = 64;
            int i1 = pos.getZ() + rand.nextInt(16) + 8;
            worldGenManaPods.generate(world, rand, new BlockPos(l, b0, i1));
        }
    }
}
