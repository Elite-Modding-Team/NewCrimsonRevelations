package mod.icarus.crimsonrevelations.item.tools;

import mod.icarus.crimsonrevelations.item.CRItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.api.items.IScribeTools;

public class ItemPrimordialScribingTools extends CRItem implements IScribeTools {
    public ItemPrimordialScribingTools() {
        super(EnumRarity.EPIC);
        this.maxStackSize = 1;
        this.setMaxDamage(9001);
        this.setHasSubtypes(false);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        super.onUpdate(stack, world, entity, itemSlot, isSelected);

        if (stack.isItemDamaged() && entity != null && entity.ticksExisted % 20 == 0) {
            stack.attemptDamageItem(-1, world.rand, entity instanceof EntityPlayerMP ? (EntityPlayerMP) entity : null);
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return slotChanged || oldStack.getItem() != newStack.getItem();
    }

    @Override
    public void setDamage(ItemStack stack, int damage) {
        super.setDamage(stack, 0);
    }
}
