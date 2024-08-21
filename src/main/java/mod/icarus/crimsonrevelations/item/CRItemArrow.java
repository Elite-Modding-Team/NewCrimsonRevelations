package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.init.CRRegistry;
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

public class CRItemArrow extends ItemArrow {
    EnumRarity rarity;

    public CRItemArrow(EnumRarity rarity) {
        super();
        this.rarity = rarity;
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return rarity;
    }

    @Override
    public EntityArrow createArrow(World world, ItemStack stack, EntityLivingBase shooter) {
        if (this == CRRegistry.aerArrow) {
            EntityPrimalArrow aerArrow = new EntityPrimalArrow(world, shooter, CRRegistry.aerArrow);
            aerArrow.setArrowType(0);
            aerArrow.setKnockbackStrength(2);
            aerArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aerArrow;
        } else if (this == CRRegistry.aquaArrow) {
            EntityPrimalArrow aquaArrow = new EntityPrimalArrow(world, shooter, CRRegistry.aquaArrow);
            aquaArrow.setArrowType(1);
            aquaArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aquaArrow;
        } else if (this == CRRegistry.ignisArrow) {
            EntityPrimalArrow ignisArrow = new EntityPrimalArrow(world, shooter, CRRegistry.ignisArrow);
            ignisArrow.setArrowType(2);
            ignisArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ignisArrow;
        } else if (this == CRRegistry.ordoArrow) {
            EntityPrimalArrow ordoArrow = new EntityPrimalArrow(world, shooter, CRRegistry.ordoArrow);
            ordoArrow.setArrowType(3);
            ordoArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ordoArrow;
        } else if (this == CRRegistry.perditioArrow) {
            EntityPrimalArrow perditioArrow = new EntityPrimalArrow(world, shooter, CRRegistry.perditioArrow);
            perditioArrow.setArrowType(4);
            perditioArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return perditioArrow;
        } else if (this == CRRegistry.terraArrow) {
            EntityPrimalArrow terraArrow = new EntityPrimalArrow(world, shooter, CRRegistry.terraArrow);
            terraArrow.setArrowType(5);
            terraArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return terraArrow;
        }

        return new EntitySpectralArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack item, EntityPlayer player) {
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, item);

        if (this == CRRegistry.aerArrow ||
                this == CRRegistry.aquaArrow ||
                this == CRRegistry.ignisArrow ||
                this == CRRegistry.ordoArrow ||
                this == CRRegistry.perditioArrow ||
                this == CRRegistry.terraArrow) {
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
