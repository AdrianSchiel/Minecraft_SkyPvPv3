package net.royalbyte.skypvp.commands;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_Data implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("SkyPvP.data.other")) {
				if(args.length == 1) {
		            OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
					SkyPvP.getInstance().getInventories().openData(p, target.getUniqueId().toString());
				}else {
					SkyPvP.getInstance().getInventories().openData(p, p.getUniqueId().toString());
				}
			}else {
				SkyPvP.getInstance().getInventories().openData(p, p.getUniqueId().toString());
			}
		}else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}



}
