package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_chatmute implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.chatmute")) {
			if(Config.getBoolean("command.chatmute.enabled")) {
				Config.set("command.chatmute.enabled", false);
				Config.save();
				Bukkit.broadcastMessage(Config.getString("command.chatmute.unmute").replaceAll("%PLAYER%", sender.getName()));
			}else {
				Config.set("command.chatmute.enabled", true);
				Config.save();
				Bukkit.broadcastMessage(Config.getString("command.chatmute.mute").replaceAll("%PLAYER%", sender.getName()));
			}
		}else {
			sender.sendMessage(Data.noperm);
		}
		
		return true;
	}

}
