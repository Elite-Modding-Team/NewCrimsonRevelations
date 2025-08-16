package mod.icarus.crimsonrevelations.client;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.fx.CRPacketFXArcBolt;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CRPacketHandler {
    public static SimpleNetworkWrapper INSTANCE;

    public static void preInit() {
        int id = 0;
        CRPacketHandler.INSTANCE.registerMessage(CRPacketFXArcBolt.class, CRPacketFXArcBolt.class, id++, Side.CLIENT);
    }

    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(NewCrimsonRevelations.MODID);
    }
}
