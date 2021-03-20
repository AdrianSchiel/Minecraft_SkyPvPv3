/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_IP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (sender.hasPermission("SkyPvP.command.ip")) {
				Player t = Bukkit.getPlayer(args[0]);
				if (t != null) {
					String ip = t.getAddress().toString();
					sender.sendMessage(
							Config.getString("Command.ip").replaceAll("%PLAYER%", t.getName()).replaceAll("%IP%", ip));
				} else {
					sender.sendMessage(Data.player_not_online);
				}
			} else {
				sender.sendMessage(Data.noperm);
			}
		} else {
			sender.sendMessage(Data.prefix + "§cUse /ip <Player>");
		}

		return true;
	}

}
