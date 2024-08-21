package mod.icarus.crimsonrevelations.events;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.item.CRItemBow;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber(modid = NewCrimsonRevelations.MODID)
@GameRegistry.ObjectHolder(NewCrimsonRevelations.MODID)
public class CREvents {
    // Courtesy of NeRdTheNed
    @SubscribeEvent
    public void FOV(FOVUpdateEvent event) {
        final EntityPlayer eventPlayer = event.getEntity();
        final Item eventItem = eventPlayer.getActiveItemStack().getItem();

        if (eventItem instanceof CRItemBow) {
            float finalFov = event.getFov();
            final float itemUseCount = ((CRItemBow) eventItem).getMaxItemUseDuration(eventPlayer.getActiveItemStack()) - eventPlayer.getItemInUseCount();
            /*
             * First, we have to reverse the standard bow zoom.
             * Minecraft helpfully applies the standard bow zoom
             * to any item that is an instance of a ItemBow.
             * However, our custom bows draw back at different speeds,
             * so the standard zoom is not at the right speed.
             * To compensate for this, we just calculate the standard bow zoom,
             * and apply it in reverse.
             */
            float realBow = itemUseCount / 20.0F;

            if (realBow > 1.0F) {
                realBow = 1.0F;
            } else {
                realBow *= realBow;
            }

            /*
             * Minecraft uses finalFov *= 1.0F - (realBow * 0.15F)
             * to calculate the standard bow zoom.
             * To reverse this, we just divide it instead.
             */
            finalFov /= 1.0F - (realBow * 0.15F);
            /*
             * We now calculate and apply our custom bow zoom.
             * The only difference between standard bow zoom and custom bow zoom
             * is that we change the hardcoded value of 20.0F to
             * whatever drawTime is.
             */
            float drawTime = 20 * ((CRItemBow) eventItem).drawTimeMult;
            float customBow = itemUseCount / drawTime;

            if (customBow > 1.0F) {
                customBow = 1.0F;
            } else {
                customBow *= customBow;
            }

            finalFov *= 1.0F - (customBow * 0.15F);
            event.setNewfov(finalFov);
        }
    }
}
