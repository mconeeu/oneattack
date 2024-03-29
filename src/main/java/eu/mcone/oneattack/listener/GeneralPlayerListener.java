package eu.mcone.oneattack.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.scoreboard.DisplaySlot;

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

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (OneAttack.getInstance().getGadgetHandler().getHasDefuser().contains(player)) {
            OneAttack.getInstance().getGadgetHandler().getHasDefuser().remove(player);
            player.getLocation().getWorld().dropItem(player.getLocation(), Items.DEFUSER.getItem());

            OneAttack.getInstance().getGadgetHandler().createDefuserHolo(player);
            for (Player all : Bukkit.getOnlinePlayers()) {
                GamePlayer gamePlayers = OneAttack.getInstance().getGamePlayer(all);
                if (gamePlayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                    OneAttack.getInstance().getMessenger().send(gamePlayers.bukkit(), "§4Der Spieler §c" + player.getName() + "§4 hat den Entschärfer fallen gelassen finde ihn!");
                }
            }
        }
    }



    @EventHandler(priority = EventPriority.LOWEST)
    public void onTrigger(InventoryClickEvent e) {
        e.setCancelled((OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState)
                && !e.getWhoClicked().getGameMode().equals(GameMode.CREATIVE));
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

        if (gamePlayer.getCurrentKit() != null) {
            if (gamePlayer.getCurrentKit().equals(Role.TRAPPER)) {
                if (e.getItem().getItemStack().getType().equals(Items.TRAPS.getItem().getType())) {
                    e.setCancelled(false);
                }
            }
        }

        if (e.getItem().getItemStack().getType().equals(Items.DEFUSER.getItem().getType())) {
            if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {

                for (Player all : Bukkit.getOnlinePlayers()) {
                    GamePlayer gamePlayers = OneAttack.getInstance().getGamePlayer(all);
                    if (gamePlayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                        OneAttack.getInstance().getMessenger().send(gamePlayers.bukkit(), "§4Der Spieler §c" + p.getName() + "§4 hat den Entschärfer aufgenommen!");
                        if (CoreSystem.getInstance().getHologramManager().getHologram(OneAttack.getInstance().getGameWorld(), "defuser") != null) {
                            CoreSystem.getInstance().getHologramManager().removeHologram(OneAttack.getInstance().getGadgetHandler().getHologram());
                        }
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
                OneAttack.getInstance().getGadgetHandler().getHasDefuser().remove(p);
                e.setCancelled(false);

                for (Player all : Bukkit.getOnlinePlayers()) {
                    GamePlayer gamePlayers = OneAttack.getInstance().getGamePlayer(all);
                    if (gamePlayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                        OneAttack.getInstance().getMessenger().send(gamePlayers.bukkit(), "§4Der Spieler §c" + p.getName() + "§4 hat den Entschärfer fallen gelassen finde ihn!");
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
