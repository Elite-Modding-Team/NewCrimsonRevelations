package mod.icarus.crimsonrevelations.item.armor;

import java.util.List;

import javax.annotation.Nullable;

import mod.icarus.crimsonrevelations.item.IDyeableGear;
import net.minecraft.block.BlockCauldron;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class CRItemArmorDyeable extends ItemArmor implements IDyeableGear {
    public CRItemArmorDyeable(ArmorMaterial material, int renderIndex, EntityEquipmentSlot equipmentSlot) {
        super(material, renderIndex, equipmentSlot);
        this.addPropertyOverride(new ResourceLocation("dyed"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
                    return 1.0F;
                }

                return 0.0F;
            }
        });
    }

    @Override
    public int getDyedColor(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        if (!stack.getTagCompound().hasKey("color", NBT.TAG_INT)) {
            stack.getTagCompound().setInteger("color", getDefaultDyedColorForMeta(stack.getMetadata()));
        }

        return stack.getTagCompound().getInteger("color");
    }

    @Override
    public void setDyedColor(ItemStack stack, int color) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setInteger("color", color);
    }

    @Override
    public int getDefaultDyedColorForMeta(int meta) {
        return 8399402;
    }

    @Override
    public boolean hasColor(ItemStack stack) {
        return this.getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata()) ? true : false;
    }

    @Override
    public int getColor(ItemStack stack) {
        return getDyedColor(stack);
    }

    @Override
    public void removeColor(ItemStack stack) {
        setDyedColor(stack, getDefaultDyedColorForMeta(stack.getMetadata()));
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        setDyedColor(stack, color);
    }

    @Override
    public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        IBlockState state = world.getBlockState(pos);

        // Right-clicking a filled cauldron with the dyed item will wash it out
        if (state.getBlock() == Blocks.CAULDRON && state.getValue(BlockCauldron.LEVEL) > 0 && getDyedColor(stack) != getDefaultDyedColorForMeta(stack.getMetadata())) {
            setDyedColor(stack, getDefaultDyedColorForMeta(stack.getMetadata()));
            world.setBlockState(pos, state.withProperty(BlockCauldron.LEVEL, state.getValue(BlockCauldron.LEVEL) - 1));
            world.playSound(null, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 0.5F, 1.0F);
            return EnumActionResult.SUCCESS;
        }

        return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> list, ITooltipFlag tooltip) {
        int color = getDyedColor(stack);

        // If it's dyed, show it on the tooltip
        if (color != getDefaultDyedColorForMeta(stack.getMetadata())) {
            if (tooltip.isAdvanced())
                list.add(new TextComponentTranslation("item.color", TextFormatting.GRAY + String.format("#%06X", color)).getFormattedText());
            else {
                list.add(TextFormatting.ITALIC + new TextComponentTranslation("item.dyed").getFormattedText());
            }
        }
    }
}
