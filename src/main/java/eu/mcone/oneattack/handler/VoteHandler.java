package eu.mcone.oneattack.handler;

import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.inventorys.AttackKitInventory;
import eu.mcone.oneattack.inventorys.DefendKitInventory;
import eu.mcone.oneattack.inventorys.VoteSpawnLocationInventory;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

@Getter
public class VoteHandler {

    private final ArrayList<Player> votedPlayer = new ArrayList<>();
    public final HashMap<Player, VoteLocationTypes> voteHashMap = new HashMap<>();
    private int worldType = 0;

    /*  TELEPORT DEFENDS TO THE SPAWNLOCATION THAT THEY CHOOSED */
    public String mathDefenderSpawnLocation() {

     //TODO from the voteHashMap checkout the Location

        int childreen = Collections.frequency(new ArrayList<>(voteHashMap.values()), VoteLocationTypes.CHILDREEN_ROOM);
        int office = Collections.frequency(new ArrayList<>(voteHashMap.values()), VoteLocationTypes.KITCHEN_OFFICE);
        int gerage = Collections.frequency(new ArrayList<>(voteHashMap.values()), VoteLocationTypes.GERAGE_ROOM);

        int max = childreen > gerage? (Math.max(childreen, office)): (Math.max(gerage, office));

        if (max == childreen) {
            worldType = 1;
            return VoteLocationTypes.GERAGE_ROOM.getLocation();
        } else if (max == office) {
            worldType = 2;
            return VoteLocationTypes.KITCHEN_OFFICE.getLocation();
        } else {
            worldType = 3;
            return VoteLocationTypes.CHILDREEN_ROOM.getLocation();
        }

    }

    /*  TELEPORT ATTACKERS TO THE SPAWNLOCATIONS FROM THE DEFENDS LOCATIONS TO HIS VOTE LOCATION */
    public String mathAttackerSpawnLocation() {

        //TODO from the voteHashMap checkout the Location

        int main = Collections.frequency(new ArrayList<>(voteHashMap.values()), VoteLocationTypes.MAIN);
        int gerage = Collections.frequency(new ArrayList<>(voteHashMap.values()), VoteLocationTypes.GERAGE);

        int max = Math.max(main,gerage);

        if (max == main) {
            return VoteLocationTypes.GERAGE.getLocation() + worldType + "." + 2;
        } else {
            return VoteLocationTypes.GERAGE.getLocation() + worldType + "." + 1;
        }

    }

    /* TELEPORT TO ALL LOCATIONS */
    public void teleportPlayerToLocation(GamePlayer gamePlayer) {
        System.out.println("§aTeleport to vote spawnlocations..");
        if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
            gamePlayer.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation(mathAttackerSpawnLocation()));
        } else if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
            gamePlayer.bukkit().teleport(OneAttack.getInstance().getGameWorld().getLocation(mathDefenderSpawnLocation()));
        }
    }

    /* CHOOSE SPAWN INV */
    public void openSpawnInv(GamePlayer gamePlayer) {
        new VoteSpawnLocationInventory(gamePlayer.bukkit());
    }

    /* CHOOSE KIT INV */
    public void openKitInv(GamePlayer gamePlayer) {
        if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getDefenderTeam().getName())) {
            new DefendKitInventory(gamePlayer.bukkit());
        } else if (gamePlayer.getTeam().getName().equalsIgnoreCase(OneAttack.getInstance().getAttackTeam().getName())) {
            new AttackKitInventory(gamePlayer.bukkit());
        }
    }

}
