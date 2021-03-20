/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_broadcast implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.broadcast")) {
			if(args.length >= 1) {
				String message = "";
				for (String part : args) {
				    if (message != "") message += " ";
				    message += part;
				}
				if(message != null) {
				Bukkit.broadcastMessage(Config.getString("command.broadcast.line-1").replaceAll("%MSG%", message).replaceAll("&", "§"));
				Bukkit.broadcastMessage(Config.getString("command.broadcast.line-2").replaceAll("%MSG%", message).replaceAll("&", "§"));
				Bukkit.broadcastMessage(Config.getString("command.broadcast.line-3").replaceAll("%MSG%", message).replaceAll("&", "§"));
				}
				for(Player all : Bukkit.getOnlinePlayers()) {
					all.playSound(all.getLocation(), Sound.LEVEL_UP, 1, 10);
				}
			}else {
				sender.sendMessage(Data.prefix + "§cUse /broadcast <Message>.");
			}
		}else {
			sender.sendMessage(Data.noperm);
		}
		return true;
	}

}
