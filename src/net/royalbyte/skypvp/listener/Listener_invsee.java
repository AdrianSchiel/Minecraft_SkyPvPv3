package net.royalbyte.skypvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import net.royalbyte.skypvp.commands.Command_invsee;


public class Listener_invsee implements Listener {
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		try {
			if (Command_invsee.map.containsKey(p)) {
				if (!p.hasPermission("SkyPvP.invsee.manage")) {
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
			if (Command_invsee.map.containsKey(p)) {
				Command_invsee.map.remove(p);
			}

		} catch (Exception e2) {
		}
	}
}
