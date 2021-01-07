package eu.mcone.oneattack.handler;

import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.kit.RoleTypes;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;

@Getter
public enum VoteLocationTypes {

    MAIN(RoleTypes.ATTACKER, Material.GRASS,"Haupt Eingang","attacker.spawn.bomb","§eSpawnOrt | §fHaupt Eingang"),
    GERAGE(RoleTypes.ATTACKER, Material.STONE, "Geragen Eingang","attacker.spawn.bomb","§eSpawnOrt | §fGeragen Tor"),

    KITCHEN_OFFICE(RoleTypes.DEFENDER, Material.FURNACE,"defender.spawn.kitchen","Büro, Küche","§eBombenOrt | §fKüche, Büro"),
    GERAGE_ROOM(RoleTypes.DEFENDER, Material.FURNACE,"Geragen Raum","defender.spawn.gerage","§eBombenOrt | §fKüche, Büro"),
    CHILDREEN_ROOM(RoleTypes.DEFENDER, Material.WOOD, "Kinder, Elternschlafzimmer","defender.spawn.thirdfloor","§eBombenOrt | §fKinder, Elternschlafzimmer");


    private final RoleTypes roleTypes;
    private final Material material;
    private final String name;
    private final String location;
    private final String displayName;

    VoteLocationTypes(RoleTypes role, Material material, String name, String location, String displayName) {
        this.roleTypes = role;
        this.material = material;
        this.name = name;
        this.location = location;
        this.displayName = displayName;
    }

}
