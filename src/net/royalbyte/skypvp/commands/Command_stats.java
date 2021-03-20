package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.mysql.Stats;

public class Command_stats implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd,String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("SkyPvP.stats.own")) {
					int coins = Stats.getCoins(p.getUniqueId().toString());
					int tode = Stats.getTode(p.getUniqueId().toString());
					int kills = Stats.getKills(p.getUniqueId().toString());
					int points = Stats.getPoints(p.getUniqueId().toString());
					double kd = Stats.getKD(p.getUniqueId().toString());
					for (int i = 1; i < 7; i++) {
						p.sendMessage(Config.getString("command.stats.line-" + i).replaceAll("%PLAYER%", p.getName())
								.replaceAll("%KD%", String.valueOf(kd)).replaceAll("%POINTS%",
										String.valueOf(points)).replaceAll("%COINS%", String.valueOf(coins)).replaceAll(
												"%TODE%",
												String.valueOf(tode)).replaceAll("%KILLS%", "" + kills));	
					}
					
				} else {
					p.sendMessage(Data.noperm);
				}
			} else {
				sender.sendMessage(Data.must_a_player);
			}
		} else if (args.length == 1) {
			Player t = Bukkit.getPlayer(args[0]);
			if (sender.hasPermission("SkyPvP.stats.other")) {
				if (t != null) {
					int coins = Stats.getCoins(t.getUniqueId().toString());
					int tode = Stats.getTode(t.getUniqueId().toString());
					int kills = Stats.getKills(t.getUniqueId().toString());
					int points = Stats.getPoints(t.getUniqueId().toString());
					double kd = Stats.getKD(t.getUniqueId().toString());
					for (int i = 1; i < 7; i++) {
						sender.sendMessage(Config.getString("command.stats.line-" + i).replaceAll("%PLAYER%", t.getName())
								.replaceAll("%KD%", String.valueOf(kd)).replaceAll("%POINTS%",
										String.valueOf(points)).replaceAll("%COINS%", String.valueOf(coins)).replaceAll(
												"%TODE%",
												String.valueOf(tode)).replaceAll("%KILLS%", "" + kills));	
					}
					
				} else {
					sender.sendMessage(Data.player_not_online);
				}
			} else {
				sender.sendMessage(Data.noperm);
			}
		} else {

		}

		return true;
	}

}
