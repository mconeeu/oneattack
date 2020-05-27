package eu.mcone.oneattack.inventorys;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DefendSpawnLocationInventory extends CoreInventory {

    public static int thirdfloor = 0;
    public static int kitchen = 0;
    public static int gerage = 0;

    public DefendSpawnLocationInventory(Player player) {
        super("Verteidungs Orte", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);


        setItem(InventorySlot.ROW_2_SLOT_3, new ItemBuilder(Material.FURNACE, kitchen, 0)
                        .displayName("§eBombenOrt | §fKüche, Büro")
                        .lore("§7§oVote dafür wo", "§7§oder BombenOrt ist", "", "§8» §f§nLinksklick§8 | §7§oVoten")
                        .create(),

                e -> {
                    kitchen++;
                    for (GamePlayer all : OneAttack.getInstance().getOnlineGamePlayers()) {
                        all.bukkit().updateInventory();
                    }
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast erfolgreich für den BombenOrt Küche gevotet!");
                });

        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.CLAY, gerage, 0)
                        .displayName("§eBombenOrt | §fGerage, Geragenraum")
                        .lore("§7§oVote dafür wo", "§7§oder BombenOrt ist", "", "§8» §f§nLinksklick§8 | §7§oVoten")
                        .create(),

                e -> {
                    gerage++;
                    for (GamePlayer all : OneAttack.getInstance().getOnlineGamePlayers()) {
                        all.bukkit().updateInventory();
                    }
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast erfolgreich für den BombenOrt Gerage gevotet!");
                });

        setItem(InventorySlot.ROW_2_SLOT_7, new ItemBuilder(Material.WOOD, thirdfloor, 0)
                        .displayName("§eBombenOrt | §fKinder, Elternschlaffzimmer")
                        .lore("§7§oVote dafür wo", "§7§oder BombenOrt ist", "", "§8» §f§nLinksklick§8 | §7§oVoten")
                        .create(),

                e -> {
                    thirdfloor++;
                    for (GamePlayer all : OneAttack.getInstance().getOnlineGamePlayers()) {
                        all.bukkit().updateInventory();
                    }
                    player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast erfolgreich für den BombenOrt 1. Etage gevotet!");
                });


        openInventory();

    }
}
