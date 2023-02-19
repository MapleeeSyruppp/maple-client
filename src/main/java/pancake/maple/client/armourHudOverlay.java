package pancake.maple.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import pancake.maple.TutorialMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class armourHudOverlay implements HudRenderCallback {

    private static final Identifier CHESTPLATE = new Identifier(TutorialMod.MOD_ID,"textures/chestplate_display.png");
    private static final Identifier PANTS = new Identifier(TutorialMod.MOD_ID,"textures/pants_display.png");
    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {

        int x = 5;
        int y = 5;

        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null){
            int width = client.getWindow().getScaledWidth();
            int height = client.getWindow().getScaledHeight();

            // TODO:
            // 1. FIX WHERE ARMOUR TEXTURES ARE PLACED
            // 2. IMPLEMENT EDGE CASES
            x = width / 2;
            y = height;
            RenderSystem.disableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexProgram);
            RenderSystem.setShaderColor(1.0F,1.0F,1.0F, 0.5F);
            RenderSystem.setShaderTexture(0,CHESTPLATE);
            MatrixStack matrices = new MatrixStack();
            DrawableHelper.drawTexture(matrices,0,0,0,0,24,24,24,24);
            RenderSystem.enableBlend();
        }
    }
}
