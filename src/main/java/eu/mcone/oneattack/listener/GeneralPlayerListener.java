package eu.mcone.oneattack.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.Messenger;
import eu.mcone.gameapi.api.event.player.GamePlayerUnloadEvent;
import eu.mcone.gameapi.api.gamestate.common.EndGameState;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scoreboard.DisplaySlot;

import java.time.OffsetDateTime;

public class GeneralPlayerListener implements Listener {

    @EventHandler
    public void on(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
                if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)) {
                    CoreSystem.getInstance().getWorldManager().getWorld(OneAttack.getInstance().getGameConfig().parseConfig().getLobby()).getLocation("spawn");
                }
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuit(GamePlayerUnloadEvent e) {
        Bukkit.getScheduler().runTask(OneAttack.getInstance(), () -> {
            for (CorePlayer all : CoreSystem.getInstance().getOnlineCorePlayers()) {
                if (!all.equals(e.getCorePlayer()))
                    all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        });
    }


    @EventHandler(priority = EventPriority.LOW)
    public void onTrigger(InventoryClickEvent e) {
        if ((OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState)
                && !e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(true);
        } else {
            e.setCancelled(false);
        }
    }


    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (e.toWeatherState()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onAchievementAward(PlayerAchievementAwardedEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemPickUp(PlayerPickupItemEvent e) {
        Player p = e.getPlayer();
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(p);
        if (e.getItem().getItemStack().getType().equals(Items.DEFUSER.getItem().getType())) {
            if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                for (GamePlayer gamePlayers : OneAttack.getInstance().getOnlineGamePlayers()) {
                    if (gamePlayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                        OneAttack.getInstance().getMessenger().broadcast(Messenger.Broadcast.BroadcastMessageTyp.INFO_MESSAGE, "§4Der Spieler §c" + p.getName() + "§4 hat den Entschärfer aufgenommen!");
                    }
                }
                e.setCancelled(false);
            } else {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }


    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(p);
        if (e.getItemDrop().getItemStack().getType().equals(Items.DEFUSER.getItem().getType())) {
            if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                Items.hasDefuser.remove(p);
                e.setCancelled(false);

                for (GamePlayer gamePlayers : OneAttack.getInstance().getOnlineGamePlayers()) {
                    if (gamePlayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                        OneAttack.getInstance().getMessenger().broadcast(Messenger.Broadcast.BroadcastMessageTyp.INFO_MESSAGE, "§4Der Spieler §c" + p.getName() + "§4 hat den Entschärfer fallen gelassen finde ihn!");
                    }
                }
            } else {
                e.setCancelled(true);
            }
        } else {
            e.setCancelled(true);
        }
    }
}
