package net.royalbyte.skypvp.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_nameitem implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length >= 1) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (p.hasPermission("SkyPvP.nameitem")) {
					if(!(p.getItemInHand().getType() != null) || !p.getItemInHand().getType().equals(Material.AIR)) {
						String name = "";
						for (String part : args) {
						    if (name != "") name += " ";
						    name += part;
						    name = name.replaceAll("&", "§");
						}
						ItemMeta m = p.getItemInHand().getItemMeta();
						m.setDisplayName(name);
						p.getItemInHand().setItemMeta(m);
						p.updateInventory();
						p.sendMessage(Config.getString("command.nameitem.msg").replaceAll("%NAME%", name));
					}else {
						p.sendMessage(Config.getString("command.nameitem.must_hold_item_in_hand"));
					}
					
					
				} else {
					p.sendMessage(Data.noperm);
				}
			} else {
				sender.sendMessage(Data.must_a_player);
			}
		} else {
			sender.sendMessage(Data.prefix + "§cUse /nameitem <name>");
		}

		return true;
	}

}
