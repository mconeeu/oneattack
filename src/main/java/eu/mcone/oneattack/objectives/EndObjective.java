package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.scoreboard.LobbyObjective;
import eu.mcone.oneattack.OneAttack;

public class EndObjective extends LobbyObjective {

    @Override
    protected void onRegister(CorePlayer corePlayer) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        setScore(6, "");
        setScore(5, "§8» §7Team:");
        setScore(4, "   §f§l" + gamePlayer.getTeam().getLabel());
        setScore(3, "");
        setScore(2, "§8» §7Kills:");
        onReload(corePlayer);
    }

    @Override
    protected void onReload(CorePlayer corePlayer) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setScore(1, "   §f§l" + gamePlayer.getRoundKills());
    }
}

