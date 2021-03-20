package net.royalbyte.skypvp.utils;

import org.bukkit.Bukkit;

import net.royalbyte.skypvp.Config;

public class Wartung {

	public Wartung() {
	}
    public boolean isWartung() {
    	return Config.getBoolean("wartung-boolean");
    }
    


	public void wartung() {
		if(isWartung()) {
			Config.set("wartung-boolean", false);
			Bukkit.broadcastMessage(Config.getString("wartung.broadcast").replaceAll("%MODE%", "OFF"));
		}else {
			Config.set("wartung-boolean", true);
			Bukkit.broadcastMessage(Config.getString("wartung.broadcast").replaceAll("%MODE%", "ON"));
			Bukkit.getOnlinePlayers().forEach((player)->{
				if(!player.hasPermission("SkyPvP.wartung.bypass")) {
					player.kickPlayer(Config.getString("wartung.kick"));
				}
			});
		}
		
	}
	
}
