package eu.mcone.oneattack.listener;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.state.IngameState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.spigotmc.ActivationRange;

import java.util.ArrayList;

public class PlayerMoveListener implements Listener {

    private final ArrayList<Player> isOutside = new ArrayList<>();

    @EventHandler
    public void on(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(p);

        if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof IngameState) {
            if (!OneAttack.getInstance().getGadgetHandler().getIsPreparing().contains(p)) {
                if (p.getLocation().add(0, +2, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +3, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +4, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +5, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +6, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +7, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +8, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +9, 0).getBlock().getType() == Material.AIR &&
                        p.getLocation().add(0, +10, 0).getBlock().getType() == Material.AIR
                ) {
                    if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                        if (!isOutside.contains(p)) {
                            OneAttack.getInstance().getMessenger().send(p, "§cAls §4Verteidiger §cdarfst du dich nicht §4nach draußen §cbegeben!");
                            p.playSound(p.getLocation(), Sound.NOTE_PLING, 1, 1);
                            isOutside.add(p);
                            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                if (isOutside.contains(p)) {
                                    CoreSystem.getInstance().createTitle().fadeOut(1).fadeIn(1).stay(2).title("§4Du stribt in").subTitle("§c3 Sekunden").send(p);
                                }
                                Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                    if (isOutside.contains(p)) {
                                        CoreSystem.getInstance().createTitle().fadeOut(1).fadeIn(1).stay(2).title("§4Du stribt in").subTitle("§c2 Sekunden").send(p);
                                    }
                                    Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                        if (isOutside.contains(p)) {
                                            CoreSystem.getInstance().createTitle().fadeOut(1).fadeIn(1).stay(2).title("§4Du stribt in").subTitle("§ceiner Sekunden").send(p);
                                        }
                                        Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {
                                            if (isOutside.contains(p)) {
                                                p.damage(20F);
                                            }
                                        }, 20L);
                                    }, 19L);
                                }, 19L);
                            }, 12L);
                        }
                    }
                } else {
                    if (isOutside.contains(p)) {
                        isOutside.remove(p);
                        p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE, 1, 1);
                        OneAttack.getInstance().getMessenger().send(p, "§cDer Vorgang wurde abgebrochen, weil du reingegangen bist!");
                    }
                }
            }
        }
    }
}
