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
import eu.mcone.oneattack.listener.PlayerMoveListener;
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

            CorePlayer corePlayer = CoreSystem.getInstance().getCorePlayer(player.getUniqueId());

            corePlayer.getScoreboard().setNewObjective(new InGameObjective());

            PlayerMoveListener.isPreparing.addAll(Bukkit.getOnlinePlayers());
            OneAttack.getInstance().getTeamManager().setTeamsForRemainingPlayersBalanced();

            for (GamePlayer gameplayers : OneAttack.getInstance().getOnlineGamePlayers()) {
                if (gameplayers.getTeam().getName().equals(OneAttack.getInstance().getAttackTeam().getName())) {
                    gameplayers.setKit(Role.DEFAULT_ATTACKER);
                } else {
                    gameplayers.setKit(Role.DEFAULT_DEFENDS);
                }
            }


            player.getInventory().clear();
            player.getInventory().setArmorContents(null);

        }
    }
}

