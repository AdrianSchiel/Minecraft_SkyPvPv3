package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_teamchat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.teamchat.use")) {
			if(args.length >= 1) {
				String msg = "";
				for(int i =0; i!=args.length; i++) {
					msg +=args[i] + " ";
				}
				for(Player all : Bukkit.getOnlinePlayers()) {
					if(all.hasPermission("Skypvp.teamchat.notify")) {
						all.sendMessage(Config.getString("command.teamchat").replaceAll("%MSG%", msg).replaceAll("%PLAYER%", sender.getName()));
					}	
				}
				
			}else {
				sender.sendMessage(Data.prefix + "§cUse /teamchat <message>");
			}
		}else {
			sender.sendMessage(Data.noperm);
		}
		return true;
	}

}
