package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.timer.kitdelay;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class Listener_Kits implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			if (e.getClickedInventory().getName() == "§b§lKits") {
				e.setCancelled(true);
				if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-1"))) {
					if (p.hasPermission("SkyPvP.getkit-1")) {

						SkyPvP.getInstance().getKits().getKit(p, "Kit-1", Config.getString("Kit-1"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}else 	if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-2"))) {
					if (p.hasPermission("SkyPvP.getkit-2")) {

						SkyPvP.getInstance().getKits().getKit(p, "Kit-2", Config.getString("Kit-2"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}else 	if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-3"))) {
					if (p.hasPermission("SkyPvP.getkit-3")) {

						SkyPvP.getInstance().getKits().getKit(p, "Kit-3", Config.getString("Kit-3"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}else 	if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-4"))) {
					if (p.hasPermission("SkyPvP.getkit-4")) {

						SkyPvP.getInstance().getKits().getKit(p, "Kit-4", Config.getString("Kit-4"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}else 	if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-5"))) {
					if (p.hasPermission("SkyPvP.getkit-5")) {

						SkyPvP.getInstance().getKits().getKit(p, "Kit-5", Config.getString("Kit-5"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}else 	if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-6"))) {
					if (p.hasPermission("SkyPvP.getkit-6")) {
						new kitdelay(p).start();
						SkyPvP.getInstance().getKits().getKit(p, "Kit-6", Config.getString("Kit-6"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}else 	if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(Config.getString("Kit-7"))) {
					if (p.hasPermission("SkyPvP.getkit-7")) {

						SkyPvP.getInstance().getKits().getKit(p, "Kit-7", Config.getString("Kit-7"));

					} else {
						p.sendMessage(Data.noperm);
						p.playSound(p.getLocation(), Sound.ENDERMAN_HIT, 1F, 1F);
					}
				}
			}
		} catch (Exception e2) {
		}
	}

}
