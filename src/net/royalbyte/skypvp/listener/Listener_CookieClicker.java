package net.royalbyte.skypvp.listener;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;
import net.royalbyte.skypvp.manager.Manager_Messages;
import net.royalbyte.skypvp.mysql.Stats;

public class Listener_CookieClicker implements Listener {

	@EventHandler
	public void onInterract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		try {

			if (e.getClickedBlock().getType().equals(Material.SKULL)) {
				Skull s = (Skull) e.getClickedBlock().getState();
				if (s.getOwner().equalsIgnoreCase("QuadratCookie")) {
						int score = Stats.getCookieScore(p.getUniqueId().toString());
						Stats.addCookies(p.getUniqueId().toString(), score);
						Manager_Messages.sendactionbar(Config.getString("cookie.actionbar")
								.replaceAll("%CS%", "" + Stats.getCookieScore(p.getUniqueId().toString()))
								.replaceAll("%COOKIES%", "" + Stats.getCookies(p.getUniqueId().toString())), p);
						p.playSound(p.getLocation(), Sound.CLICK, 1, 1);
						SkyPvP.getInstance().getTablist().send(p);
				}

			}
		} catch (Exception e2) {
		}
	}

}
