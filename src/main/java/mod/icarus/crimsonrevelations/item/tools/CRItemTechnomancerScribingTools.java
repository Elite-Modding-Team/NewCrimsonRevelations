package mod.icarus.crimsonrevelations.item.tools;

import javax.annotation.Nullable;

import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.api.items.RechargeHelper;

// As Scribing Tools mainly check for durability, we'll need to have it be affected by vis charge.
public class CRItemTechnomancerScribingTools extends CRItem implements IScribeTools, IRechargable {
    public CRItemTechnomancerScribingTools() {
        super(EnumRarity.RARE);
        this.maxStackSize = 1;
        this.setMaxDamage(400);
        this.setHasSubtypes(false);
        this.addPropertyOverride(new ResourceLocation("depleted"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (RechargeHelper.getCharge(stack) > 0) {
                    return 1.0F;
                } else if (RechargeHelper.getCharge(stack) <= 0) {
                    return 2.0F;
                }

                return 0.0F;
            }
        });
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            ItemStack base = new ItemStack(this);
            base.setItemDamage(base.getMaxDamage());
            items.add(base);
            ItemStack charged = base.copy();
            charged.setItemDamage(0);
            items.add(charged);
        }
    }

    @Override
    public void setDamage(ItemStack stack, int meta) {
        super.setDamage(stack, meta);

        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setInteger("tc.charge", (stack.getMaxDamage() - meta));
    }

    @Override
    public int getDamage(ItemStack stack) {
        stack.setItemDamage(stack.getMaxDamage() - RechargeHelper.getCharge(stack));
        return super.getDamage(stack);
    }

    @Override
    public int getMaxCharge(ItemStack stack, EntityLivingBase entity) {
        stack.setItemDamage(stack.getMaxDamage() - RechargeHelper.getCharge(stack));
        return getMaxDamage(stack);
    }

    @Override
    public EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase entity) {
        return EnumChargeDisplay.NORMAL;
    }

    @Override
    public int getRGBDurabilityForDisplay(ItemStack stack) {
        return 0x39BDFF;
    }
}
