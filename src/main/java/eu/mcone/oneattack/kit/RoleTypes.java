package eu.mcone.oneattack.kit;

import lombok.Getter;

@Getter
public enum RoleTypes {

    ATTACKER("Angreifer"),
    DEFENDER("Verteidiger");

    private final String name;

    RoleTypes(String name) {
        this.name = name;
    }
}
