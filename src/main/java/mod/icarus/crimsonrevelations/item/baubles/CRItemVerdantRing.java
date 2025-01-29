package mod.icarus.crimsonrevelations.item.baubles;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.api.potions.PotionFluxTaint;

import javax.annotation.Nullable;
import java.util.List;


public class CRItemVerdantRing extends Item implements IBauble, IRechargable {
    public CRItemVerdantRing() {
        maxStackSize = 1;
        canRepair = false;
        setMaxDamage(0);
        addPropertyOverride(new ResourceLocation("type"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (stack.getItem() instanceof CRItemVerdantRing && stack.hasTagCompound()) {
                    return stack.getTagCompound().getByte("type");
                }
                return 0.0F;
            }
        });
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.RARE;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == NewCrimsonRevelations.tabCR || tab == CreativeTabs.SEARCH) {
            items.add(new ItemStack(this));
            ItemStack ring1 = new ItemStack(this);
            ring1.setTagInfo("type", new NBTTagByte((byte) 1));
            items.add(ring1);
            ItemStack ring2 = new ItemStack(this);
            ring2.setTagInfo("type", new NBTTagByte((byte) 2));
            items.add(ring2);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (stack.hasTagCompound() && stack.getTagCompound().getByte("type") == 1) {
            tooltip.add(TextFormatting.GOLD + new TextComponentTranslation("item.verdant_charm.life.text").getFormattedText());
        }
        if (stack.hasTagCompound() && stack.getTagCompound().getByte("type") == 2) {
            tooltip.add(TextFormatting.GOLD + new TextComponentTranslation("item.verdant_charm.sustain.text").getFormattedText());
        }
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        if (!player.world.isRemote && player.ticksExisted % 20 == 0 && player instanceof EntityPlayer) {
            if (player.getActivePotionEffect(MobEffects.WITHER) != null && RechargeHelper.consumeCharge(itemstack, player, 20)) {
                player.removePotionEffect(MobEffects.WITHER);
                return;
            }

            if (player.getActivePotionEffect(MobEffects.POISON) != null && RechargeHelper.consumeCharge(itemstack, player, 10)) {
                player.removePotionEffect(MobEffects.POISON);
                return;
            }

            if (player.getActivePotionEffect(PotionFluxTaint.instance) != null && RechargeHelper.consumeCharge(itemstack, player, 5)) {
                player.removePotionEffect(PotionFluxTaint.instance);
                return;
            }

            if (itemstack.hasTagCompound() && itemstack.getTagCompound().getByte("type") == 1 && player.getHealth() < player.getMaxHealth() && RechargeHelper.consumeCharge(itemstack, player, 5)) {
                player.heal(1.0F);
                return;
            }

            if (itemstack.hasTagCompound() && itemstack.getTagCompound().getByte("type") == 2) {

                if (player.getAir() < 100 && RechargeHelper.consumeCharge(itemstack, player, 1)) {
                    player.setAir(300);
                    return;
                }

                if (player instanceof EntityPlayer && ((EntityPlayer) player).canEat(false) && RechargeHelper.consumeCharge(itemstack, player, 1)) {
                    ((EntityPlayer) player).getFoodStats().addStats(1, 0.3F);
                }
            }
        }
    }

    @Override
    public int getMaxCharge(ItemStack stack, EntityLivingBase player) {
        return 200;
    }

    @Override
    public EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) {
        return EnumChargeDisplay.NORMAL;
    }

    @Override
    public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }
}