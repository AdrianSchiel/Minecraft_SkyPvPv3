/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.utils.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_delwarp implements CommandExecutor {

    public boolean onCommand(CommandSender cs, Command arg1, String arg2, String[] args)
    {
        Player p = (Player)cs;
        if (p.hasPermission("skypvp.delwarp"))
        {
            if (args.length == 1)
            {
                String warpName = args[0];
                warpName = warpName.toLowerCase();
                if (Manager.isWarpExists(warpName))
                {
                     Manager.removeLocation(warpName);
                    p.sendMessage(Data.prefix +"Du hast den Warp §b" + warpName + " §7gelöscht!");
                }
                else
                {
                    p.sendMessage("§cFehler: Der Angegebene Warp '§c" + warpName + "§4' §4existiert nicht!");
                }
            }
            else
            {
                p.sendMessage(Data.prefix +"§c/delwarp <Name>");
            }
        }
        else {
            p.sendMessage(Data.prefix +"§cKeine Rechte!");
        }
        return false;
    }

    
}
