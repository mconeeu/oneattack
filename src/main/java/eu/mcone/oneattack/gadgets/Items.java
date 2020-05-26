package eu.mcone.oneattack.gadgets;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.kit.Kit;
import eu.mcone.oneattack.kit.Role;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashSet;

public enum Items {

    /* DEFENDS */

    REINFORCE_HOE("Reinforce Hoe", new ItemBuilder(Material.IRON_HOE, 6).unbreakable(true).displayName("§fReinforce Hoe").create()),
    TRAPS("Bären Fallen", new ItemBuilder(Material.WOOD_PLATE, 4).unbreakable(true).displayName("§fBären Falle").create()),

    /* ALL */

    WALL_PICKAXE("Öffner Picke", new ItemBuilder(Material.GOLD_PICKAXE, 5).unbreakable(true).displayName("§fÖffner Hacke").create()),

    /* ATTACKER */

    DEFUSER("Entschärfer", new ItemBuilder(Material.TRIPWIRE_HOOK, 1).unbreakable(true).displayName("§fEntschärfer").create()),

    PUSHER_PICKAXE("Pusher Picke", new ItemBuilder(Material.IRON_PICKAXE, 3).unbreakable(true).displayName("§fPusher Hacke").create());


    @Getter
    private final String name;
    @Getter
    private final ItemStack item;

    Items(String name, ItemStack item) {
        this.name = name;
        this.item = item;
    }


    public static HashSet<Player> hasDefuser = new HashSet<>();
}
