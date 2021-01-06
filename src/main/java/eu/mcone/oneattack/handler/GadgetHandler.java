package eu.mcone.oneattack.handler;

import eu.mcone.coresystem.api.bukkit.CoreSystem;
import eu.mcone.coresystem.api.bukkit.hologram.Hologram;
import eu.mcone.coresystem.api.bukkit.hologram.HologramData;
import eu.mcone.coresystem.api.bukkit.world.CoreLocation;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;

@Getter
public class GadgetHandler {

    private final ArrayList<Player> isPreparing = new ArrayList<>();

    private final HashSet<Player> hasDefuser = new HashSet<>();
    private final HashSet<Player> isDefusing = new HashSet<>();
    private final HashSet<Location> trapLocations = new HashSet<>();
    private Hologram hologram;

    public void createDefuserHolo(Player player) {
        hologram = CoreSystem.getInstance().getHologramManager().addHologram(
                new HologramData(
                        "defuser",
                        new String[]{"§fEntschärfer"},
                        new CoreLocation(player.getLocation())
                )
        );
    }

}
