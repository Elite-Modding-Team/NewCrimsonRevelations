package mod.icarus.crimsonrevelations.network;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.network.packets.CRPacketCycleChameleon;
import mod.icarus.crimsonrevelations.network.packets.CRPacketFXArcBolt;
import mod.icarus.crimsonrevelations.network.packets.CRPacketFXCultistPortal;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class CRPacketHandler {
    public static SimpleNetworkWrapper INSTANCE;

    public static void init() {
        int id = 0;
        INSTANCE.registerMessage(CRPacketFXArcBolt.class, CRPacketFXArcBolt.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CRPacketFXCultistPortal.class, CRPacketFXCultistPortal.class, id++, Side.CLIENT);
        INSTANCE.registerMessage(CRPacketCycleChameleon.class, CRPacketCycleChameleon.class, id++, Side.SERVER);
    }

    static {
        INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(NewCrimsonRevelations.MODID);
    }
}
