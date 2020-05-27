package eu.mcone.oneattack.objectives;

import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.player.GamePlayerState;
import eu.mcone.oneattack.OneAttack;

public class InGameObjective extends eu.mcone.gameapi.api.scoreboard.InGameObjective {

    @Override
    protected void onRegister(CorePlayer corePlayer) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setDisplayName("§7§l⚔ §b§l§nOneAttack");

        onReload(corePlayer);

        setScore(12, "");
        setScore(11, "§8» §7Team:");
        setScore(10, "   §f§l" + gamePlayer.getTeam().getLabel());
        setScore(9, "");
        setScore(8, "§8» §7Lebene Spieler:");
        setScore(6, "");
        setScore(5, "§8» §7Kills:");
        setScore(3, "");
        setScore(2, "§8» §7Kit:");
        setScore(1, "   §f§l" + gamePlayer.getCurrentKit().getName());
    }

    @Override
    protected void onReload(CorePlayer corePlayer) {
        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(corePlayer.getUuid());
        setScore(1, "   §f§l" + gamePlayer.getCurrentKit().getName());
        setScore(4, "   §f§l" + gamePlayer.getRoundKills());
        setScore(7, "   §f§l" + OneAttack.getInstance().getPlayerManager().getPlayers(GamePlayerState.PLAYING).size());
    }
}
