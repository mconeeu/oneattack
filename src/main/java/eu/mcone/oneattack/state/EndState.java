package eu.mcone.oneattack.state;

import eu.mcone.gameapi.api.event.gamestate.GameStateStopEvent;
import eu.mcone.gameapi.api.gamestate.common.EndGameState;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.objectives.EndObjective;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class EndState extends EndGameState {

    static {
        setObjective(EndObjective.class);
    }

    @Override
    public void onStop(GameStateStopEvent event) {
        super.onStop(event);
        for (Player player : Bukkit.getOnlinePlayers()) {
            OneAttack.getInstance().getMessenger().send(player, "§7Der Server startet nun neu...");
        }

        Bukkit.getServer().reload();
    }
}
