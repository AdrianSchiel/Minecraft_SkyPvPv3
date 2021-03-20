
package net.royalbyte.skypvp.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.commands.Command_cmdwatch;


public class Listener_CMDWATCH implements Listener {

	@EventHandler
	public void onChat(PlayerCommandPreprocessEvent e) {
		if(!e.getPlayer().hasPermission("SkyPvP.cmdwatch.bypass")) {
			Command_cmdwatch.cmdwatch.forEach(player ->{
				player.sendMessage(Config.getString("cmdwatch.msg").replaceAll("%PLAYER%", e.getPlayer().getName()).replaceAll("%CMD%", e.getMessage()));
			});
		}
	}
	
}
