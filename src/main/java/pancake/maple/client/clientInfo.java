package pancake.maple.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.entity.Entity;

public class clientInfo {
    public static int getWidth;
    public static int getHeight;
    public static MinecraftClient getClient;
    public static Entity getPlayer;
    public static TextRenderer getTextRenderer;
    MinecraftClient client = MinecraftClient.getInstance();

    public int getWidth(){
        return client.getWindow().getScaledWidth();
    }

    public int getHeight(){
        return client.getWindow().getScaledHeight();
    }

    public MinecraftClient getClient(){
        return MinecraftClient.getInstance();
    }

    public Entity getPlayer(){
        return MinecraftClient.getInstance().player;
    }

    public TextRenderer getTextRenderer(){
        return MinecraftClient.getInstance().textRenderer;
    }

}
