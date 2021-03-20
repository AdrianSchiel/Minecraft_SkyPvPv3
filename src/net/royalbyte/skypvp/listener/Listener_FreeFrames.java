/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.listener;

import org.bukkit.GameMode;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import net.royalbyte.skypvp.SkyPvP;

public class Listener_FreeFrames implements Listener {

	@EventHandler
	public void onInterract(PlayerInteractEntityEvent e) {
		Player p = e.getPlayer();
		if(e.getRightClicked() instanceof ItemFrame) {
			if(p.getGameMode()!= GameMode.CREATIVE) {
				final ItemFrame frame = (ItemFrame) e.getRightClicked();
				SkyPvP.getInstance().getInventories().openFreeFrame(p, frame.getItem());
				frame.setItem(frame.getItem());
				e.setCancelled(true);
			}
		}
	}

	
}
