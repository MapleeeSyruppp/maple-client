package pancake.maple.client.coordDisplay;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

public class coordHudOverlay implements HudRenderCallback {
    public static final Logger LOGGER = LoggerFactory.getLogger("maple");

    @Override
    public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        MinecraftClient client = MinecraftClient.getInstance();
        Entity player = client.player;
        MatrixStack matrices = new MatrixStack();

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();
        int x = (width / 4) + (width / 9) + (width / 28);
        int y = height + 165;

        DecimalFormat decimalFormat = new DecimalFormat("#.#");

        if (player != null) {
            Vec3d pos = getPos(player);
            renderPosTxt(matrices, textRenderer, decimalFormat, x, y, pos);

        }

    }

    private Vec3d getPos(Entity player) {
        return player.getPos();
    }

    private void renderPosTxt(MatrixStack matrices, TextRenderer textRenderer, DecimalFormat decimalFormat, int x, int y, Vec3d pos) {
        String posX = decimalFormat.format(pos.x);
        String posY = decimalFormat.format(pos.y);
        String posZ = decimalFormat.format(pos.z);

        String posInTxt = posX + ", " + posY + ", " + posZ;
        DrawableHelper.drawCenteredText(matrices, textRenderer, posInTxt, x, y - 180, 0xFFFFFF);
    }

}
