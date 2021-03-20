/*
 *
 *   David.R | RoyalByteNET (c) 2019.
 *
 *    ______  _______ _    _ _____ ______     ______
 *    |     \ |_____|  \  /    |   |     \   |_____/
 *    |_____/ |     |   \/   __|__ |_____/ . |    \_
 *
 *
 *
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.utils.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_setwarp implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(p.hasPermission("skypvp.setwarp")){
                if(args.length == 1){
                    Manager.setLocation(p, args[0]);
                    p.sendMessage(Data.prefix + "§aDu hast erfolgreich den Warp-Punkt §e"+ args[0]+ "§a gesetzt");
                } else {
                p.sendMessage(Data.prefix +"§c/setwarp <Name>");
                }
            } else {
                p.sendMessage(Data.prefix + "§cKeine Rechte");
            }
        }

        return true;
    }


}
