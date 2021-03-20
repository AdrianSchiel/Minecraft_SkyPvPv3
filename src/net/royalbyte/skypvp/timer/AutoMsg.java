package net.royalbyte.skypvp.timer;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import net.royalbyte.skypvp.Config;
import net.royalbyte.skypvp.Data;
import net.royalbyte.skypvp.SkyPvP;

public class AutoMsg {

	public AutoMsg() {
	}
	
	public void start() {
		new BukkitRunnable() {
			int i = 0;
			int msg = 0;
			@Override
			public void run() {
				i++;
				if(i == (Integer)Config.get("automsg.time")) {
					i = 0;
					msg++;
					if(msg == 1) {
						Bukkit.getOnlinePlayers().forEach((player) ->{
							player.sendMessage(Data.header);
							player.sendMessage( " ");
							player.sendMessage(Config.getString("automsg.msg-1"));
							player.sendMessage( " ");
							player.sendMessage(Data.header);
						});
					}else if(msg == 2) {
						Bukkit.getOnlinePlayers().forEach((player) ->{
							player.sendMessage(Data.header);
							player.sendMessage( " ");
							player.sendMessage(Config.getString("automsg.msg-2"));
							player.sendMessage( " ");
							player.sendMessage(Data.header);
						});
					}else if(msg == 3) {
						msg = 0;
						Bukkit.getOnlinePlayers().forEach((player) ->{
							player.sendMessage(Data.header);
							player.sendMessage( " ");
							player.sendMessage(Config.getString("automsg.msg-3"));
							player.sendMessage( " ");
							player.sendMessage(Data.header);
						});
					}
				}
				
			}
		}.runTaskTimerAsynchronously(SkyPvP.getInstance(), 0, 20);
	}
	
}
