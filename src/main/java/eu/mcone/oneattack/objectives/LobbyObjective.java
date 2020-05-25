package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import org.bukkit.Bukkit;

public class LobbyObjective extends eu.mcone.gameapi.api.scoreboard.LobbyObjective {

    @Override
    protected void onRegister(CorePlayer corePlayer) {
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        setScore(3, "");
        setScore(2, "§8» §7Wartende Spieler:");
        onReload(corePlayer);
    }

    @Override
    protected void onReload(CorePlayer corePlayer) {
        setScore(1, "§f  " + Bukkit.getOnlinePlayers().size());
    }

}
