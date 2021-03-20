package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;

public class Command_skull implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("SkyPvP.skull")) {
					p.getInventory().addItem(Manager_ItemBuilder.Skull("§b" + args[0], args[0], 1));
					p.sendMessage(Config.getString("command.skull").replaceAll("%NAME%", args[0]));
				}else {
					p.sendMessage(Data.noperm);
				}
			}else {
				sender.sendMessage(Data.must_a_player);
			}
		}else {
			sender.sendMessage(Data.prefix + "§cUse /skull <Player>");
		}
		
		return true;
	}

}
