package pancake.maple;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import pancake.maple.client.armourDisplay.armourHudOverlay;
import pancake.maple.client.coordDisplay.coordHudOverlay;


public class TutorialModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        HudRenderCallback.EVENT.register(new armourHudOverlay());
        HudRenderCallback.EVENT.register(new coordHudOverlay());
    }
}
