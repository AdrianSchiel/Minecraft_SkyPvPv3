package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;

public class Command_Online implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		int on = Bukkit.getOnlinePlayers().size();
		sender.sendMessage(Config.getString("Command.online").replaceAll("%ONLINE%", "" +on));
		
		return true;
	}

}
