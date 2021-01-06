package eu.mcone.oneattack.handler;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@Getter
public class VoteHandler {

    private int mainAmount;
    private int gerageAmount;
    private int officeRoomAmount;
    private int childreenRoomAmount;
    private int gerageRoomAmount;

    private final ArrayList<Player> votedPlayer = new ArrayList<>();

}
