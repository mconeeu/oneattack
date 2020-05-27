package eu.mcone.oneattack.kit;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.kit.Kit;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum AttackerRole {

    DEFAULT_ROLE("Standard Rolle", Role.DEFAULT_ATTACKER, new ItemBuilder(Material.IRON_PICKAXE, 1).displayName("§fStandard Rolle")
            .lore("",
                    "§7§oMit dieser Rolle erhälst du:",
                    "§8» §6Keinen Vorteile",
                    "",
                    "§7Kosten: §f0 Coins",
                    "§c§oDu hast dieses Kit immer!"
            ).create()),

    PUSHER("Pusher Rolle", Role.PUSHER, new ItemBuilder(Material.IRON_SWORD, 1).displayName("§fPusher Rolle")
            .lore("",
                    "§7§oMit dieser Rolle erhälst du:",
                    "§8» §64 Spitzhacke die schneller nicht",
                    "§8» §6verstärkte Wände öffnet",
                    "",
                    "§7Kosten: §f50 Coins"
            ).create()),

    SAVER("§fSicherer Rolle", Role.SAVER, new ItemBuilder(Material.GOLD_CHESTPLATE, 1).displayName("§fSicherer Rolle")
            .displayName("§fSicherer-Rolle")
            .lore(
                    "",
                    "§7§oMit dieser Rolle erhälst du:",
                    "§8» §64 Spitzhacken die",
                    "§8» §64 verstärkte Wände öffnet",
                    "",
                    "§7Kosten: §f25 Coins"
            ).create());


    @Getter
    private final String name;
    @Getter
    private final Kit kit;
    @Getter
    private final ItemStack item;

    AttackerRole(String name, Kit kit, ItemStack item) {
        this.name = name;
        this.item = item;
        this.kit = kit;
    }
}
