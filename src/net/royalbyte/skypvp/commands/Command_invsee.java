package net.royalbyte.skypvp.commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_invsee implements CommandExecutor {

	public static Map<Player, Player> map = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.hasPermission("SkyPvP.invsee.use")) {
				if(args.length == 1) {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null) {
						p.openInventory(t.getInventory());
						map.put(p, t);
						p.sendMessage(Config.getString("command.invsee.msg").replaceAll("%PLAYER%", t.getName()));
					}else {
						p.sendMessage(Data.player_not_online);
					}
				}else {
					p.sendMessage(Data.prefix + "§cUse /invsee <PLAYER>");
				}				
			}else {
				p.sendMessage(Data.noperm);
			}

		}else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}

}
