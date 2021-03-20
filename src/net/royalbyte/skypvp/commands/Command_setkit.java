package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_setkit implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (args.length == 1) {
				if (p.hasPermission("SkyPvP.setKit")) {
					if (args[0].startsWith("Kit-")) {
						int i = Integer.parseInt(args[0].replaceAll("Kit-", ""));
							if (i<= 7) {
								if (p.hasPermission("SkyPvP.setKit.Kit-" + i)) {
									SkyPvP.getInstance().getKits().saveKit(p, "Kit-" + i, Config.getString("Kit-" + i));
								} else {
									p.sendMessage(Data.noperm);
								}
							} else {
								p.sendMessage(Data.prefix + "§cUse /setkit <Kit-(1-7)>");
							}
					} else {
						p.sendMessage(Data.prefix + "§cUse /setkit <Kit-(1-7)>");
					}
				} else {
					p.sendMessage(Data.noperm);
				}
			} else {
				p.sendMessage(Data.prefix + "§cUse /setkit <Kit-(1-7)>");
			}
		} else {
			sender.sendMessage(Data.must_a_player);
		}

		return true;
	}

}
