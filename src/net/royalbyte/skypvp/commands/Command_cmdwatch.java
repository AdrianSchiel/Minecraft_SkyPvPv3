package net.royalbyte.skypvp.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_cmdwatch implements CommandExecutor {

	public static List<Player> cmdwatch = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("SkyPvP.cmdwatch.own")) {
					if (cmdwatch.contains(p)) {
						cmdwatch.remove(p);
						p.sendMessage(Config.getString("cmdwatch.command.msg.own.off"));
					} else {
						cmdwatch.add(p);
						p.sendMessage(Config.getString("cmdwatch.command.msg.own.on"));
					}
				} else {
					p.sendMessage(Data.noperm);
				}
			} else {
				sender.sendMessage(Data.prefix + "§cUse /cmdwatch <Player>");
			}
		} else if (args.length == 1) {
			if (sender.hasPermission("SkyPvP.cmdwatch.other")) {
				Player t = Bukkit.getPlayer(args[0]);
				if (t != null) {
					if (cmdwatch.contains(t)) {
						cmdwatch.remove(t);
						t.sendMessage(Config.getString("cmdwatch.command.msg.own.off"));
						sender.sendMessage(
								Config.getString("cmdwatch.command.msg.other.off").replaceAll("%PLAYER%", t.getName()));
					} else {
						cmdwatch.add(t);
						t.sendMessage(Config.getString("cmdwatch.command.msg.own.on"));
						sender.sendMessage(
								Config.getString("cmdwatch.command.msg.other.on").replaceAll("%PLAYER%", t.getName()));
					}
				} else {
					sender.sendMessage(Data.player_not_online);
				}
			} else {
				sender.sendMessage(Data.noperm);
			}
		} else {
			sender.sendMessage(Data.prefix + "§cUse /cmdwatch <Player>");
		}

		return true;
	}

}
