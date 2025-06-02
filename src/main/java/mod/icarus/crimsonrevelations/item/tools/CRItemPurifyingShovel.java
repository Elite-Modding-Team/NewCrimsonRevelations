package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.item.CRItemShovel;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.fx.PacketFXZap;

public class CRItemPurifyingShovel extends CRItemShovel {
    public CRItemPurifyingShovel() {
        super(ThaumcraftMaterials.TOOLMAT_ELEMENTAL, EnumRarity.RARE);
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state) {
        if (state.getMaterial() == ThaumcraftMaterials.MATERIAL_TAINT) {
            return efficiency * 1.5F;
        }

        return super.getDestroySpeed(stack, state);
    }

    @Override
    public boolean getIsRepairable(ItemStack stack1, ItemStack stack2) {
        return stack2.isItemEqual(new ItemStack(ItemsTC.ingots, 1, 0)) || super.getIsRepairable(stack1, stack2);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        int purified = 0;

        for (int ex = pos.getX() - 5; ex < pos.getX() + 6; ex++) {
            for (int wy = pos.getY() - 4; wy < pos.getY() + 5; wy++) {
                for (int zee = pos.getZ() - 5; zee < pos.getZ() + 6; zee++) {
                    BlockPos aim = new BlockPos(ex, wy, zee);
                    Block target = world.getBlockState(aim).getBlock();

                    // Cleanse nearby flux goo
                    if (target != null && target == BlocksTC.fluxGoo) {
                        purified++;
                        world.setBlockToAir(new BlockPos(ex, wy, zee));
                        float d1 = ((float) ex + world.rand.nextFloat());
                        float d2 = ((float) wy + world.rand.nextFloat());
                        float d0 = ((float) zee + world.rand.nextFloat());
                        //Thaumcraft.proxy.nodeBolt(world, (float)player.posX, (float)player.posY, (float)player.posZ, d1, d2, d0);
                        PacketHandler.INSTANCE.sendToAllAround(new PacketFXZap(player.getPositionVector(), new Vec3d(d1, d2, d0), 0xFFC0FF, 0.5F * 0.66F), new NetworkRegistry.TargetPoint(world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 64.0D));
                    }
                }
            }
        }

        if (purified > 0) {
            player.getHeldItem(hand).damageItem(Math.min(purified, 15), player);
            player.swingArm(hand);
            world.playSound(player, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundsTC.zap, SoundCategory.PLAYERS, 1.0F, 1.0F);
            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.PASS;
    }
}
