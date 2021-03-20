package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_ping implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 1) {
			Player p = Bukkit.getPlayer(args[0]);
			if (sender.hasPermission("SkyPvP.ping.other")) {
				if (p != null) {
					sender.sendMessage(Config.getString("command.ping.other").replaceAll("%PLAYER%", p.getName())
							.replaceAll("%PING%", String.valueOf(((CraftPlayer) p).getHandle().ping)));
				} else {
					sender.sendMessage(Data.noperm);
				}
			} else {
				sender.sendMessage(Data.noperm);
			}
		} else if (args.length == 0) {
			Player p = (Player) sender;
			if (p.hasPermission("SkyPvP.ping.own")) {
				int ping = ((CraftPlayer) p).getHandle().ping;
				p.sendMessage(Config.getString("command.ping.own").replaceAll("%PING%", String.valueOf(ping)));
			} else {
				p.sendMessage(Data.noperm);
			}
		} else {
			sender.sendMessage(Data.prefix + "§cUse /ping <Player>");
		}

		return true;
	}

}
