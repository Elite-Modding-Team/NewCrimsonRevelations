package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.CrimsonRevelations;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPrimalArrow extends RenderArrow<EntityPrimalArrow> {
    public static final ResourceLocation TEXTURE_AIR = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/arrow/primal_arrow_air.png");
    public static final ResourceLocation TEXTURE_EARTH = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/arrow/primal_arrow_earth.png");
    public static final ResourceLocation TEXTURE_ENTROPY = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/arrow/primal_arrow_entropy.png");
    public static final ResourceLocation TEXTURE_FIRE = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/arrow/primal_arrow_fire.png");
    public static final ResourceLocation TEXTURE_ORDER = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/arrow/primal_arrow_order.png");
    public static final ResourceLocation TEXTURE_WATER = new ResourceLocation(CrimsonRevelations.MODID, "textures/entity/arrow/primal_arrow_water.png");

    public RenderPrimalArrow(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPrimalArrow entity) {
        switch (entity.getArrowType()) {
            case 5:
                return TEXTURE_ENTROPY;
            case 4:
                return TEXTURE_ORDER;
            case 3:
                return TEXTURE_WATER;
            case 2:
                return TEXTURE_FIRE;
            case 1:
                return TEXTURE_EARTH;
            case 0:
            default:
                return TEXTURE_AIR;
        }
    }
}
