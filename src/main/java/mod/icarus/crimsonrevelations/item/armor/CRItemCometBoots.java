package mod.icarus.crimsonrevelations.item.armor;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.config.CRConfig;
import mod.icarus.crimsonrevelations.init.CRMaterials;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.EnchantmentFrostWalker;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.common.ISpecialArmor;
import net.minecraftforge.common.util.Constants.NBT;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.RechargeHelper;
import thecodex6824.thaumicaugmentation.api.entity.PlayerMovementAbilityManager;
import thecodex6824.thaumicaugmentation.api.entity.PlayerMovementAbilityManager.MovementType;
import thecodex6824.thaumicaugmentation.api.item.IArmorReduceFallDamage;

public class CRItemCometBoots extends ItemArmor implements ISpecialArmor, IRechargable, IVisDiscountGear, IArmorReduceFallDamage {
    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/comet_boots.png").toString();

    // Calculate attribute bonuses.    
    protected static final BiFunction<EntityPlayer, MovementType, Float> MOVEMENT_FUNC = (player, type) -> {
        float boost = 0;
        switch (type) {
            case DRY_GROUND:
                boost = (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_LAND_SPEED;
                return player.isSneaking() ? boost / 4.0F : boost;
            case WATER_GROUND:
                boost = (float) Math.max((float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_LAND_SPEED / (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_SNEAK_REDUCTION,
                        (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_WATER_SPEED);
                return player.isSneaking() ? boost / (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_SNEAK_REDUCTION : boost;
            case WATER_SWIM:
                boost = (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_WATER_SPEED;
                return player.isSneaking() ? boost / (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_SNEAK_REDUCTION : boost;
            case JUMP_BEGIN:
                return (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_JUMP_BOOST;
            case JUMP_FACTOR:
                return (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_JUMP_FACTOR;
            case STEP_HEIGHT:
                return !player.isSneaking() ? (float) CRConfig.thaumic_augmentation_settings.COMET_BOOTS_STEP_HEIGHT : 0.0F;
            default:
                return boost;
        }
    };

    protected static final Predicate<EntityPlayer> CONTINUE_FUNC = player ->
            player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof CRItemCometBoots;

    public CRItemCometBoots() {
        super(CRMaterials.BOOTS_COMET, 4, EntityEquipmentSlot.FEET);
    }

    @Override
    public void damageArmor(EntityLivingBase entity, ItemStack stack, DamageSource source, int damage, int slot) {
        if (source != DamageSource.FALL || source != DamageSource.HOT_FLOOR || source != DamageSource.IN_FIRE
                || source != DamageSource.ON_FIRE) {
            stack.damageItem(damage, entity);
        }
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player) {
        return 3;
    }

    @Override
    public int getArmorDisplay(EntityPlayer player, ItemStack armor, int slot) {
        return 0;
    }

    @Override
    public ArmorProperties getProperties(EntityLivingBase player, ItemStack armor, DamageSource source, double damage, int slot) {
        return new ArmorProperties(0, 0.0, armor.getMaxDamage() - armor.getItemDamage());
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return TEXTURE_PATH;
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (tab == NewCrimsonRevelations.tabCR || tab == CreativeTabs.SEARCH) {
            ItemStack base = new ItemStack(this, 1, 0);
            items.add(base);
            ItemStack charged = base.copy();
            RechargeHelper.rechargeItemBlindly(charged, null, getMaxCharge(charged, null));
            items.add(charged);
        }
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
        ItemStack boots = player.getItemStackFromSlot(EntityEquipmentSlot.FEET);
        double motion = Math.abs(player.motionX) + Math.abs(player.motionZ) + Math.abs(player.motionY);

        // Built-in Frost Walker effect, Frost Walker enchantment will improve the effect
        if (RechargeHelper.getCharge(stack) > 0) {
            if (!player.world.isRemote) {
                boolean isLastOnGround = player.onGround;

                player.onGround = true;
                EnchantmentFrostWalker.freezeNearby(player, player.world, new BlockPos(player), 3 + EnchantmentHelper.getEnchantmentLevel(Enchantments.FROST_WALKER, boots));
                player.onGround = isLastOnGround;
            }
        }

        // Particles when sprinting or jumping
        if (!world.isRemote) {
            if (!player.isInWater() && (motion > 0.1F || player.isSprinting())) {
                ((WorldServer) world).spawnParticle(EnumParticleTypes.END_ROD, (double) (player.posX + Math.random() - 0.5F),
                        (double) (player.getEntityBoundingBox().minY + 0.25F + ((Math.random() - 0.5) * 0.25F)), (double) (player.posZ + Math.random() - 0.5F), 1, 0.0D, 0.025D, 0.0D, 0.0D);
            }
        }

        // Speed and jump height boost.
        if (!world.isRemote && player.ticksExisted % 20 == 0) {
            int current = 0;
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("energyRemaining", NBT.TAG_INT)) {
                current = stack.getTagCompound().getInteger("energyRemaining");
            } else if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }

            if (current > 0) {
                --current;
            }

            if (current <= 0 && RechargeHelper.consumeCharge(stack, player, 2)) {
                current = 60;
            }

            stack.getTagCompound().setInteger("energyRemaining", current);
        }

        if (PlayerMovementAbilityManager.isValidSideForMovement(player)) {
            boolean apply = !player.capabilities.isFlying && !player.isElytraFlying() && RechargeHelper.getCharge(stack) > 0;

            if (apply && !PlayerMovementAbilityManager.playerHasAbility(player, MOVEMENT_FUNC, CONTINUE_FUNC)) {
                PlayerMovementAbilityManager.put(player, MOVEMENT_FUNC, CONTINUE_FUNC);
            } else if (!apply && PlayerMovementAbilityManager.playerHasAbility(player, MOVEMENT_FUNC, CONTINUE_FUNC)) {
                PlayerMovementAbilityManager.remove(player, MOVEMENT_FUNC, CONTINUE_FUNC);
            }
        }
    }

    public int getMaxCharge(ItemStack stack, EntityLivingBase player) {
        return 330;
    }

    public EnumChargeDisplay showInHud(ItemStack stack, EntityLivingBase player) {
        return EnumChargeDisplay.PERIODIC;
    }

    @Override
    public float getNewFallDamage(ItemStack stack, float origDamage, float distance) {
        if (RechargeHelper.getCharge(stack) > 0) {
            return origDamage / 5.0F - 1.0F;
        }

        return origDamage;
    }
}
