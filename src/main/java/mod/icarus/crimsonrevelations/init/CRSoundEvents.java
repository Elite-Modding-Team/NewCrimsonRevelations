package mod.icarus.crimsonrevelations.init;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CRSoundEvents {
    public static final SoundEvent FOCUS_BLINDING_LIGHT_HIT = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "focus.blinding_light.hit"));
    public static final SoundEvent FOCUS_BLINDING_LIGHT_SHOOT = new SoundEvent(new ResourceLocation(NewCrimsonRevelations.MODID, "focus.blinding_light.shoot"));
}
