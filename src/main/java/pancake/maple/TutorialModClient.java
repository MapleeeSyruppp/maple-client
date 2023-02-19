package pancake.maple;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import pancake.maple.armourStats.Stats;
import pancake.maple.client.armourHudOverlay;


public class TutorialModClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {

        //Stats.getPlayer();
        HudRenderCallback.EVENT.register(new armourHudOverlay());
    }
}
