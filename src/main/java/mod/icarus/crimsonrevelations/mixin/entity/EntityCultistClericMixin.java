package mod.icarus.crimsonrevelations.mixin.entity;

import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import thaumcraft.api.items.ItemsTC;
import thaumcraft.common.entities.monster.cult.EntityCultist;
import thaumcraft.common.entities.monster.cult.EntityCultistCleric;

@Mixin(value = EntityCultistCleric.class, remap = false)
public class EntityCultistClericMixin extends EntityCultist {
    public EntityCultistClericMixin(World world) {
        super(world);
    }

    // Replaces the original equip event with ours
    @Inject(method = "setLoot", at = @At(value = "HEAD"), cancellable = true)
    public void crSetLoot(DifficultyInstance diff, CallbackInfo ci) {
        ci.cancel();
        this.setItemStackToSlot(EntityEquipmentSlot.HEAD, new ItemStack(ItemsTC.crimsonRobeHelm));
        this.setItemStackToSlot(EntityEquipmentSlot.CHEST, new ItemStack(ItemsTC.crimsonRobeChest));
        this.setItemStackToSlot(EntityEquipmentSlot.LEGS, new ItemStack(ItemsTC.crimsonRobeLegs));
        this.setItemStackToSlot(EntityEquipmentSlot.FEET, new ItemStack(ItemsTC.crimsonBoots));
    }
}
