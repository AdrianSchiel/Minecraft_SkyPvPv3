/*
 * Copyright @RoyalByte 2018
 */

package net.royalbyte.skypvp.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;


import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;

public class Command_enderchest implements CommandExecutor, Listener {
	public static HashMap<Player, Player> map = new HashMap<>();

//	map.put(p, t);
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player)sender;
			if (args.length == 1) {
				if(p.hasPermission("SkyPvP.enderchest.other")) {
					Player t = Bukkit.getPlayer(args[0]);
					if(t != null) {
						map.put(p, t);
						p.openInventory(t.getEnderChest());
						p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
						p.sendMessage(Config.getString("command.enderchest.other"));
					}else p.sendMessage(Data.player_not_online);
				}else p.sendMessage(Data.noperm);
			} else if (args.length == 0) {
				if(p.hasPermission("SkyPvP.enderchest.own")) {
					map.put(p, p);
					p.openInventory(p.getEnderChest());
					p.playSound(p.getLocation(), Sound.LEVEL_UP, 10, 10);
					p.sendMessage(Config.getString("command.enderchest.own"));
				}else p.sendMessage(Data.noperm);
			} else
				sender.sendMessage(Data.prefix + "§cUse /enderchest");
		} else
			sender.sendMessage(Data.must_a_player);
		return true;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		try {
			if (map.containsKey(p)) {
				if (!p.hasPermission("SkyPvP.enderchest.manage")) {
					e.setCancelled(true);
				}
			}
		} catch (Exception e2) {
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		try {
			if (map.containsKey(p)) {
				map.remove(p);
			}
		} catch (Exception e2) {
		}
	}
}
