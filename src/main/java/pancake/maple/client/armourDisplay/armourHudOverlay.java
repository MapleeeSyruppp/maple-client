package pancake.maple.client.armourDisplay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class armourHudOverlay implements HudRenderCallback {
    public static final Logger LOGGER = LoggerFactory.getLogger("maple");

    private static final int green = 0x00CA00;
    private static final int yellow = 0xffcc00;
    private static final int orange = 0xff9900;
    private static final int red = 0xff0000;

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        // Init needed data from client
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        MinecraftClient client = MinecraftClient.getInstance();
        Entity player = client.player;
        MatrixStack matrices = new MatrixStack();

        onUpdate(client, player, matrices, textRenderer); // Update data
    }

    private void onUpdate(MinecraftClient client, Entity player, MatrixStack matrices, TextRenderer textRenderer) {
        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        int x = (width / 4) + (width / 4) + (width / 24) + (width / 30);
        int y = (height - 108);
        int offset = 32;

        int itemPosX = x + 42;
        int itemPosY = y + offset;
        int txtPosX = itemPosX + 28;

        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        renderSetup();
        if (player != null) {
            armourElement[] items = armourElement.getArmourItems(player);
            for (armourElement item : items) {
                renderImg(matrices, item.getIdentifier(), x, y, offset);
                double percentage = item.getDurability() * 100.0;
                if (percentage != 0.0) {
                    renderTxt(matrices, textRenderer, decimalFormat, txtPosX, itemPosY, offset, percentage);
                }
                offset += 18;
            }
        }
        RenderSystem.enableBlend();
    }

    private void renderImg(MatrixStack matrices, Identifier path, int x, int y, int offset) {
        RenderSystem.setShaderTexture(0, path);
        DrawableHelper.drawTexture(matrices, x + 42, y + offset, 0, 0, 20, 20, 20, 20);
    }

    private void renderTxt(MatrixStack matrices, TextRenderer textRenderer, DecimalFormat decimalFormat, int x, int y, int offset, double percentage) {
        String roundedPercentage = decimalFormat.format(percentage) + "%";
        DrawableHelper.drawCenteredText(matrices, textRenderer, roundedPercentage, x + 5, y + offset - 26, getColour(percentage));
    }

    private void renderSetup() {
        RenderSystem.disableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.5F);
    }

    private int getColour(Double percentage) {
        if (percentage >= 75.0) {
            return green;
        } else if (percentage >= 50.0) {
            return yellow;
        } else if (percentage >= 25.0) {
            return orange;
        } else return red;
    }
}
