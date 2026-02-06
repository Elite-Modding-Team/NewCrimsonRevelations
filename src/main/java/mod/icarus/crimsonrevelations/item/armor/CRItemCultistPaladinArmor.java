package mod.icarus.crimsonrevelations.item.armor;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.model.gear.ModelCultistPaladinArmor;
import mod.icarus.crimsonrevelations.init.CRMaterials;
import mod.icarus.crimsonrevelations.init.CRRenderRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.IRarity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.items.IVisDiscountGear;
import thaumcraft.api.items.IWarpingGear;

public class CRItemCultistPaladinArmor extends ItemArmor implements IVisDiscountGear, IWarpingGear {
    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_paladin_armor.png").toString();
    ModelBiped model1 = null;
    ModelBiped model2 = null;

    public CRItemCultistPaladinArmor(EntityEquipmentSlot equipmentSlot) {
        super(CRMaterials.ARMOR_CULTIST_PALADIN, 4, equipmentSlot);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (this.model1 == null) {
            this.model1 = new ModelCultistPaladinArmor(0.5F);
        }

        if (this.model2 == null) {
            this.model2 = new ModelCultistPaladinArmor(1.0F);
        }

        EntityEquipmentSlot type = ((ItemArmor) itemStack.getItem()).armorType;
        ModelBiped model = (type == EntityEquipmentSlot.LEGS) ? this.model1 : this.model2;
        return CRRenderRegistry.getCustomArmorModel(entityLiving, itemStack, armorSlot, model);
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        return TEXTURE_PATH;
    }

    @Override
    public IRarity getForgeRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

    @Override
    public int getVisDiscount(ItemStack stack, EntityPlayer player) {
        return 4;
    }

    @Override
    public int getWarp(ItemStack stack, EntityPlayer player) {
        return 1;
    }
}
