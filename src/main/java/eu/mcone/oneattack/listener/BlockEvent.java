package eu.mcone.oneattack.listener;

import com.mongodb.client.model.FindOneAndReplaceOptions;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.gadgets.Items;
import eu.mcone.oneattack.state.EndState;
import eu.mcone.oneattack.state.LobbyState;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockEvent implements Listener {

    @EventHandler
    public void on(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
            return;
        }

        if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
            e.setCancelled(true);
        } else {
            Block block = e.getBlock();
            Material blockType = block.getType();
            byte blockData = block.getData();
            e.setCancelled(true);


            if (block.getType().equals(Material.WOOD_PLATE)) {
                e.setCancelled(false);
                Items.trapLocations.add(block.getLocation());
            } else if (block.getType().equals(Material.STONE)) {
                e.setCancelled(false);
            }

        }
    }


    @EventHandler
    public void on(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (p.getGameMode().equals(GameMode.CREATIVE)) {
            e.setCancelled(false);
            return;
        }

        Block block = e.getBlock();
        Material blockType = block.getType();
        byte blockData = block.getData();

        if (Items.trapLocations.contains(e.getBlock().getLocation())) {
            e.setCancelled(false);
        }

        if (OneAttack.getInstance().getGameStateManager().getRunning() instanceof LobbyState || OneAttack.getInstance().getGameStateManager().getRunning() instanceof EndState) {
            e.setCancelled(true);
        } else {
            if (blockType.equals(Material.STONE) && blockData == (byte) 5 || blockData == (byte) 4) {
                if (p.getItemInHand().getType().equals(Items.PUSHER_PICKAXE.getItem().getType()) || p.getItemInHand().getType().equals(Items.WALL_PICKAXE.getItem().getType()) || p.getItemInHand().getType().equals(Items.FAST_PICKAXE.getItem().getType())) {
                    e.setCancelled(false);
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getDrops().clear();
                    if (p.getItemInHand().getAmount() == 1) {
                        p.getInventory().removeItem(p.getInventory().getItemInHand());
                    } else {
                        p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                    }
                } else {
                    e.setCancelled(true);
                }
            } else if (blockType.equals(Material.STONE) && blockData == (byte) 6) {
                if (p.getItemInHand().getType().equals(Items.WALL_PICKAXE.getItem().getType())) {
                    e.setCancelled(false);
                    e.getBlock().setType(Material.AIR);
                    e.getBlock().getDrops().clear();
                    if (p.getItemInHand().getAmount() == 1) {
                        p.getInventory().removeItem(p.getInventory().getItemInHand());
                    } else {
                        p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
                    }
                } else {
                    e.setCancelled(true);
                }
            } else {
                e.setCancelled(true);
            }
        }
    }
}
