package eu.mcone.oneattack.inventorys;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.kit.DefenderRole;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class DefendKitInventory extends CoreInventory {

    public DefendKitInventory(Player player) {
        super("Verteidungs Rollen", player, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);

        int i = 0;
        for (DefenderRole roles : DefenderRole.values()) {
            setItem(i, roles.getItem(), e -> {
                if (!gamePlayer.getCurrentKit().getName().equalsIgnoreCase(roles.getKit().getName())) {
                    player.getInventory().clear();
                    gamePlayer.setKit(roles.getKit());
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast das Kit §f " + roles.getKit().getName() + "§a ausgewählt!");
                    player.playSound(player.getLocation(), Sound.CLICK,1,1);
                } else {
                    player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM,1,1);
                    OneAttack.getInstance().getMessenger().send(player, "§4Du hast das §cKit §4bereits ausgewählt!");
                }
            });
            i++;
        }

        openInventory();
    }
}
