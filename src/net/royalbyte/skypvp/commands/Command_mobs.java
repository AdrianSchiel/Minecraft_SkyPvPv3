package net.royalbyte.skypvp.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.collect.Lists;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_mobs implements CommandExecutor {

	public static List<String> list = Lists.newArrayList();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("SkyPvP.command.mobs")){
				if(list.contains(p.getUniqueId().toString())) {
					list.remove(p.getUniqueId().toString());
					p.sendMessage(Config.getString("command.mobs.off"));
				}else {
					list.add(p.getUniqueId().toString());
					p.sendMessage(Config.getString("command.mobs.on"));
				}
			}else p.sendMessage(Data.noperm);
		}else sender.sendMessage(Data.must_a_player);
		return true;
	}

}
