package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_vanishlist implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.vanishlist")) {
			if(Command_Vanish.vanish.size() >= 1) {
				Command_Vanish.vanish.forEach(player ->{
				sender.sendMessage(Config.getString("command.vanishlist.msg").replaceAll("%PLAYER%", player.getDisplayName()));
			});
			}else {
				sender.sendMessage(Config.getString("command.vanishlist.no_in_list"));
			}
		}else {
			sender.sendMessage(Data.noperm);
		}
		return true;
	}

}
