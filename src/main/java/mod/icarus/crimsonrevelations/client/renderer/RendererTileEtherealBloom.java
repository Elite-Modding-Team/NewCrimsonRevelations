package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.tile.CRTileEtherealBloom;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;

@SideOnly(value=Side.CLIENT)
public class RendererTileEtherealBloom extends TileEntitySpecialRenderer<CRTileEtherealBloom> {
    private static final ResourceLocation tx1 = new ResourceLocation("thaumcraft", "textures/models/crystalcapacitor.png");
    private static final ResourceLocation tx2 = new ResourceLocation("thaumcraft", "textures/models/bloom_leaves.png");
    private static final ResourceLocation tx3 = new ResourceLocation("thaumcraft", "textures/models/bloom_stalk.png");
    public static final ResourceLocation texture = new ResourceLocation("thaumcraft", "textures/misc/nodes.png");
    private final ModelCube model = new ModelCube();

    public void render(CRTileEtherealBloom tile, double x, double y, double z, float pt, int p_180535_9_, float alpha) {
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
        Tessellator tessellator = Tessellator.getInstance();
        GL11.glPushMatrix();
        GL11.glAlphaFunc(516, 0.003921569f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 1);
        GL11.glPushMatrix();
        GL11.glDepthMask(false);
        GL11.glDisable(2884);
        int i = tile.counter % 32;
        this.bindTexture(texture);
        UtilsFX.renderFacingQuad((double)tile.getPos().getX() + 0.5, (float)tile.getPos().getY() + scale1, (double)tile.getPos().getZ() + 0.5, 32, 32, 192 + i, scale1, 0xAADDFF, scale1, 1, pt);
        GL11.glEnable(2884);
        GL11.glDepthMask(true);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5 - (double)(scale4 / 8.0f), y + (double)scale1 - (double)(scale4 / 6.0f), z + 0.5 - (double)(scale4 / 8.0f));
        GL11.glScaled(scale4 / 4.0f, scale4 / 3.0f, scale4 / 4.0f);
        this.bindTexture(tx1);
        this.model.render();
        GL11.glPopMatrix();
        GL11.glDisable(3042);
        float r1 = MathHelper.sin(((float) tile.counter + pt) / 12.0f) * 2.0f;
        float r2 = MathHelper.sin(((float) tile.counter + pt) / 11.0f) * 2.0f;
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.25, z + 0.5);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glScaled(scale3, scale1, scale3);
            GL11.glRotatef((float)(90 * a), 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(r1, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(r2, 0.0f, 0.0f, 1.0f);
            UtilsFX.renderQuadCentered(tx2, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y + 0.6, z + 0.5);
        GL11.glRotatef(45.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glScaled(scale4, scale1 * 0.7f, scale4);
            GL11.glRotatef((float)(90 * a), 1.0f, 0.0f, 0.0f);
            GL11.glRotatef(r2, 0.0f, 1.0f, 0.0f);
            GL11.glRotatef(r1, 0.0f, 0.0f, 1.0f);
            UtilsFX.renderQuadCentered(tx2, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated(x + 0.5, y, z + 0.5);
        GL11.glRotatef(90.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(180.0f, 1.0f, 0.0f, 0.0f);
        for (a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glTranslated(scale1 / 2.0f, 0.0, 0.0);
            GL11.glScaled(scale1, scale2, scale2);
            GL11.glRotatef((float)(90 * a), 1.0f, 0.0f, 0.0f);
            UtilsFX.renderQuadCentered(tx3, 1.0f, 1.0f, 1.0f, 1.0f, 200, 771, 1.0f);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glAlphaFunc(516, 0.1f);
        GL11.glPopMatrix();
    }
}