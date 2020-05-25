package eu.mcone.oneattack.kit;

import eu.mcone.coresystem.api.bukkit.inventory.PlayerInventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.oneattack.gadgets.Items;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public class Role {


    /*    ATTACKER         */

    public static final eu.mcone.gameapi.api.kit.Kit DEFAULT_ATTACKER = new eu.mcone.gameapi.api.kit.Kit(
            "Standard-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .displayName("§7Standard-Rolle")
                    .lore(
                            "",
                            "§7§oMit dieser Rolle erhälst du:",
                            "§8» §6Keinen Vorteile",
                            "",
                            "§7Kosten: §f0 Coins",
                            "§c§oDu hast dieses Kit immer!"
                    )
                    .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .create(),
            new HashMap<Integer, ItemStack>() {{
                put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD)
                        .displayName("§8» §fHolz Schwert")
                        .unbreakable(true)
                        .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
                        .create()
                );
                put(PlayerInventorySlot.HOTBAR_SLOT_2, Items.PUSHER_PICKAXE.getItem());
            }},
            0
    );

    public static final eu.mcone.gameapi.api.kit.Kit DEFAULT_DEFENDS = new eu.mcone.gameapi.api.kit.Kit(
            "Standard-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .displayName("§7Standard-Rolle")
                    .lore(
                            "",
                            "§7§oMit dieser Rolle erhälst du:",
                            "§8» §6Keinen Vorteile",
                            "",
                            "§7Kosten: §f0 Coins",
                            "§c§oDu hast dieses Kit immer!"
                    )
                    .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .create(),
            new HashMap<Integer, ItemStack>() {{
                put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD)
                        .displayName("§8» §fHolz Schwert")
                        .unbreakable(true)
                        .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
                        .create()
                );
                put(PlayerInventorySlot.HOTBAR_SLOT_2, Items.PUSHER_PICKAXE.getItem());
                put(PlayerInventorySlot.HOTBAR_SLOT_3, Items.REINFORCE_HOE.getItem());
            }},
            0
    );

    public static final eu.mcone.gameapi.api.kit.Kit PUSHER = new eu.mcone.gameapi.api.kit.Kit(
            "Pusher-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .displayName("§7Pusher-Rolle")
                    .lore(
                            "",
                            "§7§oMit dieser Rolle erhälst du:",
                            "§8» §6Eine Hacke die nicht",
                            "§8» §6verstärkte Wände öffnet",
                            "",
                            "§7Kosten: §f50 Coins"
                    )
                    .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .create(),
            new HashMap<Integer, ItemStack>() {{
                put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD)
                        .displayName("§8» §fHolz Schwert")
                        .unbreakable(true)
                        .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
                        .create()
                );
                put(PlayerInventorySlot.HOTBAR_SLOT_2, Items.WALL_PICKAXE.getItem()
                );
            }},
            50
    );


}
