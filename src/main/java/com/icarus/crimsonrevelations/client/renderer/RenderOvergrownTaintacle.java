package com.icarus.crimsonrevelations.client.renderer;

import com.icarus.crimsonrevelations.CrimsonRevelations;
import com.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.client.renderers.models.entity.ModelTaintacle;

@SideOnly(Side.CLIENT)
public class RenderOvergrownTaintacle extends RenderLiving<EntityOvergrownTaintacle> {
    public static final ResourceLocation TEXTURES = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/overgrown_taintacle.png");

    public RenderOvergrownTaintacle(RenderManager renderManager) {
        super(renderManager, new ModelTaintacle(12, false), 0.8F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityOvergrownTaintacle entity) {
        return TEXTURES;
    }
}
