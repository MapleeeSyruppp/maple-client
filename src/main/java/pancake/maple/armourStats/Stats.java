package pancake.maple.armourStats;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Stats {
    public static final String MOD_ID = "maple";
    public static final Logger LOGGER = LoggerFactory.getLogger("maple");

    public static void getEquippedArmour(Entity player){
        // Creates an array of items currently equipped on the player
        Iterable<ItemStack> equippedArmour = player.getItemsEquipped();
        for (ItemStack armourPiece : equippedArmour) {
            // Calculate item durability
            int durability = armourPiece.getMaxDamage() - armourPiece.getDamage();
            // Display item data
            if(!armourPiece.isEmpty()){
                LOGGER.info(armourPiece.toString() + " | " + durability);
            }
        }

    }

    public static void getPlayer(){
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            Entity player = minecraftClient.player;

            if(minecraftClient.world != null){
                if(player != null){
                    getEquippedArmour(player);
                }
            }
        });
    }
}
