package eu.mcone.oneattack.handler;

import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.kit.RoleTypes;
import lombok.Getter;
import org.bukkit.Material;

@Getter
public enum VoteLocationTypes {

    MAIN(RoleTypes.ATTACKER, Material.GRASS, OneAttack.getInstance().getVoteHandler().getMainAmount(), "Haupt Eingang","§eSpawnOrt | §fHaupt Eingang"),
    GERAGE(RoleTypes.ATTACKER, Material.STONE, OneAttack.getInstance().getVoteHandler().getGerageAmount(),"Geragen Eingang","§eSpawnOrt | §fGeragen Tor"),

    KITCHEN_OFFICE(RoleTypes.DEFENDER, Material.FURNACE, OneAttack.getInstance().getVoteHandler().getGerageAmount(),"Büro, Küche","§eBombenOrt | §fKüche, Büro"),
    GERAGE_ROOM(RoleTypes.DEFENDER, Material.FURNACE, OneAttack.getInstance().getVoteHandler().getGerageRoomAmount(), "Geragen Raum","§eBombenOrt | §fKüche, Büro"),
    CHILDREEN_ROOM(RoleTypes.DEFENDER, Material.WOOD, OneAttack.getInstance().getVoteHandler().getChildreenRoomAmount(), "Kinder, Elternschlafzimmer","§eBombenOrt | §fKinder, Elternschlafzimmer");


    private final RoleTypes roleTypes;
    private final Material material;
    private final Integer voteAmount;
    private final String name;
    private final String displayName;

    VoteLocationTypes(RoleTypes role, Material material, Integer voteAmount, String name, String displayName) {
        this.roleTypes = role;
        this.material = material;
        this.voteAmount = voteAmount;
        this.name = name;
        this.displayName = name;
    }

}
