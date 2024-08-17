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
        if (this == RegistryHandler.aerArrow) {
            EntityPrimalArrow aerArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.aerArrow);
            aerArrow.setArrowType(0);
            aerArrow.setKnockbackStrength(2);
            aerArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aerArrow;
        } else if (this == RegistryHandler.aquaArrow) {
            EntityPrimalArrow aquaArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.aquaArrow);
            aquaArrow.setArrowType(1);
            aquaArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aquaArrow;
        } else if (this == RegistryHandler.ignisArrow) {
            EntityPrimalArrow ignisArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.ignisArrow);
            ignisArrow.setArrowType(2);
            ignisArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ignisArrow;
        } else if (this == RegistryHandler.ordoArrow) {
            EntityPrimalArrow ordoArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.ordoArrow);
            ordoArrow.setArrowType(3);
            ordoArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ordoArrow;
        } else if (this == RegistryHandler.perditioArrow) {
            EntityPrimalArrow perditioArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.perditioArrow);
            perditioArrow.setArrowType(4);
            perditioArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return perditioArrow;
        } else if (this == RegistryHandler.terraArrow) {
            EntityPrimalArrow terraArrow = new EntityPrimalArrow(world, shooter, RegistryHandler.terraArrow);
            terraArrow.setArrowType(5);
            terraArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return terraArrow;
        }

        return new EntitySpectralArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack item, EntityPlayer player) {
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, item);

        if (this == RegistryHandler.aerArrow ||
                this == RegistryHandler.aquaArrow ||
                this == RegistryHandler.ignisArrow ||
                this == RegistryHandler.ordoArrow ||
                this == RegistryHandler.perditioArrow ||
                this == RegistryHandler.terraArrow) {
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
