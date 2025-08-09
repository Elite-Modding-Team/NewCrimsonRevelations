package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.tile.CRTileEtherealBloom;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;

@SideOnly(value=Side.CLIENT)
public class RendererTileEtherealBloom extends TileEntitySpecialRenderer<CRTileEtherealBloom> {
    private static final ResourceLocation tx1 = new ResourceLocation("thaumcraft", "textures/models/crystalcapacitor.png");
    private static final ResourceLocation tx2 = new ResourceLocation("thaumcraft", "textures/models/bloom_leaves.png");
    private static final ResourceLocation tx3 = new ResourceLocation("thaumcraft", "textures/models/bloom_stalk.png");
    public static final ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");
    private final ModelCube model = new ModelCube();

    public void render(CRTileEtherealBloom tile, double x, double y, double z, float pt, int ds, float alpha) {
        int a;
        float rc1;
        float rc2 = rc1 = (float) tile.growthCounter + pt;
        float rc3 = rc1 - 33.0f;
        float rc4 = rc1 - 66.0f;
        if (rc1 > 100.0f) {
            rc1 = 100.0f;
        }
        if (rc2 > 50.0f) {
            rc2 = 50.0f;
        }
        if (rc3 < 0.0f) {
            rc3 = 0.0f;
        }
        if (rc3 > 33.0f) {
            rc3 = 33.0f;
        }
        if (rc4 < 0.0f) {
            rc4 = 0.0f;
        }
        if (rc4 > 33.0f) {
            rc4 = 33.0f;
        }
        float scale1 = rc1 / 100.0f;
        float scale2 = rc2 / 60.0f + 0.1666666f;
        float scale3 = rc3 / 33.0f;
        float scale4 = rc4 / 33.0f * 0.7f;
        GlStateManager.pushMatrix();
        GlStateManager.alphaFunc(516, 0.003921569f);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 1);
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(false);
        GlStateManager.disableCull();
        int i = tile.counter % 32;
        this.bindTexture(texture);
        UtilsFX.renderFacingQuad((double)tile.getPos().getX() + 0.5, (float)tile.getPos().getY() + scale1, (double)tile.getPos().getZ() + 0.5, 32, 32, 192 + i, scale1, 0xAADDFF, scale1, 1, pt);
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5 - (scale4 / 8.0f), y + scale1 - (scale4 / 6.0f), z + 0.5 - (scale4 / 8.0f));
        GlStateManager.scale(scale4 / 4.0f, scale4 / 3.0f, scale4 / 4.0f);
        this.bindTexture(tx1);
        this.model.render();
        GlStateManager.popMatrix();
        GlStateManager.disableBlend();
        float r1 = MathHelper.sin(((float) tile.counter + pt) / 12.0f) * 2.0f;
        float r2 = MathHelper.sin(((float) tile.counter + pt) / 11.0f) * 2.0f;
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 0.25, z + 0.5);
        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
        for (a = 0; a < 4; ++a) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(scale3, scale1, scale3);
            GlStateManager.rotate((90 * a), 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(r1, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(r2, 0.0f, 0.0f, 1.0f);
            UtilsFX.renderQuadCentered(tx2, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y + 0.6, z + 0.5);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
        for (a = 0; a < 4; ++a) {
            GlStateManager.pushMatrix();
            GlStateManager.scale(scale4, scale1 * 0.7f, scale4);
            GlStateManager.rotate((90 * a), 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(r2, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(r1, 0.0f, 0.0f, 1.0f);
            UtilsFX.renderQuadCentered(tx2, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x + 0.5, y, z + 0.5);
        GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
        for (a = 0; a < 4; ++a) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(scale1 / 2.0f, 0.0, 0.0);
            GlStateManager.scale(scale1, scale2, scale2);
            GlStateManager.rotate((90 * a), 1.0f, 0.0f, 0.0f);
            UtilsFX.renderQuadCentered(tx3, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GlStateManager.popMatrix();
        }
        GlStateManager.popMatrix();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.popMatrix();
    }
}