package com.mobiusflip.crimsonrevelations.init;

import com.mobiusflip.crimsonrevelations.client.renderer.RenderOvergrownTaintacle;
import com.mobiusflip.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderingHandler {
    public static void preInit() {
        RenderingRegistry.registerEntityRenderingHandler(EntityOvergrownTaintacle.class, RenderOvergrownTaintacle::new);
    }
}
