package mod.icarus.crimsonrevelations.recipe;

import mod.icarus.crimsonrevelations.init.CRItems;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.items.ItemsTC;

import javax.annotation.Nonnull;

public class VerdantRingToCharm extends ShapelessArcaneRecipe {
    private static final Item ring = CRItems.VERDANT_RING;

    public VerdantRingToCharm() {
        super(new ResourceLocation(""), /*Research Required*/"", 5, new AspectList(), ItemStack.EMPTY, new Object[]{ring});
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull InventoryCrafting var1) {
        ItemStack item = ItemStack.EMPTY;

        for (int i = 0; i < var1.getSizeInventory(); i++) {
            ItemStack stack = var1.getStackInSlot(i);

            if (!stack.isEmpty() && stack.getItem() == ring) {
                item = stack;
            }
        }

        if (item.isEmpty()) {
            return ItemStack.EMPTY;
        }

        ItemStack result = new ItemStack(ItemsTC.charmVerdant);

        if (item.getTagCompound() != null) {
            result.setTagCompound(item.getTagCompound());
        }

        return result;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
}