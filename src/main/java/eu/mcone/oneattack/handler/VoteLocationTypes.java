package eu.mcone.oneattack.handler;

import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.kit.RoleTypes;
import lombok.Getter;
import org.bukkit.Material;

import java.util.ArrayList;

@Getter
public enum VoteLocationTypes {

    MAIN(RoleTypes.ATTACKER, Material.GRASS,"Haupt Eingang","§eSpawnOrt | §fHaupt Eingang"),
    GERAGE(RoleTypes.ATTACKER, Material.STONE, "Geragen Eingang","§eSpawnOrt | §fGeragen Tor"),

    KITCHEN_OFFICE(RoleTypes.DEFENDER, Material.FURNACE,"Büro, Küche","§eBombenOrt | §fKüche, Büro"),
    GERAGE_ROOM(RoleTypes.DEFENDER, Material.FURNACE,"Geragen Raum","§eBombenOrt | §fKüche, Büro"),
    CHILDREEN_ROOM(RoleTypes.DEFENDER, Material.WOOD, "Kinder, Elternschlafzimmer","§eBombenOrt | §fKinder, Elternschlafzimmer");


    private final RoleTypes roleTypes;
    private final Material material;
    private final String name;
    private final String displayName;

    VoteLocationTypes(RoleTypes role, Material material, String name, String displayName) {
        this.roleTypes = role;
        this.material = material;
        this.name = name;
        this.displayName = displayName;
    }

}
