package eu.mcone.oneattack.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.hologram.Hologram;
import eu.mcone.coresystem.api.bukkit.hologram.HologramData;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.util.Messenger;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.player.PlayerManager;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

public class PlayerDeathListener implements Listener {

    public static Hologram hologram;

    public static void createDefuserHolo(Player player) {
       hologram = CoreSystem.getInstance().getHologramManager().addHologram(
                new HologramData(
                        "defuser",
                        new String[]{"§fEntschärfer"},
                        new CoreLocation(player.getLocation())
                )
        );
    }

    @EventHandler
    public void on(PlayerDeathEvent e) {
        e.setDeathMessage(null);
        Player player = e.getEntity().getPlayer();
        Player killer = (player.getKiller() != null ? player.getKiller() : OneAttack.getInstance().getDamageLogger().getKiller(player));

        player.getInventory().clear();
        player.getInventory().setArmorContents(new ItemStack[0]);
        e.getDrops().clear();


        if (Items.hasDefuser.contains(player)) {
            Items.hasDefuser.remove(player);
            player.getLocation().getWorld().dropItem(player.getLocation(), Items.DEFUSER.getItem());

            createDefuserHolo(player);

            for (Player all : Bukkit.getOnlinePlayers()) {
                GamePlayer gamePlayers = OneAttack.getInstance().getGamePlayer(all);
                if (gamePlayers.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                    OneAttack.getInstance().getMessenger().send(gamePlayers.bukkit(), "§4Der Spieler §c" + player.getName() + "§4 hat den Entschärfer fallen gelassen finde ihn!");
                }
            }
        }

        if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
            CoreSystem.getInstance().getWorldManager().getWorld(OneAttack.getInstance().getGameConfig().parseConfig().getLobby()).teleport(player, "spawn");
        } else {
            GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);
            gamePlayer.addDeaths(1);

            if (killer != null) {
                GamePlayer gameKiller = OneAttack.getInstance().getGamePlayer(killer);
                gameKiller.addKills(1);
                OneAttack.getInstance().getMessenger().broadcast(Messenger.Broadcast.BroadcastMessageTyp.KILL_MESSAGE, "§7Der Spieler §f" + killer.getName() + "§7 hat §f" + player.getName() + "§7 getötet!");
                killer.addPotionEffect(PotionEffectType.REGENERATION.createEffect(35, 3));
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
                player.playEffect(player.getLocation(), Effect.INSTANT_SPELL, 1);
            } else {
                OneAttack.getInstance().getMessenger().broadcast(Messenger.Broadcast.BroadcastMessageTyp.DEATH_MESSAGE, "§7Der Spieler §f" + player.getName() + "§7 ist gestorben");
                player.playSound(player.getLocation(), Sound.NOTE_BASS, 1, 1);
            }

            player.getInventory().setArmorContents(null);
            player.getInventory().clear();

            player.getInventory().setItem(7, PlayerManager.SPECTATOR);
            player.getInventory().setItem(8, InventoryTriggerListener.QUIT_ITEM);

            CoreSystem.getInstance().createActionBar()
                    .message("§c§oDu bist gestorben")
                    .send(player);

            for (CorePlayer all : CoreSystem.getInstance().getOnlineCorePlayers()) {
                all.getScoreboard().getObjective(DisplaySlot.SIDEBAR).reload();
            }
        }

        player.spigot().respawn();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player player = e.getPlayer();
        if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
            e.setRespawnLocation(CoreSystem.getInstance().getWorldManager().getWorld(OneAttack.getInstance().getGameConfig().parseConfig().getLobby()).getLocation("spawn"));
        } else {
            e.setRespawnLocation(CoreSystem.getInstance().getWorldManager().getWorld(OneAttack.getInstance().getGameConfig().parseConfig().getGameWorld()).getLocation("game.spectator"));
        }
    }
}
