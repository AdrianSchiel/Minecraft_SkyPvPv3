package net.royalbyte.skypvp.listener;


import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import net.royalbyte.skypvp.mysql.Mute;

@SuppressWarnings("deprecation")
public class Listener_Mute implements Listener {

	@EventHandler
	public void onChat(PlayerChatEvent e) {
		Player p = e.getPlayer();
		if (Mute.isMuted(p.getUniqueId().toString())) {
			e.setCancelled(true);
			p.sendMessage(Mute.getBanMSG(p.getUniqueId().toString(), "Mute.msg.line-1"));
			p.sendMessage(Mute.getBanMSG(p.getUniqueId().toString(), "Mute.msg.line-2"));
			p.sendMessage(Mute.getBanMSG(p.getUniqueId().toString(), "Mute.msg.line-3"));
			p.sendMessage(Mute.getBanMSG(p.getUniqueId().toString(), "Mute.msg.line-4"));

		}
	}

}
