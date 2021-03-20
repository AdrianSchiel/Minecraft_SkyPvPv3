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

public class Command_Warp implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {
            Player p = (Player) sender;

            if(args.length == 1){
                String warpName = args[0];
                if (Manager.isWarpExists(warpName)) {
                    Manager.teleportToWarp(p, args[0]);
                    sender.sendMessage(Data.prefix +"§aDu wurdest erfolgreich Teleportiert!");
                } else {
                    p.sendMessage(Data.prefix + "§cDer Warp-Punkt wurde nicht gefunden!");
                }
            } else {
                p.sendMessage(Data.prefix + "§c/warp <Name>");
            }
        }
        return true;
    }

}

