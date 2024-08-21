package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.entity.projectile.EntityPrimalArrow;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderPrimalArrow extends RenderArrow<EntityPrimalArrow> {
    public static final ResourceLocation TEXTURE_AER = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_aer.png");
    public static final ResourceLocation TEXTURE_AQUA = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_aqua.png");
    public static final ResourceLocation TEXTURE_IGNIS = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_ignis.png");
    public static final ResourceLocation TEXTURE_ORDO = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_ordo.png");
    public static final ResourceLocation TEXTURE_PERDITIO = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_perditio.png");
    public static final ResourceLocation TEXTURE_TERRA = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/entity/arrow/arrow_terra.png");

    public RenderPrimalArrow(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityPrimalArrow entity) {
        switch (entity.getArrowType()) {
            case 5:
                return TEXTURE_TERRA;
            case 4:
                return TEXTURE_PERDITIO;
            case 3:
                return TEXTURE_ORDO;
            case 2:
                return TEXTURE_IGNIS;
            case 1:
                return TEXTURE_AQUA;
            case 0:
            default:
                return TEXTURE_AER;
        }
    }
}
