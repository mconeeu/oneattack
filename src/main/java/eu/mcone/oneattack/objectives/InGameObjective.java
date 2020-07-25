package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.player.GamePlayerState;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.state.IngameState;

public class InGameObjective extends eu.mcone.gameapi.api.scoreboard.InGameObjective {

    public InGameObjective() {
    }

    @Override
    protected void onRegister(CorePlayer corePlayer) {
        super.onRegister(corePlayer);
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        onReload(corePlayer);

        setScore(9, "");
        setScore(8, "§8» §7Team:");
        setScore(7, "   §f§l" + gamePlayer.getTeam().getLabel());
        setScore(6, "");
        setScore(5, "§8» §7Zeit:");
        setScore(4, "null");
        setScore(3, "");
        setScore(2, "§8» §7Kills:");
        setScore(0, "");
    }

    @Override
    protected void onReload(CorePlayer corePlayer) {
        super.onReload(corePlayer);
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setScore(1, "   §f§l" + gamePlayer.getRoundKills());
    }
}
