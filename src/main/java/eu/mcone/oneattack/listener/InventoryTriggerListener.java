package eu.mcone.oneattack.listener;

import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.GameAPI;
import eu.mcone.gameapi.api.backpack.defaults.DefaultCategory;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.inventorys.PlantInventory;
import eu.mcone.oneattack.kit.Role;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import sun.font.DelegatingShape;

public class InventoryTriggerListener implements Listener {

    public static final ItemStack QUIT_ITEM = new ItemBuilder(Material.SLIME_BALL, 1, 0).displayName("§c§lLobby §8» §7§osendet dich zu Lobby zurück").create();

    @EventHandler
    public void on(PlayerInteractEvent e) {
        Player player = e.getPlayer();


        /* TRAPPER KIT */
        if (e.getClickedBlock() != null) {
            if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.WOOD_PLATE && Items.trapLocations.contains(e.getClickedBlock().getLocation())) {
                GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);
                if (gamePlayer.getTeam().equals(OneAttack.getInstance().getAttackTeam())) {
                    e.getClickedBlock().setType(Material.AIR);
                    Items.trapLocations.remove(e.getClickedBlock().getLocation());
                    player.damage(8F);
                    player.playSound(player.getLocation(), Sound.CAT_PURR, 1, 1);
                    OneAttack.getInstance().getMessenger().send(player, "§cDu hast schaden durch eine Bären Falle erhalten!");

                    for (GamePlayer gamePlayers : OneAttack.getInstance().getOnlineGamePlayers()) {
                        if (gamePlayers.getCurrentKit() != null) {
                            if (gamePlayers.getCurrentKit().equals(Role.TRAPPER)) {
                                OneAttack.getInstance().getMessenger().send(gamePlayers.bukkit(), "§cEin Angreifer ist in deiner Bären Falle gelaufen!");
                            }
                        }
                    }
                }
            }
        }

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            ItemStack itemStack = e.getItem();
            if (itemStack == null) {
                return;
            }


            if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState
                    || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
                if (itemStack.getType().equals(QUIT_ITEM.getType())) {
                    player.performCommand("hub");
                } else if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§3§lRucksack §8» §7§oZeige deine gesammelten Items an")) {
                    e.setCancelled(true);
                    OneAttack.getInstance().getBackpackManager().openBackpackInventory(DefaultCategory.GADGET.name(), player);
                } else if (itemStack.getType().equals(Material.COMPASS)) {
                    e.setCancelled(true);
                    OneAttack.getInstance().getPlayerManager().openSpectatorInventory(player);
                }
                e.setCancelled(true);
            } else {
                /* DEFUSER */
                if (itemStack.getType().equals(Items.DEFUSER.getItem().getType())) {
                    e.setCancelled(true);
                    if (player.getLocation().distance(OneAttack.getInstance().getGameWorld().getBlockLocation("bomb-1")) < 3 ||
                            player.getLocation().distance(OneAttack.getInstance().getGameWorld().getBlockLocation("bomb-2")) < 3) {
                        new PlantInventory(player);
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                    } else if (player.getLocation().distance(OneAttack.getInstance().getGameWorld().getBlockLocation("bomb-3")) < 3 ||
                            player.getLocation().distance(OneAttack.getInstance().getGameWorld().getBlockLocation("bomb-4")) < 3) {
                        new PlantInventory(player);
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                    } else if (player.getLocation().distance(OneAttack.getInstance().getGameWorld().getBlockLocation("bomb-5")) < 3 ||
                            player.getLocation().distance(OneAttack.getInstance().getGameWorld().getBlockLocation("bomb-6")) < 3) {
                        new PlantInventory(player);
                        player.playSound(player.getLocation(), Sound.ANVIL_USE, 1, 1);
                    } else {
                        OneAttack.getInstance().getMessenger().send(player, "§4Du musst näher an die Bombe!");
                    }
                    /* REINFORCE */
                } else if (itemStack.getType().equals(Items.REINFORCE_HOE.getItem().getType())) {
                    if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                        byte blockData = e.getClickedBlock().getData();
                        if (e.getClickedBlock().getType().equals(Material.STONE) && blockData == (byte) 5) {
                            Bukkit.getScheduler().runTaskLater(OneAttack.getInstance(), () -> {

                                if (e.getClickedBlock().getType().equals(Material.STONE) && blockData == (byte) 5) {
                                    Block block = e.getClickedBlock();
                                    block.setType(Material.STONE);
                                    block.setData((byte) 6);
                                    player.playSound(player.getLocation(), Sound.DIG_WOOD, 1, 1);
                                }
                            }, 23L);
                            Block block = e.getClickedBlock();
                            block.setType(Material.STONE);
                            player.playSound(player.getLocation(), Sound.DIG_STONE, 1, 1);
                            block.setData((byte) 4);

                            if (player.getItemInHand().getAmount() == 1) {
                                player.getInventory().removeItem(player.getInventory().getItemInHand());
                            } else {
                                player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
                            }

                        }
                    }
                }
            }
        }
    }
}
