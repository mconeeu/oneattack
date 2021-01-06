package eu.mcone.oneattack.inventorys;

import eu.mcone.coresystem.api.bukkit.inventory.CoreInventory;
import eu.mcone.coresystem.api.bukkit.inventory.InventoryOption;
import eu.mcone.coresystem.api.bukkit.inventory.InventorySlot;
import eu.mcone.coresystem.api.bukkit.item.ItemBuilder;
import eu.mcone.gameapi.api.player.GamePlayer;
import eu.mcone.oneattack.OneAttack;
import eu.mcone.oneattack.handler.VoteLocationTypes;
import eu.mcone.oneattack.kit.RoleTypes;
import org.bukkit.entity.Player;

public class VoteSpawnLocationInventory extends CoreInventory {

    public VoteSpawnLocationInventory(Player player) {
        super("§fVote dein Ort", player, InventorySlot.ROW_3, InventoryOption.FILL_EMPTY_SLOTS);

        GamePlayer gamePlayer = OneAttack.getInstance().getGamePlayer(player);

        int slot = InventorySlot.ROW_2_SLOT_2;

        for (VoteLocationTypes types : VoteLocationTypes.values()) {
            if (gamePlayer.getTeam().equals(OneAttack.getInstance().getAttackTeam()) && types.getRoleTypes().equals(RoleTypes.ATTACKER)) {
                setItem(slot, new ItemBuilder(types.getMaterial(),1).displayName(types.getDisplayName()).lore("§7§oVote dafür wo", "§7§oihr als Trupp spawnt", "", "§8» §f§nLinksklick§8 | §7§oVoten").create(), e -> {
                    OneAttack.getInstance().getVoteHandler().getVotedPlayer().add(player);

                    //TODO add integer vote amount..
                });


                slot+=2;
            }
        }
    }
}
