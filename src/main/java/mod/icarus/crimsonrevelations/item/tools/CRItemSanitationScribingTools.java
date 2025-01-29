package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.potions.PotionWarpWard;

import javax.annotation.Nullable;
import java.util.List;

public class CRItemSanitationScribingTools extends CRItem implements IScribeTools {
    public CRItemSanitationScribingTools() {
        super(EnumRarity.RARE);
        this.maxStackSize = 1;
        this.setMaxDamage(80);
        this.setHasSubtypes(false);
        this.addPropertyOverride(new ResourceLocation("depleted"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
                    return 1.0F;
                } else if (stack.getItemDamage() >= stack.getMaxDamage() && getDepletedState(stack)) {
                    return 2.0F;
                }

                return 0.0F;
            }
        });
    }

    public static boolean getDepletedState(ItemStack stack) {
        return stack.hasTagCompound() && stack.getTagCompound().getBoolean("depleted");
    }

    public static void setDepletedState(ItemStack stack, boolean flag) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        stack.getTagCompound().setBoolean("depleted", flag);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);

        if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
            if (!world.isRemote) {
                // Removes some normal warp.
                if (warp.get(EnumWarpType.NORMAL) > 10) {
                    int amount = 5 + world.rand.nextInt(5);
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -amount, EnumWarpType.NORMAL);
                } else if (warp.get(EnumWarpType.NORMAL) > 0) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -1, EnumWarpType.NORMAL);
                }

                // Removes all temporary warp.
                if (warp.get(EnumWarpType.TEMPORARY) > 0) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -warp.get(EnumWarpType.TEMPORARY), EnumWarpType.TEMPORARY);
                }

                if (FMLLaunchHandler.side().isClient()) {
                    for (int a = 0; a < 40; ++a) {
                        FXDispatcher.INSTANCE.crucibleBubble((float) player.posX - 0.5F + player.world.rand.nextFloat() * 1.5F, (float) player.getEntityBoundingBox().minY + player.world.rand.nextFloat() * player.height,
                                (float) player.posZ - 0.5F + world.rand.nextFloat() * 1.5F, 1.0F, 0.7F, 0.9F);
                    }
                }
            }

            player.swingArm(EnumHand.MAIN_HAND);
            player.playSound(SoundsTC.hhon, 0.8F, 0.6F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);

            // 1 hour of Warp Ward.
            player.addPotionEffect(new PotionEffect(PotionWarpWard.instance, 60 * 1200, 0, false, false));

            player.sendStatusMessage(new TextComponentTranslation("message.crimsonrevelations.scribing_tools.sanitation").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
            setDepletedState(stack, true);
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
            tooltip.add(new TextComponentTranslation("tooltip.crimsonrevelations.scribing_tools.active").getFormattedText());
        } else if (stack.getItemDamage() >= stack.getMaxDamage() && getDepletedState(stack)) {
            tooltip.add(TextFormatting.ITALIC + new TextComponentTranslation("tooltip.crimsonrevelations.scribing_tools.inactive").getFormattedText());
        }
    }
}
