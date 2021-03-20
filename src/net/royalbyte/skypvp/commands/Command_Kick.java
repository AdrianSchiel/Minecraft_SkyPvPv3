package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_Kick implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender.hasPermission("SkyPvP.kick")) {
		if(args.length >= 2) {
			Player t = Bukkit.getPlayer(args[0]);
			if(t != null) {
				String text = "";
				for(int i =1; i!=args.length; i++) {
					text +=args[i] + " ";
				}
				text = text.replaceAll("&", "§");
				t.kickPlayer(Config.getString("Kick.message").replaceAll("%REASON%", text));
				sender.sendMessage(Config.getString("Kick.authormsg").replaceAll("%PLAYER%", t.getName()));
				Bukkit.getOnlinePlayers().forEach(player ->{
					if(player.hasPermission("SkyPvP.kick.notify")) {
						player.sendMessage(Config.getString("Kick.line-1").replaceAll("%PLAYER%", t.getName()).replaceAll("%AUTHOR%", sender.getName()));
					}
				});
			}else {
				sender.sendMessage(Data.player_not_online);
			}
			
		}else {
			sender.sendMessage(Data.prefix + "§cUse /kick <Spieler> <Grund>");
		}
		}else {
			sender.sendMessage(Data.noperm);
			return true;
		}
		return true;
	}

}
