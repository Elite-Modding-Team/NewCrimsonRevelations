package mod.icarus.crimsonrevelations;

import mod.icarus.crimsonrevelations.compat.CRCompatHandler;
import mod.icarus.crimsonrevelations.init.CRCreativeTabs;
import mod.icarus.crimsonrevelations.init.CRRenderRegistry;
import mod.icarus.crimsonrevelations.init.CRResearchRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = NewCrimsonRevelations.MODID, name = NewCrimsonRevelations.NAME, version = NewCrimsonRevelations.VERSION, dependencies = NewCrimsonRevelations.DEPENDENCIES)
public class NewCrimsonRevelations {
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = "New Crimson Revelations";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:mixinbooter@[8.0,);required-after:thaumcraft@[1.12.2-6.1.BETA26,);after:thaumicaugmentation";
    public static final CreativeTabs tabCR = new CRCreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "CrimsonRevelationsTab");

    @Mod.Instance
    public static NewCrimsonRevelations instance;

    @EventHandler
    @SideOnly(Side.CLIENT)
    public void preInit(FMLPreInitializationEvent event) {
        CRRenderRegistry.preInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CRCompatHandler.init();
        CRResearchRegistry.init();
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        CRCompatHandler.postInit();
    }
}
