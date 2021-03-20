package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Command_setInstantKill implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
		Player p = (Player)sender;
		if(p.hasPermission("SkyPvP.command.setInstantKill")) {
			if(args.length == 0) {
				Config.cfg.set("instantkill", p.getLocation().getY());
				Config.save();
				p.sendMessage(Data.prefix + "§6Die §3Instant-Kill-Höhe §6wurde erfolgreich gesetzt.");
				return true;
			}else {
				p.sendMessage(Data.prefix + "§cUse /setinstantkill");
				return true;
			}
		}else {
			p.sendMessage(Data.noperm);
			return true;	
		}
		}else {
			sender.sendMessage(Data.must_a_player);
			return true;
		}
	}

}
