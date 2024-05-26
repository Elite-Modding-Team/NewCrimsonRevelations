package com.icarus.crimsonrevelations.init;

import com.icarus.crimsonrevelations.client.renderer.RenderOvergrownTaintacle;
import com.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;

import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderingHandler {
    public static void preInit() {
        if (Loader.isModLoaded("thaumicaugmentation"))
            RenderingRegistry.registerEntityRenderingHandler(EntityOvergrownTaintacle.class, RenderOvergrownTaintacle::new);
    }
}
