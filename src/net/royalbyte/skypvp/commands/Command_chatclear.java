package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_chatclear implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.chatclear")) {
			for (int i = 0; i < 200; i++) {
				Bukkit.getOnlinePlayers().forEach(player -> {
					if(!player.hasPermission("SkyPvP.chatclear.bypass")) {
					player.sendMessage(" ");
					}
				});
			}
				Bukkit.broadcastMessage(Config.getString("command.chatclear").replaceAll("%PLAYER%", sender.getName()));
		}else {
			sender.sendMessage(Data.noperm);
		}
		
		return true;
	}

}
