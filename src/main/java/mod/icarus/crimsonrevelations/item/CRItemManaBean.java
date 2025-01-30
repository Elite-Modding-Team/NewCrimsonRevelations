package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.config.CRConfigLists;
import mod.icarus.crimsonrevelations.init.CRBlocks;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.tile.CRTileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategory;
import thecodex6824.thaumcraftfix.api.research.ResearchCategoryTheorycraftFilter;

import java.util.Random;

@SuppressWarnings("deprecation")
public class CRItemManaBean extends ItemFood implements IEssentiaContainerItem {
    static Aspect[] displayAspects = (Aspect[]) Aspect.aspects.values().toArray((Object[]) new Aspect[0]);
    public final int itemUseDuration;
    Random rand;

    public CRItemManaBean() {
        super(1, 0.5F, true);
        this.rand = new Random();
        this.itemUseDuration = 10;
        setHasSubtypes(true);
        setMaxDamage(0);
        setAlwaysEdible();
    }

    public static ItemStack makeManaBean(Aspect aspect, int stackSize) {
        if (aspect == null) {
            return null;
        } else {
            ItemStack stack = new ItemStack(CRItems.MANA_BEAN, stackSize, 0);
            ((IEssentiaContainerItem) CRItems.MANA_BEAN).setAspects(stack, (new AspectList()).add(aspect, CRConfig.general_settings.MANA_BEAN_ASPECT_COUNT));
            return stack;
        }
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack) {
        return this.itemUseDuration;
    }

    // Apply various random effects from a configurable list after eating
    @Override
    protected void onFoodEaten(ItemStack stack, World world, EntityPlayer player) {
        if (!world.isRemote) {
            Potion effect = CRConfigLists.manaBeanEffects.get(world.rand.nextInt(CRConfigLists.manaBeanEffects.size()));

            // Chance for an eaten bean to grant theories and observations for research
            if (world.rand.nextDouble() <= CRConfig.general_settings.MANA_BEAN_RESEARCH_CHANCE) {
                ResearchCategory[] rc = ResearchCategoryTheorycraftFilter.getAllowedTheorycraftCategories().toArray(new ResearchCategory[0]);
                int oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
                int tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();

                ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc[player.getRNG().nextInt(rc.length)], MathHelper.getInt(player.getRNG(), oProg / 2, oProg / 2));
                ThaumcraftApi.internalMethods.addKnowledge(player, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc[player.getRNG().nextInt(rc.length)], MathHelper.getInt(player.getRNG(), tProg / 2, tProg / 2));
            }

            if (effect != null) {
                if (effect.isInstant()) {
                    effect.affectEntity(player, player, player, 0, 3.0D);
                } else {
                    player.addPotionEffect(new PotionEffect(effect, (15 * 20) + (world.rand.nextInt(30) * 20), 0));
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == NewCrimsonRevelations.tabCR || tab == CreativeTabs.SEARCH) {

            for (Aspect tag : Aspect.aspects.values()) {
                ItemStack stack = new ItemStack(this);
                this.setAspects(stack, (new AspectList()).add(tag, CRConfig.general_settings.MANA_BEAN_ASPECT_COUNT));
                items.add(stack);
            }
        }

    }

    // Get aspect colors
    @SideOnly(Side.CLIENT)
    public int getColor(ItemStack stack, int par2) {
        if (getAspects(stack) != null) {
            return getAspects(stack).getAspects()[0].getColor();
        }

        int idx = (int) (System.currentTimeMillis() / 500L % displayAspects.length);
        return displayAspects[idx].getColor();
    }

    // Add aspect count to our beans (5 by default)
    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5) {
        if (!par2World.isRemote && !par1ItemStack.hasTagCompound()) {
            setAspects(par1ItemStack, (new AspectList()).add(displayAspects[this.rand.nextInt(displayAspects.length)], CRConfig.general_settings.MANA_BEAN_ASPECT_COUNT));
        }

        super.onUpdate(par1ItemStack, par2World, par3Entity, par4, par5);
    }

    @Override
    public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
        if (!par1ItemStack.hasTagCompound()) {
            setAspects(par1ItemStack, (new AspectList()).add(displayAspects[this.rand.nextInt(displayAspects.length)], CRConfig.general_settings.MANA_BEAN_ASPECT_COUNT));
        }
    }

    @Override
    public AspectList getAspects(ItemStack itemstack) {
        if (itemstack.hasTagCompound()) {
            AspectList aspects = new AspectList();
            aspects.readFromNBT(itemstack.getTagCompound());
            return (aspects.size() > 0) ? aspects : null;
        }

        return null;
    }

    @Override
    public void setAspects(ItemStack itemstack, AspectList aspects) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        }

        aspects.writeToNBT(itemstack.getTagCompound());
    }

    @Override
    public boolean ignoreContainedAspects() {
        return false;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!player.canPlayerEdit(pos, facing, player.getHeldItem(hand)) || facing.getIndex() != 0) {
            return EnumActionResult.FAIL;
        }

        Biome biome = world.getBiome(pos);
        boolean magicBiome = false;

        if (biome != null) {
            magicBiome = BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);
        }

        if (!magicBiome) {
            return EnumActionResult.FAIL;
        }

        Block i1 = world.getBlockState(pos).getBlock();

        if (i1 instanceof BlockLog || i1 == BlocksTC.logGreatwood || i1 == BlocksTC.logSilverwood) {
            BlockPos pos1 = new BlockPos(pos.getX(), pos.getY() - 1, pos.getZ());

            if (world.isAirBlock(pos1)) {
                IBlockState k1 = CRBlocks.MANA_POD.getStateForPlacement(world, pos1, facing, hitX, hitY, hitZ, 0, player);
                world.setBlockState(pos1, k1, 2);
                TileEntity tile = world.getTileEntity(pos1);

                if (tile != null && tile instanceof CRTileManaPod && getAspects(player.getHeldItem(hand)) != null && getAspects(player.getHeldItem(hand)).size() > 0) {
                    ((CRTileManaPod) tile).aspect = getAspects(player.getHeldItem(hand)).getAspects()[0];
                }

                if (!player.capabilities.isCreativeMode) {
                    player.getHeldItem(hand).setCount(player.getHeldItem(hand).getCount() - 1);
                }
            }

            return EnumActionResult.SUCCESS;
        }

        return EnumActionResult.SUCCESS;
    }
}
