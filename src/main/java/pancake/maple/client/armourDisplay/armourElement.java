package pancake.maple.client.armourDisplay;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

import java.util.Objects;

public class armourElement {
    private final String name;
    private final int durability;
    private final Identifier identifier;

    // Armour Item Object
    public armourElement(String name, int durability, Identifier identifier) {
        this.name = name;
        this.durability = durability;
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public int getDurability() {
        return durability;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public String toDataString() {
        return "{" + "name='" + name + "', durability='" + durability + "', identifier='" + identifier + "'}";
    }

    // Gets all armour items the player is currently wearing
    public static armourElement[] getArmourItems(Entity player) {
        Iterable<ItemStack> items = player.getArmorItems();
        armourElement[] itemsObjectData = new armourElement[4];

        int i = 3;
        // For each item, create object with name and durability,
        // then push into Object Array for Hud Element use.
        for (ItemStack item : items) {
            if (!Objects.equals(getItemString(item), "Air")) {
                String name = getItemString(item);
                int durability = getItemDurability(item);
                Identifier identifier = getItemIdentifier(name);

                itemsObjectData[i] = new armourElement(name, durability, identifier); // Create Object
            }
            i--; // Increment the index
        }
        return itemsObjectData;
    }

    private static String getItemString(ItemStack item) {
        return item.getItem().getName().getString().toLowerCase();
    }

    private static int getItemDurability(ItemStack item) {
        return (item.getMaxDamage() - item.getDamage());
    }

    private static Identifier getItemIdentifier(String name) {
        return switch (name) {
            case "elytra" -> armourIdentifiers.elytra;
            case "netherite helmet" -> armourIdentifiers.netherite_helmet;
            case "netherite chestplate" -> armourIdentifiers.netherite_chestplate;
            case "netherite leggings" -> armourIdentifiers.netherite_leggings;
            case "netherite boots" -> armourIdentifiers.netherite_boots;
            case "diamond helmet" -> armourIdentifiers.diamond_helmet;
            case "diamond chestplate" -> armourIdentifiers.diamond_chestplate;
            case "diamond leggings" -> armourIdentifiers.diamond_leggings;
            case "diamond boots" -> armourIdentifiers.diamond_boots;
            case "iron helmet" -> armourIdentifiers.iron_helmet;
            case "iron chestplate" -> armourIdentifiers.iron_chestplate;
            case "iron leggings" -> armourIdentifiers.iron_leggings;
            case "iron boots" -> armourIdentifiers.iron_boots;
            case "golden helmet" -> armourIdentifiers.gold_helmet;
            case "golden chestplate" -> armourIdentifiers.gold_chestplate;
            case "golden leggings" -> armourIdentifiers.gold_leggings;
            case "golden boots" -> armourIdentifiers.gold_boots;
            case "chainmail helmet" -> armourIdentifiers.chain_helmet;
            case "chainmail chestplate" -> armourIdentifiers.chain_chestplate;
            case "chainmail leggings" -> armourIdentifiers.chain_leggings;
            case "chainmail boots" -> armourIdentifiers.chain_boots;
            case "leather cap" -> armourIdentifiers.leather_helmet;
            case "leather tunic" -> armourIdentifiers.leather_chestplate;
            case "leather pants" -> armourIdentifiers.leather_leggings;
            case "leather boots" -> armourIdentifiers.leather_boots;
            default -> armourIdentifiers.empty;
        };
    }

}
