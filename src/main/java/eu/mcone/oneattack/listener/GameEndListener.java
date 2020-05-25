package eu.mcone.oneattack.listener;

import eu.mcone.gameapi.api.event.game.GameDrawEvent;
import eu.mcone.gameapi.api.event.team.TeamWonEvent;
import eu.mcone.gameapi.api.team.Team;
import eu.mcone.oneattack.OneAttack;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameEndListener implements Listener {

    @EventHandler
    public void on(TeamWonEvent e) {
        preparePlayersForEnd(e.getTeam());
    }

    @EventHandler
    public void on(GameDrawEvent e) {
        preparePlayersForEnd(OneAttack.getInstance().getTeamManager().calculateWinnerByKills());
    }

    private static void preparePlayersForEnd(Team team) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.FIREWORK_BLAST, 1, 1);
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_DEATH, 1, 1);

            OneAttack.getInstance().getMessenger().send(player, "§7Team " + team.getLabel() + " §7hat das Spiel gewonnen!");
        }
    }

}
