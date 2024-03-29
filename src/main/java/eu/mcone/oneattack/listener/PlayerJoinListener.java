package eu.mcone.oneattack.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.gameapi.api.event.player.GamePlayerLoadedEvent;
import eu.mcone.gameapi.api.player.PlayerManager;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffectType;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(GamePlayerLoadedEvent e) {
        Player player = e.getBukkitPlayer();

        player.setGameMode(GameMode.SURVIVAL);

        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setExp(0);
        player.setFlying(false);
        player.setAllowFlight(false);
        player.removePotionEffect(PotionEffectType.SLOW);


        if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState) {
                CoreSystem.getInstance().getWorldManager().getWorld(OneAttack.getInstance().getGameConfig().parseConfig().getLobby()).teleport(player, "spawn");
        } else {
            player.getInventory().setItem(7, PlayerManager.SPECTATOR);
        }

    }
}
