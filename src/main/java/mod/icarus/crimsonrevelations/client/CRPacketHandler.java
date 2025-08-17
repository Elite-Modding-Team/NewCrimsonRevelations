package mod.icarus.crimsonrevelations.client;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.fx.CRPacketFXArcBolt;
import mod.icarus.crimsonrevelations.client.fx.CRPacketFXCultistPortal;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CRPacketHandler {
    public static SimpleNetworkWrapper INSTANCE;

    public static void preInit() {
        int id = 0;
        INSTANCE.registerMessage(CRPacketFXArcBolt.class, CRPacketFXArcBolt.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CRPacketFXCultistPortal.class, CRPacketFXCultistPortal.class, id++, Side.CLIENT);
    }

    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(NewCrimsonRevelations.MODID);
    }
}
