package mod.icarus.crimsonrevelations.block;

import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import mod.icarus.crimsonrevelations.tile.CRTileManaPod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.IGrowable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.blocks.BlocksTC;
import thaumcraft.api.internal.WorldCoordinates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

@SuppressWarnings("deprecation")
public class CRBlockManaPod extends Block implements IGrowable {
    public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 8);
    public static float W1 = 0.0625F;
    public static float W2 = 0.125F;
    public static float W3 = 0.1875F;
    public static float W4 = 0.25F;
    public static float W5 = 0.3125F;
    public static float W6 = 0.375F;
    public static float W7 = 0.4375F;
    public static float W8 = 0.5F;
    public static float W9 = 0.5625F;
    public static float W10 = 0.625F;
    public static float W11 = 0.6875F;
    public static float W12 = 0.75F;
    public static float W13 = 0.8125F;
    public static float W14 = 0.875F;
    public static float W15 = 0.9375F;
    static HashMap<WorldCoordinates, Aspect> st = new HashMap<>();

    public CRBlockManaPod() {
        super(Material.PLANTS);
        this.setDefaultState(this.blockState.getBaseState().withProperty(this.getAgeProperty(), 0));
        setTickRandomly(true);
        this.setHardness(0.5F);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, AGE);
    }

    protected int getAge(IBlockState state) {
        return state.getValue(this.getAgeProperty());
    }

    public IBlockState withAge(int age) {
        return this.getDefaultState().withProperty(this.getAgeProperty(), age);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.withAge(meta);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return this.getAge(state);
    }

    protected PropertyInteger getAgeProperty() {
        return AGE;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        getBoundingBox(state, worldIn, pos);
        return super.getCollisionBoundingBox(state, worldIn, pos);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        int l = getMetaFromState(state);

        switch (l) {
            case 0:
                return new AxisAlignedBB(0.25F, W12, 0.25F, 0.75F, 1.0F, 0.75F);
            case 1:
                return new AxisAlignedBB(0.25F, W10, 0.25F, 0.75F, 1.0F, 0.75F);
            case 2:
                return new AxisAlignedBB(0.25F, W8, 0.25F, 0.75F, 1.0F, 0.75F);
            case 3:
                return new AxisAlignedBB(0.25F, W6, 0.25F, 0.75F, 1.0F, 0.75F);
            case 4:
                return new AxisAlignedBB(0.25F, W5, 0.25F, 0.75F, 1.0F, 0.75F);
            case 5:
                return new AxisAlignedBB(0.25F, W4, 0.25F, 0.75F, 1.0F, 0.75F);
            case 6:
                return new AxisAlignedBB(0.25F, W3, 0.25F, 0.75F, 1.0F, 0.75F);
            case 7:
                return new AxisAlignedBB(0.25F, W2, 0.25F, 0.75F, 1.0F, 0.75F);
        }

        return new AxisAlignedBB(0.25F, W2, 0.25F, 0.75F, 1.0F, 0.75F);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public AxisAlignedBB getSelectedBoundingBox(IBlockState state, World worldIn, BlockPos pos) {
        getBoundingBox(state, worldIn, pos);
        return super.getSelectedBoundingBox(state, worldIn, pos);
    }

    @Override
    public void updateTick(World world, BlockPos pos, IBlockState state, Random rand) {
        if (!canBlockStay(world, pos, state)) {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        } else if (world.rand.nextInt(30) == 0) {
            TileEntity tile = world.getTileEntity(pos);

            if (tile instanceof CRTileManaPod) {
                ((CRTileManaPod) tile).checkGrowth();
            }

            st.remove(new WorldCoordinates(pos, world.provider.getDimension()));
        }
    }

    public boolean canBlockStay(World world, BlockPos pos, IBlockState state) {
        Biome biome = world.getBiome(pos);
        boolean magicBiome = false;

        if (biome != null) {
            magicBiome = BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);
        }

        Block i1 = world.getBlockState(pos.up()).getBlock();

        return (magicBiome && (i1 instanceof BlockLog || i1 == BlocksTC.logGreatwood || i1 == BlocksTC.logSilverwood));
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing facing) {
        Biome biome = world.getBiome(pos);
        boolean magicBiome = biome != null && BiomeDictionary.hasType(biome, BiomeDictionary.Type.MAGICAL);
        Block blockAbove = world.getBlockState(pos.up()).getBlock();
        boolean isLog = blockAbove instanceof BlockLog || blockAbove == BlocksTC.logGreatwood || blockAbove == BlocksTC.logSilverwood;
        return facing == EnumFacing.DOWN && isLog && magicBiome;
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return getMetaFromState(state);
    }

    @Override
    public void neighborChanged(IBlockState state, World par1World, BlockPos pos, Block blockIn, BlockPos fromPos) {
        if (!canBlockStay(par1World, pos, state)) {
            dropBlockAsItem(par1World, pos, state, 0);
            par1World.setBlockToAir(pos);
        }
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tile = world.getTileEntity(pos);

        if (tile instanceof CRTileManaPod && ((CRTileManaPod) tile).aspect != null) {
            st.put(new WorldCoordinates(pos, world.provider.getDimension()), ((CRTileManaPod) tile).aspect);
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public ArrayList<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> dropped = new ArrayList<>();
        int metadata = getMetaFromState(state);

        if (metadata < 2) {
            return dropped;
        }

        byte b0 = 1;

        if (metadata >= 7 && ((World) world).rand.nextFloat() > 0.33F) {
            b0 = 2;
        }

        Aspect aspect = Aspect.PLANT;

        if (st.containsKey(new WorldCoordinates(pos, ((World) world).provider.getDimension()))) {
            aspect = st.get(new WorldCoordinates(pos, ((World) world).provider.getDimension()));
        } else {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof CRTileManaPod && ((CRTileManaPod) tile).aspect != null)
                aspect = ((CRTileManaPod) tile).aspect;
        }

        for (int k1 = 0; k1 < b0; k1++) {
            ItemStack i = new ItemStack(CRItems.MANA_BEAN);
            ((CRItemManaBean) i.getItem()).setAspects(i, (new AspectList()).add(aspect, CRConfig.general_settings.MANA_BEAN_ASPECT_COUNT));
            dropped.add(i);
        }

        st.remove(new WorldCoordinates(pos, ((World) world).provider.getDimension()));
        return dropped;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return CRItems.MANA_BEAN.getDefaultInstance();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemById(0);
    }

    @Override
    public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new CRTileManaPod();
    }

    @Override
    public boolean canGrow(World world, BlockPos blockPos, IBlockState iBlockState, boolean b) {
        return iBlockState.getValue(AGE) < 8;
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, IBlockState iBlockState) {
        return false;
    }

    @Override
    public void grow(World world, Random random, BlockPos pos, IBlockState state) {
        world.setBlockState(pos, state.withProperty(AGE, state.getValue(AGE) + 1), 8);
    }
}
