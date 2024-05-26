package com.icarus.crimsonrevelations.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;

import com.icarus.crimsonrevelations.init.LootTableHandler;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import thaumcraft.common.entities.monster.cult.EntityCultistPortalLesser;

@Mixin(value = EntityCultistPortalLesser.class, remap = false)
public class EntityCultistPortalLesserMixin extends EntityMob {
    public EntityCultistPortalLesserMixin(World world) {
        super(world);
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LootTableHandler.LESSER_CULTIST_PORTAL;
    }
}
