package net.royalbyte.skypvp.listener;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class Listener_data implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			if(e.getClickedInventory().getName() == "§bPlayer-Data") {
				e.setCancelled(true);
			}
		} catch (Exception e2) {
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockY() != e.getTo().getBlockY()
				|| e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
			new Spawn(e.getPlayer()).stop();
		}
	}

	@EventHandler
	public void onDmg(EntityDamageEvent e) {
		if(e.getEntityType() == EntityType.PLAYER) {
			new Spawn((Player) e.getEntity()).stop();
		}
	}
	
}
