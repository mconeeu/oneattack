package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.coresystem.api.bukkit.scoreboard.CoreSidebarObjectiveEntry;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.player.GamePlayerState;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.state.IngameState;

public class InGameObjective extends eu.mcone.gameapi.api.scoreboard.InGameObjective {

    public InGameObjective() {
    }

    @Override
    protected void onInGameRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry coreSidebarObjectiveEntry) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        onReload(corePlayer, coreSidebarObjectiveEntry);

        coreSidebarObjectiveEntry.setScore(9, "");
        coreSidebarObjectiveEntry.setScore(8, "§8» §7Team:");
        coreSidebarObjectiveEntry.setScore(7, "   §f§l" + gamePlayer.getTeam().getLabel());
        coreSidebarObjectiveEntry.setScore(6, "");
        coreSidebarObjectiveEntry.setScore(5, "§8» §7Zeit:");
        coreSidebarObjectiveEntry.setScore(4, "null");
        coreSidebarObjectiveEntry.setScore(3, "");
        coreSidebarObjectiveEntry.setScore(2, "§8» §7Kills:");
        coreSidebarObjectiveEntry.setScore(0, "");
    }

    @Override
    protected void onInGameReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry coreSidebarObjectiveEntry) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        coreSidebarObjectiveEntry.setScore(1, "   §f§l" + gamePlayer.getRoundKills());
    }
}
