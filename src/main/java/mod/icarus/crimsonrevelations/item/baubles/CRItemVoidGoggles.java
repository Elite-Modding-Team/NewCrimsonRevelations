package mod.icarus.crimsonrevelations.item.baubles;

import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.render.IRenderBauble;
import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import thaumcraft.api.ThaumcraftMaterials;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.items.IGoggles;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.IWarpingGear;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.misc.PacketAuraToClient;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.world.aura.AuraChunk;
import thaumcraft.common.world.aura.AuraHandler;

// TODO: WIP, will have more functions in the future.
public class CRItemVoidGoggles extends ItemArmor implements IVisDiscountGear, IGoggles, IBauble, IRenderBauble, IWarpingGear {
    ResourceLocation TEXTURE_PATH_BAUBLE = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/items/void_goggles_bauble.png");
    ResourceLocation TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/void_goggles.png");

    public CRItemVoidGoggles() {
        super(ThaumcraftMaterials.ARMORMAT_SPECIAL, 4, EntityEquipmentSlot.HEAD);
        this.setMaxDamage(350);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return TEXTURE_PATH.toString();
    }

    @Override
    public boolean showIngamePopups(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return true;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemStack) {
        return BaubleType.HEAD;
    }

    @Override
    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.EPIC;
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);

        if (stack.isItemDamaged() && entity != null && entity.ticksExisted % 20 == 0) {
            stack.attemptDamageItem(-1, world.rand, entity instanceof EntityPlayerMP ? (EntityPlayerMP) entity : null);
        }

        if (this.isWorn(entity) && !world.isRemote && entity.ticksExisted % 20 == 0 && entity instanceof EntityPlayerMP) {
            this.updateAura(world, (EntityPlayerMP) entity);
        }
    }

    @Override
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (player != null) {
            if (!player.world.isRemote && player.ticksExisted % 20 == 0 && player instanceof EntityPlayerMP) {
                this.updateAura(player.world, (EntityPlayerMP) player);
            }
        }
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        super.onArmorTick(world, player, stack);

        if (!world.isRemote && player.ticksExisted % 20 == 0) {
            if (stack.getItemDamage() > 0) {
                stack.attemptDamageItem(-1, player.getRNG(), player instanceof EntityPlayerMP ? (EntityPlayerMP) player : null);
            }
        }
    }

    @Override
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return repair.isItemEqual(new ItemStack(ItemsTC.ingots, 1, 1)) || super.getIsRepairable(toRepair, repair);
    }

    public boolean isWorn(Entity player) {
        if (player instanceof EntityPlayerMP) {
            IItemHandler baubles = BaublesApi.getBaublesHandler((EntityPlayerMP) player);
            ItemStack headSlot = baubles.getStackInSlot(4);

            return (((EntityPlayerMP) player).getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof CRItemVoidGoggles || headSlot.getItem() instanceof CRItemVoidGoggles);
        }

        return false;
    }

    private void updateAura(World world, EntityPlayerMP player) {
        AuraChunk chunk = AuraHandler.getAuraChunk(world.provider.getDimension(), player.getPosition().getX() >> 4, player.getPosition().getZ() >> 4);

        if (chunk != null) {
            if ((chunk.getFlux() > chunk.getVis() || chunk.getFlux() > (float) (chunk.getBase() / 3)) && !ThaumcraftCapabilities.knowsResearch(player, "FLUX")) {
                ResearchManager.startResearchWithPopup(player, "FLUX");
                player.sendStatusMessage(new TextComponentTranslation("research.FLUX.warn").setStyle(new Style().setColor(TextFormatting.DARK_PURPLE)), true);
            }

            PacketHandler.INSTANCE.sendTo(new PacketAuraToClient(chunk), player);
        }
    }

    @Override
    public void onPlayerBaubleRender(ItemStack itemStack, EntityPlayer entityPlayer, RenderType renderType, float ticks) {
        if (renderType == RenderType.HEAD) {
            boolean armor = !entityPlayer.getItemStackFromSlot(EntityEquipmentSlot.HEAD).isEmpty();
			
            Minecraft.getMinecraft().renderEngine.bindTexture(this.TEXTURE_PATH_BAUBLE);
            Helper.translateToHeadLevel(entityPlayer);
            Helper.translateToFace();
            Helper.defaultTransforms();
            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.translate(-0.5D, -0.5D, armor ? 0.12D : 0.0D);
            UtilsFX.renderTextureIn3D(0.0F, 0.0F, 1.0F, 1.0F, 16, 26, 0.1F);
        }
    }

    @Override
    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 10;
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer player) {
        return 2;
    }
}
