package mod.icarus.crimsonrevelations.client.renderer;

import mod.icarus.crimsonrevelations.NewCrimsonRevelations;
import mod.icarus.crimsonrevelations.client.model.gear.ModelManaPod;
import mod.icarus.crimsonrevelations.tile.CRTileManaPod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;

import java.awt.*;

public class TileManaPodRenderer extends TileEntitySpecialRenderer {
    private static final ResourceLocation pod0tex = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/block/manapod_0.png");

    private static final ResourceLocation pod2tex = new ResourceLocation(NewCrimsonRevelations.MODID, "textures/models/block/manapod_2.png");

    private final ModelManaPod model = new ModelManaPod();

    public void renderEntityAt(CRTileManaPod pod, double x, double y, double z, float fq) {
        int meta = 0;
        //int bright = 20;
        Aspect aspect = Aspect.PLANT;
        if (pod.getWorld() == null) {
            meta = 5;
        } else {
            meta = pod.getBlockMetadata();
            if (pod.aspect != null)
                aspect = pod.aspect;
            //bright = pod.getBlockType().func_149677_c((IBlockAccess)pod.getWorld(), pod.field_145851_c, pod.field_145848_d, pod.field_145849_e);
        }
        if (meta > 1) {
            float br = 0.14509805F;
            float bg = 0.6156863F;
            float bb = 0.45882353F;
            float fr = br;
            float fg = bg;
            float fb = bb;
            if (pod.aspect != null) {
                Color color = new Color(aspect.getColor());
                float ar = color.getRed() / 255.0F;
                float ag = color.getGreen() / 255.0F;
                float ab = color.getBlue() / 255.0F;
                if (meta == 7) {
                    fr = ar;
                    fg = ag;
                    fb = ab;
                } else {
                    float m = (meta - 2);
                    fr = (br + ar * m) / (m + 1.0F);
                    fg = (bg + ag * m) / (m + 1.0F);
                    fb = (bb + ab * m) / (m + 1.0F);
                }
            }
            Minecraft mc = FMLClientHandler.instance().getClient();
            GL11.glPushMatrix();
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            GL11.glEnable(32826);
            GL11.glBlendFunc(770, 771);
            GL11.glTranslated(x + 0.5D, y + 0.75D, z + 0.5D);
            GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
            if (meta > 2) {
                EntityPlayerSP entityClientPlayerMP = (Minecraft.getMinecraft()).player;
                float scale = MathHelper.sin((entityClientPlayerMP.ticksExisted + pod.hashCode() % 100) / 8.0F) * 0.1F + 0.9F;
                GL11.glPushMatrix();
                float bs = MathHelper.sin((entityClientPlayerMP.ticksExisted + pod.hashCode() % 100) / 8.0F) * 0.3F + 0.7F;
                int j = meta * 10 + (int) (150.0F * scale);
                int k = j % 65536;
                int l = j / 65536;
                OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, k, l);
                GL11.glTranslated(0.0D, 0.1D, 0.0D);
                GL11.glScaled(0.125D * meta * scale, 0.125D * meta * scale, 0.125D * meta * scale);
                this.bindTexture(pod0tex);
                this.model.pod0.render(0.0625F);
                GL11.glPopMatrix();
            }
            GL11.glScaled(0.15D * meta, 0.15D * meta, 0.15D * meta);
            GL11.glColor4f(fr, fg, fb, 0.9F);
            this.bindTexture(pod2tex);
            this.model.pod2.render(0.0625F);
            GL11.glDisable(32826);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
    }

    public void render(TileEntity tileentity, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        renderEntityAt((CRTileManaPod) tileentity, x, y, z, partialTicks);
    }
}
