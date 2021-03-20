/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_Vanish implements CommandExecutor {

	public static List<Player> vanish = new ArrayList<>();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 0) {
				if (p.hasPermission("SkyPvP.vanish")) {

					if (vanish.contains(p)) {
						vanish.remove(p);
						Bukkit.getOnlinePlayers().forEach((player) -> {
							player.showPlayer(p);
						});
						p.sendMessage(Config.getString("Command.vanish").replaceAll("%MODE%", "OFF"));

						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
					} else {
						vanish.add(p);
						Bukkit.getOnlinePlayers().forEach((player) -> {
							player.hidePlayer(p);
						});
						p.sendMessage(Config.getString("Command.vanish").replaceAll("%MODE%", "ON"));
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10F, 10F);
					}

				} else {
					p.sendMessage(Data.noperm);
					p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
				}
			} else if (args.length == 1) {
				Player t = Bukkit.getPlayer(args[0]);
				if (p.hasPermission("SkyPvP.vanish.other")) {

					if (t != null) {
						if (vanish.contains(t)) {
							vanish.remove(t);
							Bukkit.getOnlinePlayers().forEach((player) -> {
								player.showPlayer(t);
							});
							t.sendMessage(Config.getString("Command.vanish").replaceAll("%MODE%", "OFF"));

							t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						} else {
							vanish.add(t);
							Bukkit.getOnlinePlayers().forEach((player) -> {
								player.hidePlayer(t);
							});
							t.sendMessage(Config.getString("Command.vanish").replaceAll("%MODE%", "ON"));

							t.playSound(t.getLocation(), Sound.LEVEL_UP, 10F, 10F);
						}
					} else {
						p.sendMessage(Data.player_not_online);
					}

				} else {
					p.sendMessage(Data.noperm);
					p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
				}
			}else {
				sender.sendMessage(Data.prefix + "§cUse /vanish <Player>");
			}

		} else {
			sender.sendMessage(Data.must_a_player);
			return true;
		}

		return true;
	}

}
