/*
 * ---------------------------------------
 * Copyright @RoyalByte | Adrian Schiel
 * Youtube : RoyalByte Developer
 * Skype: RoyalByte
 * Website: RoyalByte.net
 * ---------------------------------------
 */

package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.utils.Tablist;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.mysql.Datas;

public class Listener_Quit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();

		// DATAS REGISTRIERUNG
		Datas.setlastonline(p.getUniqueId().toString(), System.currentTimeMillis());

		if (p.hasPermission("SkyPvP.team")) {
			e.setQuitMessage(Config.getString("Quit").replaceAll("%PLAYER%", p.getDisplayName()));
		} else {
			e.setQuitMessage(null);
		}

		// Tablist-Scoreboard
		new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.getOnlinePlayers().forEach(player->{
					if(Config.getBoolean("tablist.enabled").equals(true)){
						SkyPvP.getInstance().getTablist().send(player);
					}else{ }
				});
					
					
			}
		}.runTaskLaterAsynchronously(SkyPvP.getInstance(), 3);

	}
}
