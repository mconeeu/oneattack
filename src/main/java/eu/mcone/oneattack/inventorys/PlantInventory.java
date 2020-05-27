package eu.mcone.oneattack.inventorys;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.coresystem.api.bukkit.util.Messenger;
import eu.mcone.oneattack.OneAttack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;

public class PlantInventory extends CoreInventory {

    public static HashSet<Player> isDefusing = new HashSet<>();

    public PlantInventory(Player player) {
        super("§fBombe entschärfen", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);
        PlantInventory.isDefusing.remove(player);
        isDefusing.add(player);
        OneAttack.getInstance().getMessenger().broadcast(Messenger.Broadcast.BroadcastMessageTyp.INFO_MESSAGE, "§4Die Bombe wird entschärft!");
        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 9).displayName("§cHacking...").create());
        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
            if (isDefusing.contains(player)) {
                new PlantInventory(player);
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 6).displayName("§cHacking...").create());
            }
            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                if (isDefusing.contains(player)) {
                    new PlantInventory(player);
                    player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
                    setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 5).displayName("§2Hacking..").create());
                }
                Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                    if (isDefusing.contains(player)) {
                        new PlantInventory(player);
                        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
                        setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 2).displayName("§aHacking...").create());
                    }
                    Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                        if (isDefusing.contains(player)) {
                            new PlantInventory(player);
                            player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
                            setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 3).displayName("§dHacking...").create());
                        }
                        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                            if (isDefusing.contains(player)) {
                                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
                                isDefusing.remove(player);
                                setItem(InventorySlot.ROW_2_SLOT_5, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, 8).displayName("§aErfolgreich").create());
                                OneAttack.getInstance().getTeamManager().stopGameWithWinner(OneAttack.getInstance().getAttackTeam());
                            }
                        }, 20L);
                    }, 20L);
                }, 20L);
            }, 20L);
        }, 20L);


        openInventory();


    }
}
