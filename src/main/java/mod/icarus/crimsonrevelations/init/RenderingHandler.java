package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.client.renderer.RenderOvergrownTaintacle;
import mod.icarus.crimsonrevelations.core.CRConfig;
import mod.icarus.crimsonrevelations.entity.boss.EntityOvergrownTaintacle;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderingHandler {
    public static void preInit() {
        if (Loader.isModLoaded("thaumicaugmentation") && CRConfig.general_settings.TA_INTEGRATION)
            RenderingRegistry.registerEntityRenderingHandler(EntityOvergrownTaintacle.class, RenderOvergrownTaintacle::new);
    }
}
