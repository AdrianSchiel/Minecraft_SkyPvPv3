/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_Update implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;
        if(commandSender.hasPermission("SkyPvP.team")){
          if(strings.length == 0){
              if (SkyPvP.UpdateV = true) {
                  commandSender.sendMessage("");
                  commandSender.sendMessage(Config.getString("prefix") + " §aDie neusten Updates zur Version:");
                  commandSender.sendMessage(Config.getString("prefix") + "     §2In der news.yml im SkyPVP Ordner");
                  commandSender.sendMessage("");
              }else {
                  commandSender.sendMessage("");
                  commandSender.sendMessage(Config.getString("prefix") + " §aDie neusten Updates zur Version:");
                  commandSender.sendMessage(Config.getString("prefix") + "     §2In der news.yml im SkyPVP Ordner");
                  commandSender.sendMessage("");
              }
          }
          if(strings.length == 1){
             if(s.equalsIgnoreCase("news")){
                 commandSender.sendMessage("");
                 commandSender.sendMessage(Config.getString("prefix") + " §aDie neusten Updates zur Version:");
                 commandSender.sendMessage(Config.getString("prefix") + "     §2In der news.yml im SkyPVP Ordner");
                 commandSender.sendMessage("");
              }
          }
        }
        return false;
    }
}
