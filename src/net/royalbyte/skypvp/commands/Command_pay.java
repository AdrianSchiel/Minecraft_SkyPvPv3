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

public class Command_pay implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p =(Player)sender;
			if(p.hasPermission("SkyPvP.pay")) {
			if(args.length == 2) {
				Player t = Bukkit.getPlayer(args[0]);
				int coins = Integer.parseInt(args[1]);
				if(t != null) {
					if(Stats.getCoins(p.getUniqueId().toString())>= coins) {
						Stats.addCoins(t.getUniqueId().toString(), coins);
						Stats.removeCoins(p.getUniqueId().toString(), coins);
						SkyPvP.getInstance().getScoreboard().setBoard(p);
						SkyPvP.getInstance().getScoreboard().setBoard(t);
						SkyPvP.getInstance().getTablist().send(p);
						SkyPvP.getInstance().getTablist().send(t);
						t.sendMessage(Config.getString("command.pay.other").replaceAll("%PLAYER%", p.getName()).replaceAll("%COINS%", String.valueOf(coins)));
						p.sendMessage(Config.getString("command.pay.own").replaceAll("%PLAYER%", t.getName()).replaceAll("%COINS%", String.valueOf(coins)));
					}else {
						p.sendMessage(Config.getString("command.pay.not_enough_coins"));
					}
				}else {
					p.sendMessage(Data.player_not_online);
				}
			}else {
				sender.sendMessage(Data.prefix + "§cUse /pay <Player> <amount>");
			}
			}else {
				p.sendMessage(Data.noperm);
			}
		}else {
			sender.sendMessage(Data.noperm);
		}
		return true;
	}

}
