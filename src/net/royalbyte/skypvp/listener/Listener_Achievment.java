package net.royalbyte.skypvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;

public class Listener_Achievment implements Listener {

	@EventHandler
	public void onAchievment(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}


}
