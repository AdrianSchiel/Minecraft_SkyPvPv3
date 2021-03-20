package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.manager.Manager_ItemBuilder;

public class Command_ranggutschein implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 1) {
			if(sender instanceof Player) {
				Player p = (Player)sender;
				if(p.hasPermission("SkyPvP.ranggutschein")) {
					String rangname = args[0];
					p.getInventory().addItem(Manager_ItemBuilder.lore("§bRang §7: " + rangname, Material.PAPER, 1, 0 , new String[] {"" , "§8§l§ §7§lRechtsklick zum §b§leinl§sen" , ""}));
					p.sendMessage(Config.getString("command.ranggutschein.get").replaceAll("%RANG%", rangname));
				}else {
					p.sendMessage(Data.noperm);
				}
			}else {
				sender.sendMessage(Data.must_a_player);
			}
		}else if(args.length == 2) {
			if(sender.hasPermission("SkyPvp.ranggutschein")) {
				Player target = Bukkit.getPlayer(args[1]);
				if(target != null) {
					String rangname = args[0];
					target.getInventory().addItem(Manager_ItemBuilder.lore("§bRang §7: " + rangname, Material.PAPER, 1, 0 , new String[] {"" , "§8§l§ §7§lRechtsklick zum §b§leinl§sen" , ""}));
					target.sendMessage(Config.getString("command.ranggutschein.get").replaceAll("%RANG%", rangname));
				}else {
					sender.sendMessage(Data.player_not_online);
				}
			}else {
				sender.sendMessage(Data.noperm);
			}
		}else {
			sender.sendMessage(Data.prefix + "§cUse /ranggutschein <rang-name> <Player>");
		}
		
		return true;
	}

}
