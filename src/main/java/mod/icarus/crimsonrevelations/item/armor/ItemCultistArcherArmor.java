package mod.icarus.crimsonrevelations.item.armor;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.model.gear.ModelCultistArcherArmor;
import mod.icarus.crimsonrevelations.init.CRItems;
import mod.icarus.crimsonrevelations.init.CRRenderRegistry;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemCultistArcherArmor extends ItemArmor {
    ModelBiped model1 = null;
    ModelBiped model2 = null;

    protected static final String TEXTURE_PATH = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/armor/cultist_archer_armor.png").toString();

    public ItemCultistArcherArmor(EntityEquipmentSlot equipmentSlot) {
        super(CRItems.ARMOR_CULTIST_ARCHER, 4, equipmentSlot);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default) {
        if (this.model1 == null) {
            this.model1 = new ModelCultistArcherArmor(0.5f);
        }
        if (this.model2 == null) {
            this.model2 = new ModelCultistArcherArmor(1.0F);
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
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }
}
