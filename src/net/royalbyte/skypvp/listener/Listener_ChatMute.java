package net.royalbyte.skypvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.royalbyte.skypvp.Config;

public class Listener_ChatMute implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p =e.getPlayer();
		if(Config.getBoolean("command.chatmute.enabled")) {
			if(!p.hasPermission("SkyPvP.chatmute.bypass")) {
				e.setCancelled(true);
				p.sendMessage(Config.getString("command.chatmute.ismutet"));
			}
		}
		
	}
	
}
