package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Command_manage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(args.length == 1) {
				if(p.hasPermission("Skypvp.manage")) {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null) {
						if (!SkyPvP.getInstance().playermanager.containsKey(p)) {
							SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p, t);
							SkyPvP.getInstance().playermanager.put(p, t);
						} else {
							SkyPvP.getInstance().playermanager.remove(p);
							p.closeInventory();
							SkyPvP.getInstance().getInventories().openSkyPvPPlayer(p, t);
							SkyPvP.getInstance().playermanager.put(p, t);
						}
					}else {
						p.sendMessage(Data.player_not_online);
					}
				}else {
					p.sendMessage(Data.noperm);
				}
			}else {
				p.sendMessage(Data.prefix + "§cUse /manage <Player>.");
			}
		}else {
			sender.sendMessage(Data.must_a_player);
		}
		return true;
	}

}
