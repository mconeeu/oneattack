package eu.mcone.oneattack.gadgets;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum Items {

    /* DEFENDS */

    REINFORCE_HOE("Reinforce Hoe", new ItemBuilder(Material.IRON_HOE, 6).unbreakable(true).displayName("§fVerstärker").create()),
    TRAPS("Bären Falle", new ItemBuilder(Material.WOOD_PLATE, 4).unbreakable(true).displayName("§fBären Falle").create()),
    ONE_TRAP("Bären Falle", new ItemBuilder(Material.WOOD_PLATE, 1).unbreakable(true).displayName("§fBären Falle").create()),
    BARRICADE("Barrikade", new ItemBuilder(Material.STONE, 3).displayName("§fBarrikaden").create()),

    /* ALL */

    PUSHER_PICKAXE("Pusher Picke", new ItemBuilder(Material.IRON_PICKAXE, 3).unbreakable(true).displayName("§fPusher Hacke").create()),

    /* ATTACKER */

    DEFUSER("Entschärfer", new ItemBuilder(Material.TRIPWIRE_HOOK, 1).unbreakable(true).displayName("§fEntschärfer").create()),
    WALL_PICKAXE("Öffner Picke", new ItemBuilder(Material.GOLD_PICKAXE, 5).unbreakable(true).displayName("§fVerstärker Hacke").create()),
    FAST_PICKAXE("Schnelle Picke", new ItemBuilder(Material.DIAMOND_PICKAXE, 3).unbreakable(true).displayName("§fSchnelle Hacke").create());


    @Getter
    private final String name;
    @Getter
    private final ItemStack item;

    Items(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }

}
