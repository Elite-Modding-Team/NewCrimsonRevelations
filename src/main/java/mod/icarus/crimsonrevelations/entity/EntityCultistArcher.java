package mod.icarus.crimsonrevelations.entity;

import mod.icarus.crimsonrevelations.init.CRItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.AbstractIllager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.ai.combat.AICultistHurtByTarget;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.cult.EntityCultist;

public class EntityCultistArcher extends EntityCultist {
    public EntityCultistArcher(World world) {
        super(world);
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0, false));
        this.tasks.addTask(4, new EntityAIRestrictOpenDoor(this));
        this.tasks.addTask(5, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(6, new EntityAIMoveTowardsRestriction(this, 0.8));
        this.tasks.addTask(7, new EntityAIWander(this, 0.8));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new AICultistHurtByTarget(this, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget<EntityPlayer>(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget<EntityEldritchGuardian>(this, EntityEldritchGuardian.class, true));
        this.targetTasks.addTask(4, new EntityAINearestAttackableTarget<AbstractIllager>(this, AbstractIllager.class, true));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0);
    }

    @Override
    protected void setLoot(DifficultyInstance diff) {
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(CRItems.crimsonArcherHelmet));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(CRItems.crimsonArcherChestplate));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(CRItems.crimsonArcherGreaves));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
        this.setHeldItem(this.getActiveHand(), new ItemStack(Items.BOW));
    }

    @Override
    protected void setEnchantmentBasedOnDifficulty(DifficultyInstance diff) {
        final float f = diff.getClampedAdditionalDifficulty();
        if (this.getHeldItemMainhand() != null && !this.getHeldItemMainhand().isEmpty() && this.rand.nextFloat() < 0.25f * f) {
            EnchantmentHelper.addRandomEnchantment(this.rand, this.getHeldItemMainhand(), (int) (5.0f + f * this.rand.nextInt(18)), false);
        }
    }
}
