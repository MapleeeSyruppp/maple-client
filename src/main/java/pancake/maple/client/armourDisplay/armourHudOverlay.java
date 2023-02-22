package pancake.maple.client.armourDisplay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class armourHudOverlay implements HudRenderCallback {
    public static final Logger LOGGER = LoggerFactory.getLogger("maple");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        // Init needed data from client
        MinecraftClient client = MinecraftClient.getInstance();
        Entity player = client.player;
        MatrixStack matrices = new MatrixStack();

        onUpdate(client, player, matrices); // Update data
    }

    private void onUpdate(MinecraftClient client, Entity player, MatrixStack matrices){
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        int x = (width / 50);
        int y = (height - 160);
        int offset = 32;

        RenderSystem.disableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.5F);

        if (player != null) {
            armourElement[] items = armourElement.getArmourItems(player);
            for (armourElement item : items) {
                LOGGER.info(item.getName() + " | " + item.getIdentifier());
                renderImg(matrices, item.getIdentifier(), x, y, offset);
                offset += 32;
            }
        }
        RenderSystem.enableBlend();
    }

    private void renderImg(MatrixStack matrices, Identifier path, int x, int y, int offset) {
        RenderSystem.setShaderTexture(0, path);
        DrawableHelper.drawTexture(matrices, x, y + offset, 0, 0, 24, 24, 24, 24);
    }
}
