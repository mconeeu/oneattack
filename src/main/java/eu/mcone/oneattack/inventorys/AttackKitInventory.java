package eu.mcone.oneattack.inventorys;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.kit.AttackerRole;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AttackKitInventory extends CoreInventory {

    public AttackKitInventory(Player player) {
        super("Angriffs Rollen", player, InventorySlot.ROW_5, InventoryOption.FILL_EMPTY_SLOTS);
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);
        CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player);

        int i = 0;
        for (AttackerRole roles : AttackerRole.values()) {
            setItem(i, roles.getItem(), e -> {
                if (gamePlayer.getCorePlayer().getCoins() >= roles.getKit().getCoinsPrice()) {
                    player.getInventory().clear();
                    gamePlayer.setKit(roles.getKit());
                    corePlayer.removeCoins(roles.getKit().getCoinsPrice());
                    player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§aDu hast das Kit §f " + roles.getKit().getName() + "§a gekauft und ausgerüstet!");
                } else {
                    player.playSound(player.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§4Du hast nicht genügend Coins!");
                }
            });

            i++;
        }

        openInventory();
    }
}
