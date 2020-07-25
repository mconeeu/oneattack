package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.scoreboard.LobbyObjective;
import eu.mcone.oneattack.OneAttack;

public class EndObjective extends LobbyObjective {

    @Override
    protected void onLobbyRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        entry.setScore(6, "");
        entry.setScore(5, "§8» §7Team:");
        entry.setScore(4, "   §f§l" + gamePlayer.getTeam().getLabel());
        entry.setScore(3, "");
        entry.setScore(2, "§8» §7Kills:");
        onReload(corePlayer, entry);
    }

    @Override
    protected void onLobbyReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        entry.setScore(1, "   §f§l" + gamePlayer.getRoundKills());
    }
}

