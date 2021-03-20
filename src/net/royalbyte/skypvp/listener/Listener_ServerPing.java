package net.royalbyte.skypvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;

public class Listener_ServerPing implements Listener {

	@EventHandler
	public void onPing(ServerListPingEvent e) {
		if(SkyPvP.getInstance().getWartung().isWartung()) {
			e.setMotd(Config.getString("wartung.motd-1") + "\n" + Config.getString("wartung.motd-2"));
		}else {
			e.setMotd(Config.getString("motd-1") + "\n" + Config.getString("motd-2"));
		}
	}
	
}
