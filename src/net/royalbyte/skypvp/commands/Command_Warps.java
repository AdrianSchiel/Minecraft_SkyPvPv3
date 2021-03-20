package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.utils.Manager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_Warps implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if (sender instanceof Player) {

			if (args.length == 0) {
					int i = 0;
					p.sendMessage(Data.prefix + "§7Alle §bWarps§7:");
					for (String key : Manager.data.getKeys(true) ) {
						if (!key.contains("."))
						{
							i++;
							p.sendMessage(String.valueOf("§7" + i + ". §b'" + key + "'"));
						}
					}
					i = 0;
				}
			} else {
				SkyPvP.getInstance().getInventories().openWarps(p);
			}
		return true;
	}

}
