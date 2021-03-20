/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.listener.Spawn;
import net.royalbyte.skypvp.manager.Manager_Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {


        if(!(sender instanceof Player)) {
            sender.sendMessage("Du musst ein Spieler sein!");
            return true;
        }

        Player p = (Player) sender;

        new Spawn(p).start();
        p.sendMessage(Data.prefix + "§aTeleportation startet...");

        return true;
    }
}
