package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(CrimsonRevelations.MODID)
public class SoundHandler {
    public static final SoundEvent FOCUS_BLINDING_LIGHT_HIT = new SoundEvent(new ResourceLocation(CrimsonRevelations.MODID, "focus.blinding_light.hit"));
    public static final SoundEvent FOCUS_BLINDING_LIGHT_SHOOT = new SoundEvent(new ResourceLocation(CrimsonRevelations.MODID, "focus.blinding_light.shoot"));
}
