package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_cmdwatchlist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.cmdwatchlist")) {
			if(Command_cmdwatch.cmdwatch.size() >= 1) {
			Command_cmdwatch.cmdwatch.forEach(player ->{
				sender.sendMessage(Config.getString("command.cmdwatchlist.msg").replaceAll("%PLAYER%", player.getDisplayName()));
			});
			}else {
				sender.sendMessage(Config.getString("command.cmdwatchlist.no_in_list"));
			}
		}else {
			sender.sendMessage(Data.noperm);
		}
		return true;
	}

}
