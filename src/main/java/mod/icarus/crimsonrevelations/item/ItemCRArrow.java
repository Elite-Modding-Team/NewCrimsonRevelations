package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.init.RegistryHandler;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.init.Enchantments;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.lib.SoundsTC;

public class ItemCRArrow extends ItemArrow {
    EnumRarity rarity;

    public ItemCRArrow(EnumRarity rarity) {
        super();
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
        if (this == RegistryHandler.airPrimalArrow) {
            EntityPrimalArrow airArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.airPrimalArrow);
            airArrow.setArrowType(0);
            airArrow.setKnockbackStrength(2);
            airArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return airArrow;
        } else if (this == RegistryHandler.earthPrimalArrow) {
            EntityPrimalArrow earthArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.earthPrimalArrow);
            earthArrow.setArrowType(1);
            earthArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return earthArrow;
        } else if (this == RegistryHandler.firePrimalArrow) {
            EntityPrimalArrow fireArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.firePrimalArrow);
            fireArrow.setArrowType(2);
            fireArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return fireArrow;
        } else if (this == RegistryHandler.waterPrimalArrow) {
            EntityPrimalArrow waterArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.waterPrimalArrow);
            waterArrow.setArrowType(3);
            waterArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return waterArrow;
        } else if (this == RegistryHandler.orderPrimalArrow) {
            EntityPrimalArrow orderArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.orderPrimalArrow);
            orderArrow.setArrowType(4);
            orderArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return orderArrow;
        } else if (this == RegistryHandler.entropyPrimalArrow) {
            EntityPrimalArrow entropyArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.entropyPrimalArrow);
            entropyArrow.setArrowType(5);
            entropyArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return entropyArrow;
        }

        return new EntitySpectralArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack item, EntityPlayer player) {
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, item);

        if (this == RegistryHandler.airPrimalArrow ||
                this == RegistryHandler.earthPrimalArrow ||
                this == RegistryHandler.entropyPrimalArrow ||
                this == RegistryHandler.firePrimalArrow ||
                this == RegistryHandler.orderPrimalArrow ||
                this == RegistryHandler.waterPrimalArrow) {
            if (enchantLevel <= 0) {
                return false;
            } else {

                // Primal arrows have a 1/3 chance to work with the Infinity enchant
                return (player.getRNG().nextInt(3) == 0);
            }
        }

        return false;
    }
}
