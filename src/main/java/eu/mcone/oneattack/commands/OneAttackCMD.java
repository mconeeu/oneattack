package eu.mcone.oneattack.commands;

import eu.mcone.coresystem.api.bukkit.command.CoreCommand;
import eu.mcone.coresystem.api.bukkit.command.CorePlayerCommand;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class OneAttackCMD extends CoreCommand {

    public OneAttackCMD() {
        super("oneattack");
    }

    @Override
    public boolean onCommand(CommandSender commandSender, String[] strings) {
        Player player = (Player) commandSender;
        eu.mcone.oneattack.OneAttack.getInstance().getMessenger().send(player, "§8§m---------- §r§b§lMCONE-OneAttack §8§m----------");
        eu.mcone.oneattack.OneAttack.getInstance().getMessenger().send(player, "§7Entwickelt von §fMarvio");
        eu.mcone.oneattack.OneAttack.getInstance().getMessenger().send(player, "§r");
        eu.mcone.oneattack.OneAttack.getInstance().getMessenger().send(player, "§7§oWir bemühen uns darum alle Systeme und Spielmodi so effizient wie möglich zu gestalten.");
        eu.mcone.oneattack.OneAttack.getInstance().getMessenger().send(player, "§7§oDeshalb sind auch alle von uns verwendeten Plugins ausschließlich selbst entwickelt!");
        eu.mcone.oneattack.OneAttack.getInstance().getMessenger().send(player, "§8§m---------- §r§b§lMCONE-OneAttack §8§m----------");
        player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1, 1);


        return false;
    }
}
