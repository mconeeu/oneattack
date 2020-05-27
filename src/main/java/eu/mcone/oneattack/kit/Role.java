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

    public static final eu.mcone.gameapi.api.kit.Kit PUSHER = new eu.mcone.gameapi.api.kit.Kit(
            "Pusher-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .create(),
            new HashMap<Integer, ItemStack>() {{
                put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD)
                        .displayName("§8» §fHolz Schwert")
                        .unbreakable(true)
                        .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
                        .create()
                );
                put(PlayerInventorySlot.HOTBAR_SLOT_2, Items.FAST_PICKAXE.getItem()
                );
            }},
            50
    );

    public static final eu.mcone.gameapi.api.kit.Kit SAVER = new eu.mcone.gameapi.api.kit.Kit(
            "Sicherer-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .itemFlags(ItemFlag.HIDE_ATTRIBUTES)
                    .create(),
            new HashMap<Integer, ItemStack>() {{
                put(PlayerInventorySlot.HOTBAR_SLOT_1, new ItemBuilder(Material.WOOD_SWORD)
                        .displayName("§8» §fHolz Schwert")
                        .unbreakable(true)
                        .itemFlags(ItemFlag.HIDE_UNBREAKABLE)
                        .create()
                );
                put(PlayerInventorySlot.HOTBAR_SLOT_2, Items.WALL_PICKAXE.getItem());
            }},
            25
    );




    /*                      */


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

    public static final eu.mcone.gameapi.api.kit.Kit TRAPPER = new eu.mcone.gameapi.api.kit.Kit(
            "Fallenläger-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .displayName("§7Fallenläger-Rolle")
                    .lore(
                            "",
                            "§7§oMit dieser Rolle erhälst du:",
                            "§8» §64 Bärenfallen die du",
                            "§8» §6platzieren kannst",
                            "",
                            "§7Kosten: §f100 Coins"
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
                put(PlayerInventorySlot.HOTBAR_SLOT_4, Items.TRAPS.getItem());
            }},
            100
    );

    public static final eu.mcone.gameapi.api.kit.Kit BARRICADER = new eu.mcone.gameapi.api.kit.Kit(
            "Barrikader-Rolle",
            new ItemBuilder(Material.WOOD_SWORD)
                    .displayName("§fBarrikader-Rolle")
                    .lore(
                            "",
                            "§7§oMit dieser Rolle erhälst du:",
                            "§8» §45 Steine die angreifern",
                            "§8» §4hindern könnte",
                            "",
                            "§7Kosten: §f25 Coins"
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
                put(PlayerInventorySlot.HOTBAR_SLOT_4, Items.BARRICADE.getItem());
            }},
            25
    );


}
