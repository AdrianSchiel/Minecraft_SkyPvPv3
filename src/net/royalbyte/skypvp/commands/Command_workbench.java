package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_workbench implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("SkyPvP.workbench")) {
				p.openWorkbench(p.getLocation(), true); 
				p.sendMessage(Config.getString("command.workbench"));
			} else
				p.sendMessage(Data.noperm);
		} else
			sender.sendMessage(Data.must_a_player);
		return true;
	}

}
