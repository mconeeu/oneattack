package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import org.bukkit.Bukkit;

public class LobbyObjective extends eu.mcone.gameapi.api.scoreboard.LobbyObjective {

    @Override
    protected void onLobbyRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onRegister(corePlayer, entry);
        entry.setTitle("§7§l⚔ §b§l§nOneAttack");

        entry.setScore(3, "");
        entry.setScore(2, "§8» §7Wartende Spieler:");
        onReload(corePlayer, entry);
    }

    @Override
    protected void onLobbyReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onReload(corePlayer, entry);
        entry.setScore(1, "§f  " + Bukkit.getOnlinePlayers().size());
    }
}
