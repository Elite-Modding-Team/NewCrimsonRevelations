package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.init.CRSoundEvents;
import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.capabilities.IPlayerWarp.EnumWarpType;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.potions.PotionWarpWard;
import thecodex6824.thaumcraftfix.api.research.ResearchCategoryTheorycraftFilter;

import javax.annotation.Nullable;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CRItemPrimordialScribingTools extends CRItem implements IScribeTools {
    public CRItemPrimordialScribingTools() {
        super(EnumRarity.EPIC);
        this.maxStackSize = 1;
        this.setMaxDamage(200);
        this.setHasSubtypes(false);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        ItemStack stack = player.getHeldItem(hand);
        ResearchCategory[] categories = this.getResearchCategories();
        int observationProgress = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
        int theoryProgress = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();
        IPlayerWarp warp = ThaumcraftCapabilities.getWarp(player);

        if (stack.getItemDamage() >= stack.getMaxDamage()) {
            if (!world.isRemote) {
                if (categories.length > 0) {
                    ThaumcraftApi.internalMethods.addKnowledge(
                            player,
                            IPlayerKnowledge.EnumKnowledgeType.OBSERVATION,
                            categories[player.getRNG().nextInt(categories.length)],
                            MathHelper.getInt(player.getRNG(), observationProgress / 2, observationProgress));

                    ThaumcraftApi.internalMethods.addKnowledge(
                            player,
                            IPlayerKnowledge.EnumKnowledgeType.THEORY,
                            categories[player.getRNG().nextInt(categories.length)],
                            MathHelper.getInt(player.getRNG(), theoryProgress / 2, theoryProgress));
                }

                if (world.rand.nextDouble() <= CRConfig.general_settings.PRIMORDIAL_TOOLS_CURIOSITY_CHANCE) {
                    // Eldritch or Twisted Curiosity
                    EntityItem curio = new EntityItem(world, player.posX, player.posY, player.posZ, world.rand.nextBoolean() ? new ItemStack(ItemsTC.curio, 1, 3) : new ItemStack(ItemsTC.curio, 1, 5));
                    world.spawnEntity(curio);
                }

                if (FMLLaunchHandler.side().isClient()) {
                    for (int a = 0; a < 40; ++a) {
                        FXDispatcher.INSTANCE.drawNitorFlames((float) player.posX - 1.0F + player.world.rand.nextFloat() * 1.5F, (float) player.getEntityBoundingBox().minY + player.world.rand.nextFloat() * player.height,
                                (float) player.posZ - 1.0F + world.rand.nextFloat() * 1.5F, world.rand.nextGaussian() * 0.0025, world.rand.nextFloat() * 0.06, world.rand.nextGaussian() * 0.0025, 8022271, 0);
                    }
                }

                // Removes some normal warp.
                if (warp.get(EnumWarpType.NORMAL) > 10) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -10, EnumWarpType.NORMAL);
                } else if (warp.get(EnumWarpType.NORMAL) > 0) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -1, EnumWarpType.NORMAL);
                }

                // Removes all temporary warp.
                if (warp.get(EnumWarpType.TEMPORARY) > 0) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(player, -warp.get(EnumWarpType.TEMPORARY), EnumWarpType.TEMPORARY);
                }
            }

            player.swingArm(EnumHand.MAIN_HAND);
            player.playSound(CRSoundEvents.RUNIC_BAUBLE_REGEN, 0.8F, 0.8F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);

            // 1 hour of Warp Ward.
            player.addPotionEffect(new PotionEffect(PotionWarpWard.instance, 60 * 1200, 0, false, false));

            player.sendStatusMessage(new TextComponentTranslation("message.crimsonrevelations.scribing_tools.primordial").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
            this.setDamage(stack, -this.getMaxDamage(stack)); // Won't restore on Creative mode but I guess that's normal behavior...?
            return new ActionResult<>(EnumActionResult.SUCCESS, stack);
        } else {
            return new ActionResult<>(EnumActionResult.FAIL, stack);
        }
    }

    // Use Thaumcraft Fix's filter if it's also installed.
    private ResearchCategory[] getResearchCategories() {
        if (Loader.isModLoaded("thaumcraftfix")) {
            return ResearchCategoryTheorycraftFilter.getAllowedTheorycraftCategories().toArray(new ResearchCategory[0]);
        } else {
            Set<ResearchCategory> categories = new HashSet<>();
            categories.add(ResearchCategories.getResearchCategory("ALCHEMY"));
            categories.add(ResearchCategories.getResearchCategory("ARTIFICE"));
            categories.add(ResearchCategories.getResearchCategory("AUROMANCY"));
            categories.add(ResearchCategories.getResearchCategory("BASICS"));
            categories.add(ResearchCategories.getResearchCategory("GOLEMANCY"));
            categories.add(ResearchCategories.getResearchCategory("INFUSION"));
            categories.add(ResearchCategories.getResearchCategory("ELDRITCH"));
            return categories.toArray(new ResearchCategory[0]);
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean hasEffect(ItemStack stack) {
        return stack.getItemDamage() >= stack.getMaxDamage();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<String> tooltip, ITooltipFlag flag) {
        if (stack.getItemDamage() >= stack.getMaxDamage()) {
            tooltip.add(new TextComponentTranslation("tooltip.crimsonrevelations.scribing_tools.active").getFormattedText());
        }
    }
}
