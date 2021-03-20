package net.royalbyte.skypvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.mysql.Rewards;

public class Listener_Rewards implements Listener {

	@SuppressWarnings("static-access")
	@EventHandler
	public void onRewards(InventoryClickEvent e) {
		try {
			Player p = (Player) e.getWhoClicked();
			if(e.getClickedInventory().getName().equalsIgnoreCase("§b§lRewards")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bStuendlicher-Reward")) {
					if(SkyPvP.getInstance().getRewards().isAllowedstuendlich(p)) {
						SkyPvP.getInstance().getRewards().setstuendlich(p);	
						p.closeInventory();
						SkyPvP.getInstance().getInventories().openRewards(p);
					}else {
						p.sendMessage(Config.getString("rewards.not_alowed.stuendlich").replaceAll("%TIME%", SkyPvP.getInstance().getRewards().getEndDate(p, Rewards.getstuendlich(p.getUniqueId().toString()))));
					}
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bTaeglicher-Reward")) {
					if(SkyPvP.getInstance().getRewards().isAllowedtaeglich(p)) {
						SkyPvP.getInstance().getRewards().settaeglich(p);	
						p.closeInventory();
						SkyPvP.getInstance().getInventories().openRewards(p);
					}else {
						p.sendMessage(Config.getString("rewards.not_alowed.taeglich").replaceAll("%TIME%", SkyPvP.getInstance().getRewards().getEndDate(p, Rewards.getstuendlich(p.getUniqueId().toString()))));
					}
				}else if(e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bWoechentlicher-Reward")) {
					if(SkyPvP.getInstance().getRewards().isAllowedwoechentlich(p)) {
						SkyPvP.getInstance().getRewards().setwoechentlich(p);	
						p.closeInventory();
						SkyPvP.getInstance().getInventories().openRewards(p);
					}else {
						p.sendMessage(Config.getString("rewards.not_alowed.woechentlich").replaceAll("%TIME%", SkyPvP.getInstance().getRewards().getEndDate(p, Rewards.getwoechentlich(p.getUniqueId().toString()))));
					}
				}
			}
		} catch (Exception e2) {

		}
	}
	
}
