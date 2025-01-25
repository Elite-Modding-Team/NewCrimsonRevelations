package mod.icarus.crimsonrevelations;

import mod.icarus.crimsonrevelations.compat.CRCompatHandler;
import mod.icarus.crimsonrevelations.events.CRClientEvents;
import mod.icarus.crimsonrevelations.events.CREvents;
import mod.icarus.crimsonrevelations.init.*;
import mod.icarus.crimsonrevelations.item.CRItemManaBean;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = NewCrimsonRevelations.MODID, name = NewCrimsonRevelations.NAME, version = NewCrimsonRevelations.VERSION, dependencies = NewCrimsonRevelations.DEPENDENCIES)
public class NewCrimsonRevelations {
    public static final String MODID = Tags.MOD_ID;
    public static final String NAME = "New Crimson Revelations";
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:mixinbooter;required-after:thaumcraft@[1.12.2-6.1.BETA26,);after:thaumicaugmentation";
    public static final CreativeTabs tabCR = new CRCreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length, "CrimsonRevelationsTab");

    @Mod.Instance
    public static NewCrimsonRevelations instance;

    public static boolean isServer() {
        return FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new CREvents());

        if (!isServer()) {
            MinecraftForge.EVENT_BUS.register(new CRClientEvents());
            CRRenderRegistry.preInit();
        }
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        CRCompatHandler.init();
        CRResearchRegistry.init();

        CRRecipes.initArcaneCrafting();
        CRRecipes.initCrucible();
        CRRecipes.initInfusion();

        if (!isServer()) {

            IItemColor itemCrystalPlanterColourHandler = (stack, tintIndex) -> {
                Item item = stack.getItem();
                if (item == CRItems.manaBeanItem) {
                    return ((CRItemManaBean) item).getColor(stack, tintIndex);
                }
                return 16777215;
            };

            Minecraft.getMinecraft().getItemColors().registerItemColorHandler(itemCrystalPlanterColourHandler, CRItems.manaBeanItem);

        }
    }

    @EventHandler
    public void postinit(FMLPostInitializationEvent event) {
        CREntities.registerEntitySpawns();
        CRCompatHandler.postInit();
    }
}
