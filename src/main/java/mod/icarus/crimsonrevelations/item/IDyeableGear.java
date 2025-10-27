package mod.icarus.crimsonrevelations.item;

import net.minecraft.item.ItemStack;

public interface IDyeableGear {
    public int getDyedColor(ItemStack stack);

    public void setDyedColor(ItemStack stack, int color);

    public int getDefaultDyedColorForMeta(int meta);
}
