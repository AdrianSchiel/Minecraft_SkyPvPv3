package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_stack implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player)sender;
            if(p.hasPermission("SkyPvP.stack")) {
                if(args.length == 0) {
                    p.getItemInHand().setAmount(64);
                    p.sendMessage(Data.prefix + "§6Du hast das §6Item §3gestackt§6.");
                }else {
                    p.sendMessage(Data.prefix + "§cUse /stack");
                }
            }else {
                p.sendMessage(Data.noperm);
            }

        }else {
            sender.sendMessage(Data.must_a_player);
        }
        return true;
    }

}
