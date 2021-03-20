package net.royalbyte.skypvp.listener;

import net.royalbyte.skypvp.Data;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.SkyPvP;

import java.util.ArrayList;

public class Listener_Chat implements Listener {

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		String chatcolor = SkyPvP.getInstance().getRang().getChatColor(e.getPlayer().getUniqueId().toString());
		String prefix = SkyPvP.getInstance().getRang().getPrefix(e.getPlayer().getUniqueId().toString());
		String msg = e.getMessage();
		String playername = e.getPlayer().getName();
		if(!e.getPlayer().hasPermission("SkyPvP.chat.chatcolor")) {
		e.setFormat(Config.getString("chat").replaceAll("%LISTNAME%", prefix).replaceAll("%PLAYERNAME%", playername).replaceAll("%CHATCOLOR%", chatcolor).replaceAll("%MSG%", msg));
		}else {
			e.setFormat(Config.getString("chat").replaceAll("%LISTNAME%", prefix).replaceAll("%PLAYERNAME%", playername).replaceAll("%CHATCOLOR%", chatcolor).replaceAll("%MSG%", msg.replaceAll("&", "§")));
		}

	}

	
}
