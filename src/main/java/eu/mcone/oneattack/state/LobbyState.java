package eu.mcone.oneattack.state;

import eu.mcone.coresystem.api.bukkit.CorePlugin;
import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.player.CorePlayer;
import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.LobbyGameState;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.gameapi.api.player.GamePlayerState;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.objectives.InGameObjective;
import eu.mcone.oneattack.objectives.LobbyObjective;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LobbyState extends LobbyGameState {

    static {
        setObjective(LobbyObjective.class);
    }

    @Override
    public void onStop(GameStateStopEvent event) {
        for (Player player : OneAttack.getInstance().getPlayerManager().getPlayers(GamePlayerState.PLAYING)) {

            GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);
            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player.getUniqueId());


            corePlayer.getScoreboard().setNewObjective(new InGameObjective());
            player.getInventory().clear();
            player.getInventory().setArmorContents(null);
        }
    }

    @Override
    public void onCountdownSecond(CorePlugin plugin, int second) {
        super.onCountdownSecond(plugin, second);

        if (second == 5) {
            OneAttack.getInstance().getTeamManager().setTeamsForRemainingPlayersBalanced();

            for (GamePlayer gamePlayer : OneAttack.getInstance().getOnlineGamePlayers()) {
                if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
                    gamePlayer.setKit(Role.DEFAULT_ATTACKER);
                } else if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
                    gamePlayer.setKit(Role.DEFAULT_DEFENDS);
                }
            }
        }
    }
}

