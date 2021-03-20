package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;


public class Command_night implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("SkyPvP.night")) {
			if(args.length == 0) {
				p.getWorld().setTime(18000);
				p.sendMessage(Config.getString("command.night"));
				return true;
				
				
			}else {
				p.sendMessage(Data.prefix + "§cUse /night");
				return true;
			}
			}else {
				p.sendMessage(Data.noperm);
				return true;
			}
			
		}else {
			sender.sendMessage(Data.must_a_player);
			return true;
		}
		
	}

}
