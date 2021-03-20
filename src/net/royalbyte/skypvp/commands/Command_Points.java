package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.mysql.Stats;

public class Command_Points implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				sender.sendMessage(Config.getString("command.points.info").replaceAll("%POINTS%",
						Stats.getPoints(player.getUniqueId().toString()).toString()));
			}
		} else if (args.length == 3) {
			if (args[0].equalsIgnoreCase("add")) {
				Player target = Bukkit.getPlayer(args[1]);
				int coins = Integer.parseInt(args[2]);
				if (sender.hasPermission("SkyPvP.addPoints")) {
					if (target != null) {
						Stats.addPoints(target.getUniqueId().toString(), coins);
						SkyPvP.getInstance().getScoreboard().setBoard(target);
						SkyPvP.getInstance().getTablist().send(target);
						sender.sendMessage(
								Config.getString("command.points.update").replaceAll("%PLAYER%", target.getName()));
					} else {
						sender.sendMessage(Data.player_not_online);
					}
				} else {
					sender.sendMessage(Data.noperm);
				}
			} else if (args[0].equalsIgnoreCase("remove")) {
				Player target = Bukkit.getPlayer(args[1]);
				int coins = Integer.parseInt(args[2]);
				if (sender.hasPermission("SkyPvP.removePoints")) {
					if (target != null) {
						Stats.removePoints(target.getUniqueId().toString(), coins);
						SkyPvP.getInstance().getScoreboard().setBoard(target);
						SkyPvP.getInstance().getTablist().send(target);
						sender.sendMessage(
								Config.getString("command.points.update").replaceAll("%PLAYER%", target.getName()));
					} else {
						sender.sendMessage(Data.player_not_online);
					}
				} else {
					sender.sendMessage(Data.noperm);
				}
			} else if (args[0].equalsIgnoreCase("set")) {
				Player target = Bukkit.getPlayer(args[1]);
				int coins = Integer.parseInt(args[2]);
				if (sender.hasPermission("SkyPvP.setPoints")) {
					if (target != null) {
						Stats.setPoints(target.getUniqueId().toString(), coins);
						SkyPvP.getInstance().getScoreboard().setBoard(target);
						SkyPvP.getInstance().getTablist().send(target);
						sender.sendMessage(
								Config.getString("command.points.update").replaceAll("%PLAYER%", target.getName()));
					} else {
						sender.sendMessage(Data.player_not_online);
					}
				} else {
					sender.sendMessage(Data.noperm);
				}
			} else {
				sender.sendMessage(Data.prefix + "§cUse /points <add,remove,set> <Spieler> <Points>");
			}
		} else {
			sender.sendMessage(Data.prefix + "§cUse /points <add,remove,set> <Spieler> <Points>");
		}

		return true;
	}
}
