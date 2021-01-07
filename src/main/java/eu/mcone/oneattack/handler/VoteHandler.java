package eu.mcone.oneattack.handler;

import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.inventorys.AttackKitInventory;
import eu.mcone.oneattack.inventorys.DefendKitInventory;
import eu.mcone.oneattack.inventorys.VoteSpawnLocationInventory;
import eu.mcone.oneattack.kit.RoleTypes;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

@Getter
public class VoteHandler {

    private final ArrayList<Player> votedPlayer = new ArrayList<>();
    public final HashMap<Player, VoteLocationTypes> voteHashMap = new HashMap<>();

    /*  TELEPORT DEFENDS TO THE SPAWNLOCATION THAT THEY CHOOSED */
    public String mathDefenderSpawnLocation() {

     //TODO from the voteHashMap checkout the Location

       /*    if (DefendSpawnLocationInventory.gerage < DefendSpawnLocationInventory.kitchen && DefendSpawnLocationInventory.gerage < DefendSpawnLocationInventory.thirdfloor) {
            System.out.println("§aTeleport defender to defender.spawn.gerage");
            return "defender.spawn.gerage";
        } else if (DefendSpawnLocationInventory.kitchen > DefendSpawnLocationInventory.thirdfloor) {
            System.out.println("§aTeleport defender to defender.spawn.kitchen");
            return "defender.spawn.kitchen";
        } else if (DefendSpawnLocationInventory.kitchen < DefendSpawnLocationInventory.thirdfloor) {
            System.out.println("§aTeleport defender to defender.spawn.thirdfloor");
            return "defender.spawn.thirdfloor";
        } else {
            System.out.println("§aTeleport defender to defender.spawn.gerage because nobody he not choosed!");
            return "defender.spawn.gerage";
        }


        */

        return null;

    }

    /*  TELEPORT ATTACKERS TO THE SPAWNLOCATIONS FROM THE DEFENDS LOCATIONS TO HIS VOTE LOCATION */
    public String mathAttackerSpawnLocation() {

        //TODO from the voteHashMap checkout the Location

        /* if (AttackSpawnLocationInventory.gerage > AttackSpawnLocationInventory.main_entrance) {
            if (mathDefenderSpawnLocation().equals("defender.spawn.gerage")) {
                return "attacker.spawn.bomb1.1";
            } else if (mathDefenderSpawnLocation().equals("defender.spawn.kitchen")) {
                return "attacker.spawn.bomb2.1";
            } else {
                return "attacker.spawn.bomb3.1";
            }
        } else if (AttackSpawnLocationInventory.gerage < AttackSpawnLocationInventory.main_entrance) {
            if (mathDefenderSpawnLocation().equals("defender.spawn.gerage")) {
                return "attacker.spawn.bomb1.2";
            } else if (mathDefenderSpawnLocation().equals("defender.spawn.kitchen")) {
                return "attacker.spawn.bomb2.2";
            } else {
                return "attacker.spawn.bomb3.2";
            }
        } else {
            if (mathDefenderSpawnLocation().equals("defender.spawn.gerage")) {
                return "attacker.spawn.bomb1.1";
            } else if (mathDefenderSpawnLocation().equals("defender.spawn.kitchen")) {
                return "attacker.spawn.bomb2.1";
            } else {
                return "attacker.spawn.bomb3.1";
            }
        }
        */

        return null;

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
