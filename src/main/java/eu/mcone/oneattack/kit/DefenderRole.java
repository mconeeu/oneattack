package eu.mcone.oneattack.kit;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.kit.Kit;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum DefenderRole {

    DEFAULT_ROLE("§fStandard Rolle", Role.DEFAULT_DEFENDS, new ItemBuilder(Material.IRON_PICKAXE,1).displayName("§fStandard Rolle")
            .lore("",
            "§7§oMit dieser Rolle erhälst du:",
            "§8» §6Keinen Vorteile",
            "",
            "§7Kosten: §f0 Coins",
            "§c§oDu hast dieses Kit immer!"
    ).create());


    @Getter
    private final String name;
    @Getter
    private final Kit kit;
    @Getter
    private final ItemStack item;

    DefenderRole(String name, Kit kit, ItemStack item) {
        this.name = name;
        this.item = item;
        this.kit = kit;
    }
}
