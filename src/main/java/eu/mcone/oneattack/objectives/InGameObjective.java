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
    protected void onInGameRegister(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onRegister(corePlayer, entry);
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        onReload(corePlayer, entry);

        entry.setScore(9, "");
        entry.setScore(8, "§8» §7Team:");
        entry.setScore(7, "   §f§l" + gamePlayer.getTeam().getLabel());
        entry.setScore(6, "");
        entry.setScore(5, "§8» §7Zeit:");
        entry.setScore(4, "null");
        entry.setScore(3, "");
        entry.setScore(2, "§8» §7Kills:");
        entry.setScore(0, "");
    }

    @Override
    protected void onInGameReload(CorePlayer corePlayer, CoreSidebarObjectiveEntry entry) {
        super.onReload(corePlayer, entry);
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        entry.setScore(1, "   §f§l" + gamePlayer.getRoundKills());
    }
}
