package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.items.IScribeTools;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.common.lib.SoundsTC;

public class ItemKnowledgeScribingTools extends CRItem implements IScribeTools {
    public ItemKnowledgeScribingTools() {
        super(EnumRarity.RARE);
        this.maxStackSize = 1;
        this.setMaxDamage(50);
        this.setHasSubtypes(false);
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
        ResearchCategory[] rc = ResearchCategories.researchCategories.values().toArray(new ResearchCategory[0]);
        int oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
        int tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();

        if (stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack)) {
            if (!world.isRemote) {
                ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc[player.getRNG().nextInt(rc.length)], MathHelper.getInt(player.getRNG(), oProg / 2, oProg));
                ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc[player.getRNG().nextInt(rc.length)], MathHelper.getInt(player.getRNG(), tProg / 3, tProg / 2));
            }

            player.swingArm(EnumHand.MAIN_HAND);
            player.playSound(SoundsTC.scan, 0.8F, 0.8F + (float) player.getEntityWorld().rand.nextGaussian() * 0.05F);
            player.sendStatusMessage(new TextComponentTranslation("message.crimsonrevelations.knowledge_scribing_tools.deplete").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
            setDepletedState(stack, true);
            return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
        } else {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return stack.getItemDamage() >= stack.getMaxDamage() && !getDepletedState(stack);
    }
}
