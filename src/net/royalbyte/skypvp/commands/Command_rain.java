/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_rain implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if(player.hasPermission("SkyPvP.rain")) {

                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"toggledownfall");
                        player.sendMessage(Data.prefix + "§aRegen wurde umgestellt");
                        return true;

        }
        return true;
    }
}