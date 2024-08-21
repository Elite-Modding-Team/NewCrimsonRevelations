package mod.icarus.crimsonrevelations.item;

import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import mod.icarus.crimsonrevelations.init.CRItems;
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
        if (this == CRItems.aerArrow) {
            EntityPrimalArrow aerArrow = new EntityPrimalArrow(world, shooter, CRItems.aerArrow);
            aerArrow.setArrowType(0);
            aerArrow.setKnockbackStrength(2);
            aerArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aerArrow;
        } else if (this == CRItems.aquaArrow) {
            EntityPrimalArrow aquaArrow = new EntityPrimalArrow(world, shooter, CRItems.aquaArrow);
            aquaArrow.setArrowType(1);
            aquaArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return aquaArrow;
        } else if (this == CRItems.ignisArrow) {
            EntityPrimalArrow ignisArrow = new EntityPrimalArrow(world, shooter, CRItems.ignisArrow);
            ignisArrow.setArrowType(2);
            ignisArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ignisArrow;
        } else if (this == CRItems.ordoArrow) {
            EntityPrimalArrow ordoArrow = new EntityPrimalArrow(world, shooter, CRItems.ordoArrow);
            ordoArrow.setArrowType(3);
            ordoArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return ordoArrow;
        } else if (this == CRItems.perditioArrow) {
            EntityPrimalArrow perditioArrow = new EntityPrimalArrow(world, shooter, CRItems.perditioArrow);
            perditioArrow.setArrowType(4);
            perditioArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return perditioArrow;
        } else if (this == CRItems.terraArrow) {
            EntityPrimalArrow terraArrow = new EntityPrimalArrow(world, shooter, CRItems.terraArrow);
            terraArrow.setArrowType(5);
            terraArrow.playSound(SoundsTC.hhoff, 0.6F, 0.8F / (itemRand.nextFloat() * 0.4F + 0.8F));
            return terraArrow;
        }

        return new EntitySpectralArrow(world, shooter);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack item, EntityPlayer player) {
        int enchantLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, item);

        if (this == CRItems.aerArrow ||
                this == CRItems.aquaArrow ||
                this == CRItems.ignisArrow ||
                this == CRItems.ordoArrow ||
                this == CRItems.perditioArrow ||
                this == CRItems.terraArrow) {
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
