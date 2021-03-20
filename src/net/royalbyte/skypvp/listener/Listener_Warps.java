package net.royalbyte.skypvp.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.royalbyte.skypvp.manager.Manager_Location;

public class Listener_Warps implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			if (e.getClickedInventory().getName() == "§b§lWarps") {
				e.setCancelled(true);
				Player p = (Player) e.getWhoClicked();
				if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §bShop") {
					Manager_Location.tpLocation(p, "shop");
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §bTeam-Halle") {
					Manager_Location.tpLocation(p, "teamhalle");
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §bCookie-Clicker") {
					Manager_Location.tpLocation(p, "Cookie");
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §bSpawn") {
					Manager_Location.tpLocation(p, "spawn");
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				} else if (e.getCurrentItem().getItemMeta().getDisplayName() == "§8§l§ §bVerzaubern") {
					Manager_Location.tpLocation(p, "Verzaubern");
					p.closeInventory();
					p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
				}
			}
		} catch (Exception e2) {
		}
	}

}
