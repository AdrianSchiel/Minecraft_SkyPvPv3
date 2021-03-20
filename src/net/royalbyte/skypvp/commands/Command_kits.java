package net.royalbyte.skypvp.commands;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.timer.kitdelay;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_kits implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;

			if(Config.getBoolean("Kit.Delay-Enable") == true){
				if(kitdelay.count == 0){
					if (args.length == 0) {
						new kitdelay(p).start();
						SkyPvP.getInstance().getInventories().openKits(p);

					} else {
						p.sendMessage(Data.prefix + "§cUse /kits");
					}
				}else {
					p.sendMessage(Data.prefix + "§cDu hast bereits ein Kit ausgewählt bitte warte einen Moment! §8[§c" + kitdelay.count + "§8]");
				}

			}else {
				if (args.length == 0) {
					SkyPvP.getInstance().getInventories().openKits(p);

				} else {
					p.sendMessage(Data.prefix + "§cUse /kits");
				}
			}

		} else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}
}
