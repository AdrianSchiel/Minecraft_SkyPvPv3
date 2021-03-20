package net.royalbyte.skypvp.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_anvil implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player)sender;
			if(p.hasPermission("SkyPvP.anvil")) {

				Inventory inv = Bukkit.createInventory(p, InventoryType.ANVIL);
				p.openInventory(inv);
				p.sendMessage(Config.getString("command.anvil"));


			}else p.sendMessage(Data.noperm);
		}else sender.sendMessage(Data.must_a_player);
		return true;
	}

}
