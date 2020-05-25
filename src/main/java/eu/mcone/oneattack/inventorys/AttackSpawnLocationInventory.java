package eu.mcone.oneattack.inventorys;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AttackSpawnLocationInventory extends CoreInventory {

    public static int main_entrance = 0;
    public static int gerage = 0;

    public AttackSpawnLocationInventory(Player player) {
        super("", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        setItem(InventorySlot.ROW_2_SLOT_4, new ItemBuilder(Material.GRASS, main_entrance, 0)
                        .displayName("§eSpawnOrt | §fHaupt Eingang")
                        .lore("§7§oVote dafür wo", "§7§oihr als Trupp spawnt", "", "§8» §f§nLinksklick§8 | §7§oVoten")
                        .create(),

                e -> {
                    main_entrance++;
                    for (GamePlayer all : OneAttack.getInstance().getOnlineGamePlayers()) {
                        all.bukkit().updateInventory();
                    }
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast erfolgreich für den SpawnOrt Haupt Eingang gevotet!");
                });

        setItem(InventorySlot.ROW_2_SLOT_6, new ItemBuilder(Material.STONE, gerage, 0)
                        .displayName("§eSpawnOrt | §fGeragen Tor")
                        .lore("§7§oVote dafür wo", "§7§oihr als Trupp spawnt", "", "§8» §f§nLinksklick§8 | §7§oVoten")
                        .create(),

                e -> {
                    gerage++;
                    for (GamePlayer all : OneAttack.getInstance().getOnlineGamePlayers()) {
                        all.bukkit().updateInventory();
                    }
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast erfolgreich für den SpawnOrt Geragen Eingang gevotet!");
                });

        openInventory();

    }
}
