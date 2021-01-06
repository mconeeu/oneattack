package eu.mcone.oneattack.listener;

import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.inventorys.PlantInventory;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void on(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player player = (Player) e.getEntity();
            Player damager = (Player) e.getDamager();
            GamePlayer gameDamager = OneAttack.getInstance().getGamePlayer(damager);
            GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);

            if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                    || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
                e.setCancelled(true);
            } else {
                if (!gameDamager.getTeam().getName().equalsIgnoreCase(gamePlayer.getTeam().getName())) {
                    e.setCancelled(false);

                    if (OneAttack.getInstance().getGadgetHandler().getHasDefuser().contains(player)) {
                        OneAttack.getInstance().getGadgetHandler().getHasDefuser().remove(player);
                        player.closeInventory();
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            OneAttack.getInstance().getMessenger().send(all, "§4Der Entschärfungs Vorgang wurde abgebrochen!");
                        }
                    }
                } else {
                    e.setCancelled(true);
                    damager.damage(1);
                    damager.playSound(damager.getLocation(), Sound.NOTE_BASS_DRUM, 1, 1);
                    OneAttack.getInstance().getMessenger().send(damager, "§4Du darfst deinen Team Partner nicht angreifen!");
                }
            }
        } else {
            e.setCancelled(true);
        }

    }
}
