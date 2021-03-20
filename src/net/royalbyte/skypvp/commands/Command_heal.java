package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_heal implements CommandExecutor {


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(args.length == 1) {
			if(sender.hasPermission("SkyPvP.heal.other")) {
				Player t = Bukkit.getPlayer(args[0]);
				if(t != null) {
					t.setHealth(20);
					t.sendMessage(Config.getString("command.heal.own"));
					sender.sendMessage(Config.getString("command.heal.other").replaceAll("%PLAYER%", t.getName()));
				}else {
					sender.sendMessage(Data.player_not_online);
				}
			}else {
				sender.sendMessage(Data.noperm);
			}
		}else if(args.length == 0) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("SkyPVP.heal.own")) {
					p.setHealth(20);
					p.sendMessage(Config.getString("command.heal.own"));
				}else {
					p.sendMessage(Data.noperm);
				}
			}else {
				sender.sendMessage(Data.must_a_player);
			}
		}else {
			sender.sendMessage(Data.prefix + "§cUse /heal <Player>");
		}
		
		return true;
	}

}
