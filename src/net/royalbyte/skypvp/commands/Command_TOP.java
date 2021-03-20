package net.royalbyte.skypvp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_TOP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
	
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(args.length == 0) {
				SkyPvP.getInstance().getRanking().setTop(p);
			}else {
				p.sendMessage(Data.prefix + "§cUse /top");
			}
		}else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}

}
